import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface UserResponse {
  id: string;
  soldierNumber: string;
  firstName: string;
  lastName: string;
  dni: string;
  phoneNumber: number;
  role: string;
  email: string;
  username: string;
  avatarUrl: string;
  createdAt: string;
  locationId: string;
  rank: string;
  active: boolean;
  tempPassword: string;
}

@Component({
  selector: 'app-user-created',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-created.component.html'
})
export class UserCreatedComponent {

  user!: UserResponse;

  copied = false;

  ngOnInit() {
    const stored = sessionStorage.getItem('created-user');

    if (stored) {
      this.user = JSON.parse(stored);
    }
  }

  copyPassword() {
    navigator.clipboard.writeText(this.user.tempPassword);
    this.copied = true;

    setTimeout(() => {
      this.copied = false;
    }, 2000);
  }
}