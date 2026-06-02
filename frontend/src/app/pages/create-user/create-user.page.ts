import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicFormComponent } from '../../shared/components/dynamic-form-component/dynamic-form.component';
import { ApiInfoService } from '../../core/services/api.info.service';
import { hydrateForm } from '../../shared/models/form-hydrator';
import { DynamicFieldComponent } from "../../shared/components/decorative-footer/decorative-footer.component";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { CREATE_USER_MODEL } from '../../shared/models/users/create-user.dto';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'app-item-edit',
  standalone: true,
  imports: [CommonModule, DynamicFormComponent, DynamicFieldComponent, ItemHeader, UiButtonComponent],
  templateUrl: './create-user.page.html'
})
export class UserCreateComponent {

  model: any;

  constructor(
    private api: ApiInfoService,
    private userService : UserService,
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
    this.model = hydrateForm(CREATE_USER_MODEL, {});
    this.cdr.detectChanges();
}

  handleCreate(payload: any) {
  this.info.create(payload, `/admin/users`).subscribe({
    next: (response) => {
        sessionStorage.setItem(
            'created-user',
            JSON.stringify(response)
        );
        this.router.navigate(['/users/create/results']);
    }
  });
}

  cancel() {
    this.router.navigate(['/users']);
  }
}