import { CommonModule } from "@angular/common";
import {
  Component,
  Input,
  Output,
  EventEmitter,
  ChangeDetectorRef,
  DoCheck
} from "@angular/core";
import { FormFieldDto } from "../../models/form-dto/form-field.dto";
import { ApiInfoService } from "../../../core/services/api.info.service";


@Component({
  selector: 'app-dynamic-field',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dynamic-field.component.html'
})
export class DynamicFieldComponent{

  @Input() field!: FormFieldDto;

  @Input() value: any;

  @Output() valueChange = new EventEmitter<any>();

  onChange(val: any) {

  this.value = val;

  this.valueChange.emit(val);
}

  options: any[] = [];

constructor(
    private api: ApiInfoService,
    private cdr: ChangeDetectorRef
) {}

ngOnInit() {

  if (
    this.field.type === 'SELECT' &&
    !this.value &&
    this.field.options?.length
  ) {

    this.value = this.field.options[0];

    this.valueChange.emit(this.value);
  }

  if (this.field.type === 'SELECT_REMOTE') {
    this.loadRemoteOptions();
  }

  if (this.field.type === 'SELECT_REMOTE_DEPENDENT') {
    this.loadDependentOptions();
  }

  if (this.field.type === 'SELECT_REMOTE_MULTIPLE') {
    this.loadRemoteOptions();
  }
}

isSelected(id: any): boolean {
  return Array.isArray(this.value) && this.value.includes(id);
}

private _formValues: Record<string, any> = {};

@Input()
set formValues(val: Record<string, any>) {
  this._formValues = val;

  if (this.field?.type === 'SELECT_REMOTE_DEPENDENT') {
    this.tryReload();
  }
}

get formValues() {
  return this._formValues;
}

private lastDependencyState = '';

private tryReload() {
  if (!this.field?.dependsOn?.length) return;

  const state = this.field.dependsOn
    .map(dep => this._formValues?.[dep] ?? '')
    .join('|');

  if (state === this.lastDependencyState) return;

  this.lastDependencyState = state;

  this.loadDependentOptions();
}

loadRemoteOptions() {
  this.api.get(`${this.field.endpoint}`).subscribe(res => {
    this.options = res;

    // Solo auto-seleccionar el primero para SELECT_REMOTE simple
    if (this.field.type === 'SELECT_REMOTE' && !this.value && this.options.length) {
      this.value = this.options[0].id;
      this.valueChange.emit(this.value);
    }

    // Para SELECT_REMOTE_MULTIPLE, garantizar que value sea siempre un array
    if (this.field.type === 'SELECT_REMOTE_MULTIPLE') {
      if (!Array.isArray(this.value)) {
        this.value = this.value ? [this.value] : [];
      }
    }

    this.cdr.detectChanges();
  });
}

  getInputValue(event: Event): string {
  return (event.target as HTMLInputElement).value;
}

getNumberValue(event: Event): number {
  return (event.target as HTMLInputElement).valueAsNumber;
}

getCheckedValue(event: Event): boolean {
  return (event.target as HTMLInputElement).checked;
}

getSelectValue(event: Event): string | null {

  const value =
    (event.target as HTMLSelectElement).value;

  return value === '' ? null : value;
}

onMultiSelect(event: Event) {
  const select = event.target as HTMLSelectElement;

  const values = Array.from(select.selectedOptions).map(o => o.value);

  this.onChange(values);
}

onFileSelected(event: Event) {

  const input = event.target as HTMLInputElement;

  if (!input.files?.length) return;

  const file = input.files[0];

  const formData = new FormData();

  formData.append('file', file);

  this.api.uploadImage(formData).subscribe({
    next: (res: any) => {
      this.onChange(res.url);
    }
  });
}

loadDependentOptions() {
  if (!this.field.dependsOn?.length) return;

  const params: Record<string, any> = {};

  for (const dep of this.field.dependsOn) {
    const value = this.formValues[dep];

    if (!value) {
      this.options = [];
      this.value = null;
      this.valueChange.emit(null);
      return;
    }

    params[dep] = value;
  }

  // 🔥 reset antes de cargar nuevas opciones
  this.options = [];
  this.value = null;
  this.valueChange.emit(null);

  this.api.getWithParams(this.field.endpoint!, params)
    .subscribe(res => {
      this.options = res;

      if (this.options.length) {
        this.value = this.options[0].id;
        this.valueChange.emit(this.value);
      }

      this.cdr.detectChanges();
    });
}

msOpen = false;

// Para MULTISELECT (opciones estáticas)
toggleOption(opt: string) {
  const current: string[] = this.value ?? [];
  const idx = current.indexOf(opt);
  const next = idx >= 0 ? current.filter(o => o !== opt) : [...current, opt];
  this.onChange(next);
}

removeOption(opt: string) {
  this.onChange((this.value ?? []).filter((o: string) => o !== opt));
}

// Para SELECT_REMOTE_MULTIPLE (opciones remotas con id/name)
toggleOptionById(id: any) {
  const current: any[] = this.value ?? [];
  const idx = current.findIndex(v => v == id);
  const next = idx >= 0 ? current.filter(v => v != id) : [...current, id];
  this.onChange(next);
}

removeOptionById(id: any) {
  this.onChange((this.value ?? []).filter((v: any) => v != id));
}

getOptionName(id: any): string {
  return this.options.find(o => o.id == id)?.name ?? id;
}
}