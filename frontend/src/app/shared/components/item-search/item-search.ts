import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UiButtonComponent } from "../form-button-component/form-button";

export interface SearchFilter {
  field: string;
  operator: string;
  value: any;
}

@Component({
  selector: 'app-item-search',
  imports: [CommonModule, FormsModule, UiButtonComponent],
  templateUrl: './item-search.html',
  styleUrl: './item-search.css',
})
export class ItemSearch {
openCreate(arg0: any) {
throw new Error('Method not implemented.');
}
  @Output() search = new EventEmitter<SearchFilter[]>();

  text = '';
  type = '';
  searchField: 'name' | 'reference' = 'name';
  operator: 'EQ' | 'REGEX' = 'REGEX';
activeTab: any;

  onFieldChange() {

  if (this.searchField === 'name') {
    this.operator = 'REGEX';
  }

  if (this.searchField === 'reference') {
    this.operator = 'EQ';
  }
}

  onSearch() {
    const filters: SearchFilter[] = [];
    if (this.text.trim()) {
      filters.push({
        field: this.searchField,
        operator: this.operator,
        value: this.text.trim()
      });
    }

    /*if (this.type) {
      filters.push({
        field: 'type',
        operator: 'EQ',
        value: this.type
      });
    }*/

    this.search.emit(filters);
  }

  clear() {
    this.text = '';
    this.type = '';
    this.searchField = 'name';
    this.operator = 'REGEX';
    this.search.emit([]);
  }
}
