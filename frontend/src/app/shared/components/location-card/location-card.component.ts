import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-location-card',
  imports: [CommonModule],
  templateUrl: './location-card.component.html'
})
export class LocationCard {
  constructor(
    private router: Router
  ) {}

  @Input() category: any;
  @Input() item: any;

  goToDetail(): void {
  this.router.navigate([
    '/locations/detail',
    this.item.id
  ]);
}
}
