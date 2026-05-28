import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicFormComponent } from '../../shared/components/dynamic-form-component/dynamic-form.component';
import { CREATE_WEAPON_MODEL } from '../../shared/models/create-items-dto/create-weapon.dto'; 
import { ApiInfoService } from '../../core/services/api.info.service';
import { CREATE_PLATFORM_MODEL } from '../../shared/models/create-items-dto/create-platform.dto';
import { hydrateForm } from '../../shared/models/form-hydrator';
import { UPDATE_WEAPON_MODEL } from '../../shared/models/update-items-dto/weapon-update.dto';
import { DynamicFieldComponent } from "../../shared/components/decorative-footer/decorative-footer.component";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";

@Component({
  selector: 'app-item-edit',
  standalone: true,
  imports: [CommonModule, DynamicFormComponent, DynamicFieldComponent, ItemHeader, UiButtonComponent],
  templateUrl: './item-edit.component.html'
})
export class ItemEditComponent {

  model: any;

  category = "";

  id = "";

  constructor(
    private api: ApiInfoService,
    private router: Router,
    private route: ActivatedRoute,
    private info: ApiInfoService,
    private cdr: ChangeDetectorRef,
    ) { }


    submitForm() {
  const values: Record<string, any> = {};

  for (const section of this.model.sections) {
    for (const field of section.fields) {
      values[field.key] = field.value;
    }
  }

  this.handleCreate(values);
}

ngOnInit(): void {

    this.category =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.id =
      this.route.snapshot.paramMap.get('id') ?? '';

    const template = this.getTemplate(this.category);

    this.api.getItemsById(this.id, `/armory/weapons/model/`)
    .subscribe({
      next: (data) => {
        this.model = hydrateForm(template, data);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error loading item:', err);
        this.model = hydrateForm(template, {}); // fallback seguro
        this.cdr.detectChanges();
      }
    });
  }

  private getTemplate(category: string) {

  switch (category) {

    case 'weapons':
      return UPDATE_WEAPON_MODEL;

    case 'platforms':
      return CREATE_PLATFORM_MODEL;

    default:
      return UPDATE_WEAPON_MODEL;
  }
}

  handleCreate(payload: any) {
  console.log('CREATE PAYLOAD:', payload);

  const map: Record<string, any> = {
    weapons: (data: any) => this.info.editItem(data, `/armory/weapons/${this.id}`),
    //platforms: (data: any) => this.info.createItem(data, "platforms"),
    /*ammo: (data: any) => this.info.createAmmo(data),
    magazines: (data: any) => this.info.createMagazine(data),
    misc: (data: any) => this.info.createMisc(data)*/
  };

  const req = map[this.category];

  if (!req) return;

  req(payload).subscribe({
    next: () => {
        this.router.navigate(['/items', this.category]);
    }
  });
}

  cancel() {
    this.router.navigate(['/items', this.category]);
  }
}