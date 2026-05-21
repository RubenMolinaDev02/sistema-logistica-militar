import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-item-pagination',
  imports: [CommonModule],
  templateUrl: './item-pagination.html',
  styleUrl: './item-pagination.css',
})
export class ItemPagination {
  @Input() page = 0;

  @Input() totalPages = 0;

  @Output() previous = new EventEmitter<void>();

  @Output() next = new EventEmitter<void>();
}
