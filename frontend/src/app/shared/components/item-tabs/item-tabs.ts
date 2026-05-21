import { Component, EventEmitter, Input, Output } from '@angular/core';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-item-tabs',
  imports: [CommonModule],
  templateUrl: './item-tabs.html',
  styleUrl: './item-tabs.css',
})
export class ItemTabs {
  @Input() tabs: any[] = [];

  @Input() activeTab = '';

  @Output() change = new EventEmitter<string>();

  selectTab(key: string): void {

    this.change.emit(key);
  }
}
