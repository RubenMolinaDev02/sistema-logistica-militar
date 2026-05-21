import { Component, Input } from '@angular/core';
import { ItemCard } from "../item-card/item-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-item-grid',
  imports: [ItemCard, CommonModule],
  templateUrl: './item-grid.html',
  styleUrl: './item-grid.css',
})
export class ItemGrid {
  @Input() items: any[] = [];
  @Input() category: any;
}
