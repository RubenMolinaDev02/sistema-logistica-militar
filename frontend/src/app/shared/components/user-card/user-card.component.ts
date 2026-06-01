import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-card',
  imports: [CommonModule],
  templateUrl: './user-card.component.html'
})
export class UserCard {
  constructor(
    private router: Router
  ) {}

  @Input() category: any;
  @Input() item: any;

  goToDetail(): void {
  this.router.navigate([
    '/users/detail',
    this.item.id
  ]);
}
}
