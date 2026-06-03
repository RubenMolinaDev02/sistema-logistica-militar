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
import { ActivatedRoute, Router } from '@angular/router';
import { UiButtonComponent } from "../../shared/components/form-button-component/form-button";
import { AuthService } from '../../core/services/auth.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-items',
  standalone: true,
  imports: [
    CommonModule,
    ItemGrid,
    ItemTabs,
    ItemHeader,
    ItemPagination,
    ItemSearch,
    UiButtonComponent,
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

  environment = environment;

  activeTab = 'weapons';

  tabsDisabled = false;

  tabs = [
    { key: 'weapons', label: 'Armas' },
    { key: 'ammo', label: 'Calibres' },
    { key: 'ammotype', label: 'Munición' },
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
    private route: ActivatedRoute,
    private router: Router,
    public authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.activeTab =
      this.route.snapshot.paramMap.get('category') ?? '';

    this.loadTab(this.activeTab);
  }

  loadTab(key: string, page: number = 0): void {
    
    if (this.tabsDisabled) return;

    this.tabsDisabled = true;

    this.router.navigate(['/items', key]);
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
    weapons: () => this.info.getItems(this.size, this.page, request, `/armory/weapons/search`),
    ammo: () => this.info.getItems(this.size, this.page, request, `/armory/calibers/search`),
    ammotype: () => this.info.getItems(this.size, this.page, request, `/armory/ammo/search`),
    magazines: () => this.info.getItems(this.size, this.page, request, `/armory/magazines/search`),
    helmets: () => this.info.getItems(this.size, this.page, request, `/armory/helmet/search`),
    armorVests: () => this.info.getItems(this.size, this.page, request, `/armory/vests/search`),
    plates: () => this.info.getItems(this.size, this.page, request, `/armory/plates/search`),
    nvgs: () => this.info.getItems(this.size, this.page, request, `/armory/nvg/search`),
    textile: () => this.info.getItems(this.size, this.page, request, `/armory/textile/search`),
    optics: () => this.info.getItems(this.size, this.page, request, `/armory/optics/search`),
    holsters: () => this.info.getItems(this.size, this.page, request, `/armory/holsters/search`),
    grenades: () => this.info.getItems(this.size, this.page, request, `/armory/grenades/search`),
    gasmaskfilter: () => this.info.getItems(this.size, this.page, request, `/armory/gas-mask-filter/search`),
    gasmask: () => this.info.getItems(this.size, this.page, request, `/armory/gas-mask/search`),
    bayonets: () => this.info.getItems(this.size, this.page, request, `/armory/bayonets/search`),
    barrelattachments: () => this.info.getItems(this.size, this.page, request, `/armory/barrel-attachments/search`),
    attachments: () => this.info.getItems(this.size, this.page, request, `/armory/attachments/search`),
    platforms: () => this.info.getItems(this.size, this.page, request, `/armory/platforms/search`),
    stocks: () => this.info.getItems(this.size, this.page, request, `/armory/stocks/search`),
    handguards: () => this.info.getItems(this.size, this.page, request, `/armory/handguards/search`),
    misc: () => this.info.getItems(this.size, this.page, request, `/armory/misc/search`)
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