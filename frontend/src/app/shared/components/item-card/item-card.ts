import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-item-card',
  imports: [CommonModule],
  templateUrl: './item-card.html',
  styleUrl: './item-card.css',
})
export class ItemCard {
  constructor(
    private router: Router
  ) {}

  @Input() category: any;
  @Input() item: any;

  goToDetail(): void {
  this.router.navigate([
    '/items',
    this.category,
    this.item.id
  ]);
}
}
