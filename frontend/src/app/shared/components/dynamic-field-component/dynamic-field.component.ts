import { CommonModule } from "@angular/common";
import { Component, Input, Output, EventEmitter, ChangeDetectorRef } from "@angular/core";
import { FormFieldDto } from "../../models/form-dto/form-field.dto";
import { ApiInfoService } from "../../../core/services/api.info.service";

@Component({
  selector: 'app-dynamic-field',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dynamic-field.component.html'
})
export class DynamicFieldComponent {

  @Input() field!: FormFieldDto;

  @Input() value: any;

  @Output() valueChange = new EventEmitter<any>();

  onChange(val: any) {
    this.valueChange.emit(val);
  }

  options: any[] = [];

constructor(
    private api: ApiInfoService,
    private cdr: ChangeDetectorRef
) {}

ngOnInit() {
  if (this.field.type === 'SELECT_REMOTE') {
    this.loadRemoteOptions();
  }
}

loadRemoteOptions() {
  this.api.get(this.field.endpoint).subscribe(res => {
    this.options = res;
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

getSelectValue(event: Event): string {
  return (event.target as HTMLSelectElement).value;
}

onMultiSelect(event: Event) {
  const select = event.target as HTMLSelectElement;

  const values = Array.from(select.selectedOptions).map(o => o.value);

  this.onChange(values);
}
}