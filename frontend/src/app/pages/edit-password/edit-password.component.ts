import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiInfoService } from '../../core/services/api.info.service';
import { DynamicFieldComponent } from "../../shared/components/decorative-footer/decorative-footer.component";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";

@Component({
  selector: 'app-user-edit',
  standalone: true,
  imports: [CommonModule, FormsModule, DynamicFieldComponent, ItemHeader, UiButtonComponent],
  templateUrl: './edit-password.component.html'
})
export class EditPasswordComponent {

  id = '';
  currentPassword : string = '';
  newPassword : string = '';
  confirmPassword = '';
  apiError = '';

  errors: { current?: string; new?: string; confirm?: string } = {};

  constructor(
    private api: ApiInfoService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id') ?? '';
  }

  submitForm() {
    this.errors = {};
    this.apiError = '';

    if (!this.currentPassword) {
      this.errors.current = 'Required field';
    }
    if (!this.newPassword) {
      this.errors.new = 'Required field';
    } else if (this.newPassword.length < 6) {
      this.errors.new = 'Minimum 6 characters';
    }
    if (!this.confirmPassword) {
      this.errors.confirm = 'Required field';
    } else if (this.newPassword !== this.confirmPassword) {
      this.errors.confirm = 'Passwords do not match';
    }

    if (Object.keys(this.errors).length) return;

    this.api.changePassword(
      this.currentPassword,
      this.newPassword
    ).subscribe({
      next: () => this.router.navigate(['/myuser']),
      error: (err) => {
        this.apiError = err?.error?.message ?? 'Error changing password';
      }
    });
  }

  cancel() {
    this.router.navigate(['/myuser']);
  }
}