// items.component.ts

import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ApiInfoService } from '../../core/services/api.info.service';

import { ItemTabs } from "../../shared/components/item-tabs/item-tabs";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { ItemPagination } from "../../shared/components/item-pagination/item-pagination";
import { ItemSearch } from "../../shared/components/item-search/item-search";
import { ActivatedRoute, Router } from '@angular/router';
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { AuthService } from '../../core/services/auth.service';
import { UserGrid } from "../../shared/components/location-grid/location-grid.component";

@Component({
  selector: 'app-locations',
  standalone: true,
  imports: [
    CommonModule,
    ItemTabs,
    ItemHeader,
    ItemPagination,
    ItemSearch,
    UiButtonComponent,
    UserGrid
],
  templateUrl: './location.page.html'
})
export class LocationsComponent implements OnInit {


openCreate() {
  this.router.navigate(['/locations/create'])
}


  page = 0;
  size = 24;
  totalPages = 0;

  items: any[] = [];

  loading = false;

  constructor(
    private info: ApiInfoService,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private router: Router,
    public authService: AuthService
  ) {}

  ngOnInit(): void {

    this.loadTab();
  }

  loadTab(page: number = 0): void {
    this.page = page;
    this.loadData();
}
currentFilters: any[] = [];

applyFilters(filters: any[]): void {
  this.currentFilters = filters;
  this.page = 0;

  this.loadData();
}

loadData(): void {

  this.loading = true;

  const filters = this.currentFilters;

  const request = {
      filters: filters,
      sortBy: 'name',
      direction: 'ASC'
  }

  this.info.getItems(this.size, this.page, request, `/organization/locations/search`).subscribe({
    next: (res: any) => {

      this.items = [...(res.content ?? [])];
      this.totalPages = res.totalPages ?? 0;
      console.log(res.content)

      this.loading = false;
      this.cdr.detectChanges();
    },
    error: () => {
      this.loading = false;
      
      this.cdr.detectChanges();
    }
  });
}


}