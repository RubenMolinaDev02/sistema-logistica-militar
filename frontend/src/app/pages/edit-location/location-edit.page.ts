import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicFormComponent } from '../../shared/components/dynamic-form-component/dynamic-form.component';
import { ApiInfoService } from '../../core/services/api.info.service';
import { CREATE_PLATFORM_MODEL } from '../../shared/models/create-items-dto/create-platform.dto';
import { hydrateForm } from '../../shared/models/form-hydrator';
import { UPDATE_WEAPON_MODEL } from '../../shared/models/update-items-dto/weapon-update.dto';
import { DynamicFieldComponent } from "../../shared/components/decorative-footer/decorative-footer.component";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { UPDATE_USER_MODEL } from '../../shared/models/users/update-user.dto';
import { UPDATE_LOCATION_MODEL } from './location-edit.dto';

@Component({
  selector: 'app-user-edit',
  standalone: true,
  imports: [CommonModule, DynamicFormComponent, DynamicFieldComponent, ItemHeader, UiButtonComponent],
  templateUrl: './location-edit.page.html'
})
export class EditLocationComponent {

  model: any;

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

    this.id =
      this.route.snapshot.paramMap.get('id') ?? '';

    const template = UPDATE_LOCATION_MODEL

    this.api.getItemsById(this.id, `/organization/locations/`)
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


  handleCreate(payload: any) {
  this.info.editItem(payload, `/organization/locations/${this.id}`).subscribe({
    next: () => {
        this.router.navigate(['/locations']);
    }
  });
}

  cancel() {
    this.router.navigate(['/locations']);
  }
}