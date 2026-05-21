// items.component.ts

import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InformationService } from '../../core/services/api.info.service';
import { WeaponResponse } from '../../shared/models/weapon/weaponResponse';

import { ItemCard } from '../../shared/components/item-card/item-card';
import { ItemGrid } from '../../shared/components/item-grid/item-grid';
import { ItemTabs } from "../../shared/components/item-tabs/item-tabs";
import { ItemHeader } from "../../shared/components/item-header/item-header";
import { ItemPagination } from "../../shared/components/item-pagination/item-pagination";
import { BaseResponse } from '../../shared/models/base/baseResponse';
import { BaseModel } from '../../shared/models/base/baseModel';
import { ItemSearch } from "../../shared/components/item-search/item-search";


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

  page = 0;
  size = 24;
  totalPages = 0;

  items: any[] = [];

  loading = false;

  activeTab = 'weapons';

  tabs = [
    { key: 'weapons', label: 'Armas' },
    { key: 'ammo', label: 'Munición' },
    { key: 'magazines', label: 'Cargadores' },
    { key: 'helmets', label: 'Cascos' },
    { key: 'armorVests', label: 'Chalecos' },
    { key: 'nvgs', label: 'Visión nocturna' },
    { key: 'misc', label: 'Misc'}
  ];

  constructor(
    private info: InformationService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.loadTab('weapons');
  }

  loadTab(key: string, page: number = 0): void {
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
    weapons: () => this.info.getWeapons(this.size, this.page, request),
    ammo: () => this.info.getAmmo(),
    magazines: () => this.info.getMagazines(),
    helmets: () => this.info.getHelmets(),
    armorVests: () => this.info.getArmorVests(),
    nvgs: () => this.info.getNvgs(),
    misc: () => this.info.getMiscItems(this.size, this.page, request)
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
      this.cdr.detectChanges();
    },
    error: () => {
      this.loading = false;
      this.cdr.detectChanges();
    }
  });
}
}