// items.component.ts

import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ApiInfoService } from '../../core/services/api.info.service';

import { ItemGrid } from '../../shared/components/item-grid/item-grid';
import { ItemTabs } from "../../shared/components/item-tabs/item-tabs";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { ItemPagination } from "../../shared/components/item-pagination/item-pagination";
import { ItemSearch } from "../../shared/components/item-search/item-search";
import { DynamicFormComponent } from "../../shared/components/dynamic-form-component/dynamic-form.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-items',
  standalone: true,
  imports: [
    CommonModule,
    ItemGrid,
    ItemTabs,
    ItemHeader,
    ItemPagination,
    ItemSearch
],
  templateUrl: './items.component.html'
})
export class ItemsComponent implements OnInit {


openCreate(category: string) {
  this.router.navigate(['/create/' + category])
}

  page = 0;
  size = 24;
  totalPages = 0;

  items: any[] = [];

  loading = false;

  activeTab = 'weapons';

  tabsDisabled = false;

  tabs = [
    { key: 'weapons', label: 'Armas' },
    { key: 'ammo', label: 'Calibres' },
    { key: 'ammotypes', label: 'Munición' },
    { key: 'magazines', label: 'Cargadores' },
    { key: 'helmets', label: 'Cascos' },
    { key: 'armorVests', label: 'Chalecos' },
    { key: 'plates', label: 'Placas balisticas' },
    { key: 'nvgs', label: 'Visión nocturna' },
    { key: 'textile', label: 'Uniformidad' },
    { key: 'optics', label: 'Miras' },
    { key: 'holsters', label: 'Pistoleras' },
    { key: 'grenades', label: 'Granadas' },
    { key: 'gasmaskfilter', label: 'Filtros NBQ' },
    { key: 'gasmask', label: 'Mascaras de gas' },
    { key: 'bayonets', label: 'Bayonetas' },
    { key: 'barrelattachments', label: 'Accesorios cañon' },
    { key: 'attachments', label: 'Accesorios' },
    { key: 'platforms', label: 'Plataformas de armas' },
    { key: 'stocks', label: 'Culatas' },
    { key: 'handguards', label: 'Guardamanos' },
    { key: 'misc', label: 'Misc'}
  ];

  constructor(
    private info: ApiInfoService,
    private cdr: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.loadTab('weapons');
  }

  loadTab(key: string, page: number = 0): void {
    
    if (this.tabsDisabled) return;

    this.tabsDisabled = true;

    this.activeTab = key;
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

  console.log(request)

  const map: Record<string, any> = {
    weapons: () => this.info.getItems(this.size, this.page, request, "weapons"),
    ammo: () => this.info.getItems(this.size, this.page, request, "calibers"),
    ammotypes: () => this.info.getItems(this.size, this.page, request, "ammo"),
    magazines: () => this.info.getItems(this.size, this.page, request, "magazines"),
    helmets: () => this.info.getItems(this.size, this.page, request, "helmet"),
    armorVests: () => this.info.getItems(this.size, this.page, request, "vests"),
    plates: () => this.info.getItems(this.size, this.page, request, "plates"),
    nvgs: () => this.info.getItems(this.size, this.page, request, "nvg"),
    textile: () => this.info.getItems(this.size, this.page, request, "textile"),
    optics: () => this.info.getItems(this.size, this.page, request, "optics"),
    holsters: () => this.info.getItems(this.size, this.page, request, "holsters"),
    grenades: () => this.info.getItems(this.size, this.page, request, "grenades"),
    gasmaskfilter: () => this.info.getItems(this.size, this.page, request, "gas-mask-filter"),
    gasmask: () => this.info.getItems(this.size, this.page, request, "gas-mask"),
    bayonets: () => this.info.getItems(this.size, this.page, request, "bayonets"),
    barrelattachments: () => this.info.getItems(this.size, this.page, request, "barrel-attachments"),
    attachments: () => this.info.getItems(this.size, this.page, request, "attachments"),
    platforms: () => this.info.getItems(this.size, this.page, request, "platforms"),
    stocks: () => this.info.getItems(this.size, this.page, request, "stocks"),
    handguards: () => this.info.getItems(this.size, this.page, request, "handguards"),
    misc: () => this.info.getItems(this.size, this.page, request, "misc")
  };

  const req = map[this.activeTab];

  if (!req) {
    this.loading = false;
    return;
  }

  req().subscribe({
    next: (res: any) => {

      this.items = [...(res.content ?? [])];
      this.totalPages = res.totalPages ?? 0;
      console.log(res.content)

      this.loading = false;
      this.tabsDisabled = false;
      this.cdr.detectChanges();
    },
    error: () => {
      this.loading = false;
      
      this.tabsDisabled = false;
      this.cdr.detectChanges();
    }
  });
}


}