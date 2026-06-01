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
import { UserService } from '../../core/services/user.service';
import { UPDATE_MY_PROFILE_MODEL } from '../../shared/models/users/update-my-profile.dto';

@Component({
  selector: 'app-item-edit',
  standalone: true,
  imports: [CommonModule, DynamicFormComponent, DynamicFieldComponent, ItemHeader, UiButtonComponent],
  templateUrl: './edit-my-profile.component.html'
})
export class EditMyProfileComponent {

  model: any;

  constructor(
    private api: UserService,
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


    const template = UPDATE_MY_PROFILE_MODEL

    this.api.getMyUser()
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
  this.api.editMyUser(payload).subscribe({
    next: () => {
        this.router.navigate(['/users']);
    }
  });
}

  cancel() {
    this.router.navigate(['/users']);
  }
}