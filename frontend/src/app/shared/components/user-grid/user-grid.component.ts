import { Component, Input } from '@angular/core';
import { UserCard } from '../user-card/user-card.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-grid',
  imports: [UserCard, CommonModule],
  templateUrl: './user-grid.component.html',
})
export class UserGrid {
  @Input() items: any[] = [];
  @Input() category: any;
}
