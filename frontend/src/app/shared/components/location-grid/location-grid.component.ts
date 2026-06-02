import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LocationCard } from '../location-card/location-card.component';

@Component({
  selector: 'app-location-grid',
  imports: [LocationCard],
  templateUrl: './location-grid.component.html',
})
export class UserGrid {
  @Input() items: any[] = [];
  @Input() category: any;
}
