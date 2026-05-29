import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { ItemHeader } from "../../shared/components/item-header/item-header";

import { InventoryService } from '../../core/services/api.inventory.service';
import { LocationService } from '../../core/services/api.location.service';

import { ShipmentResponse, StockResponse } from '../../shared/models/models';
import { DashboardKpiComponent } from "./dashboard-kpi-component/dashboard-kpi.component";
import { DashboardPanelComponent } from "./dashboard-panel-component/dashboard.panel.component";


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    DashboardKpiComponent,
    DashboardPanelComponent,
    ItemHeader
],
  templateUrl: './dashboard.page.html'
})
export class DashboardComponent implements OnInit {

  now = new Date();

  shipments: ShipmentResponse[] = [];

  lowStock: StockResponse[] = [];

  Math = Math;

  kpis = [
    { label: 'SKUs activos', value: '—', sub: 'referencias de inventario', accent: false },
    { label: 'Stock total', value: '—', sub: 'unidades en almacén', accent: true },
    { label: 'Envíos activos', value: '—', sub: 'en tránsito', accent: false },
    { label: 'Unidades serializadas', value: '—', sub: 'con nº de serie', accent: false },
    { label: 'Localizaciones', value: '—', sub: 'bases y almacenes', accent: false },
    { label: 'Alertas stock', value: '—', sub: 'bajo mínimos', accent: false }
  ];

  constructor(
    private inventory: InventoryService,
    private locationSvc: LocationService
  ) {}

  ngOnInit() {

    forkJoin({
      skus: this.inventory.getAllSkus(),
      stock: this.inventory.getAllStock(),
      shipments: this.inventory.getAllShipments(),
      locations: this.locationSvc.getAll()
    }).subscribe({

      next: ({ skus, stock, shipments, locations }) => {

        this.shipments = shipments.sort((a, b) =>
          b.creationDate.localeCompare(a.creationDate)
        );

        this.lowStock = stock
          .filter(s => s.units < 10)
          .sort((a, b) => a.units - b.units);

        const activeShipments =
          shipments.filter(s =>
            s.status === 'CREATED' ||
            s.status === 'IN_PROGRESS'
          ).length;

        const totalUnits =
          stock.reduce((acc, s) => acc + s.units, 0);

        this.kpis[0].value = skus.length.toString();
        this.kpis[1].value = totalUnits.toLocaleString();
        this.kpis[2].value = activeShipments.toString();
        this.kpis[4].value = locations.length.toString();
        this.kpis[5].value = this.lowStock.length.toString();

      }

    });

  }

}