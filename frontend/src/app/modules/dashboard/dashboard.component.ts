import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { InventoryService} from '../../core/services/api.inventory.service';
import { LocationService} from '../../core/services/api.location.service';
import { ShipmentResponse, StockResponse } from '../../shared/models/models';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  template: `
    <div class="dashboard">
      <header class="page-header">
        <div>
          <div class="page-label">SISTEMA DE LOGÍSTICA MILITAR</div>
          <h1 class="page-title">Dashboard</h1>
        </div>
        <div class="header-time">{{ now | date:'dd MMM yyyy · HH:mm' }}</div>
      </header>

      <section class="kpi-grid">
        <div class="kpi-card" *ngFor="let kpi of kpis">
          <div class="kpi-label">{{ kpi.label }}</div>
          <div class="kpi-value" [class.accent]="kpi.accent">{{ kpi.value }}</div>
          <div class="kpi-sub">{{ kpi.sub }}</div>
        </div>
      </section>

      <div class="content-grid">
        <section class="panel">
          <div class="panel-header">
            <span class="panel-title">ENVÍOS RECIENTES</span>
            <a routerLink="/shipments" class="panel-link">Ver todos →</a>
          </div>
          <div class="panel-body">
            <div class="empty" *ngIf="shipments.length === 0">Sin datos</div>
            <table class="data-table" *ngIf="shipments.length > 0">
              <thead>
                <tr>
                  <th>REFERENCIA</th>
                  <th>ESTADO</th>
                  <th>FECHA</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let s of shipments.slice(0, 8)">
                  <td class="mono">{{ s.reference }}</td>
                  <td><span class="badge" [class]="s.status.toLowerCase()">{{ s.status }}</span></td>
                  <td class="dim">{{ s.creationDate | date:'dd/MM/yy' }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>

        <section class="panel">
          <div class="panel-header">
            <span class="panel-title">STOCK CRÍTICO</span>
            <a routerLink="/inventory/stock" class="panel-link">Ver stock →</a>
          </div>
          <div class="panel-body">
            <div class="empty" *ngIf="lowStock.length === 0">Sin alertas de stock</div>
            <div class="stock-item" *ngFor="let s of lowStock">
              <div class="stock-ref mono">{{ s.reference }}</div>
              <div class="stock-bar">
                <div class="stock-fill" [style.width.%]="Math.min(s.units * 5, 100)"
                     [class.critical]="s.units < 5"></div>
              </div>
              <div class="stock-units" [class.critical]="s.units < 5">{{ s.units }} uds</div>
            </div>
          </div>
        </section>
      </div>
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');

    :host {
      --bg: #0a0c0f;
      --surface: #111418;
      --surface2: #181c22;
      --border: #1e2530;
      --accent: #00e5a0;
      --accent2: #0088ff;
      --danger: #ff3b3b;
      --warn: #ffaa00;
      --text: #c8d4e0;
      --text-dim: #4a5568;
      --mono: 'Share Tech Mono', monospace;
      --sans: 'Barlow', sans-serif;
      display: block;
      font-family: var(--sans);
      color: var(--text);
    }

    .dashboard { padding: 32px; max-width: 1400px; }

    .page-header {
      display: flex;
      justify-content: space-between;
      align-items: flex-end;
      margin-bottom: 32px;
    }

    .page-label {
      font-family: var(--mono);
      font-size: 10px;
      letter-spacing: 3px;
      color: var(--accent);
      margin-bottom: 4px;
    }

    .page-title {
      font-size: 28px;
      font-weight: 700;
      letter-spacing: -0.5px;
    }

    .header-time {
      font-family: var(--mono);
      font-size: 12px;
      color: var(--text-dim);
    }

    .kpi-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
      gap: 16px;
      margin-bottom: 32px;
    }

    .kpi-card {
      background: var(--surface);
      border: 1px solid var(--border);
      padding: 20px;
      position: relative;
      overflow: hidden;
    }

    .kpi-card::before {
      content: '';
      position: absolute;
      top: 0; left: 0; right: 0;
      height: 2px;
      background: var(--accent);
      opacity: 0.3;
    }

    .kpi-label {
      font-size: 10px;
      letter-spacing: 2px;
      color: var(--text-dim);
      text-transform: uppercase;
      font-family: var(--mono);
      margin-bottom: 10px;
    }

    .kpi-value {
      font-size: 36px;
      font-weight: 700;
      line-height: 1;
      margin-bottom: 6px;
    }

    .kpi-value.accent { color: var(--accent); }

    .kpi-sub {
      font-size: 11px;
      color: var(--text-dim);
    }

    .content-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 20px;
    }

    .panel {
      background: var(--surface);
      border: 1px solid var(--border);
    }

    .panel-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 14px 20px;
      border-bottom: 1px solid var(--border);
    }

    .panel-title {
      font-family: var(--mono);
      font-size: 11px;
      letter-spacing: 2px;
      color: var(--text-dim);
    }

    .panel-link {
      font-size: 12px;
      color: var(--accent);
      text-decoration: none;
    }

    .panel-body { padding: 16px 20px; }

    .empty {
      font-family: var(--mono);
      font-size: 12px;
      color: var(--text-dim);
      text-align: center;
      padding: 24px 0;
    }

    .data-table {
      width: 100%;
      border-collapse: collapse;
      font-size: 13px;
    }

    .data-table th {
      text-align: left;
      font-family: var(--mono);
      font-size: 10px;
      letter-spacing: 2px;
      color: var(--text-dim);
      padding: 0 0 10px;
      border-bottom: 1px solid var(--border);
    }

    .data-table td {
      padding: 9px 0;
      border-bottom: 1px solid rgba(30,37,48,0.5);
    }

    .mono { font-family: var(--mono); font-size: 12px; }
    .dim { color: var(--text-dim); }

    .badge {
      font-family: var(--mono);
      font-size: 10px;
      letter-spacing: 1px;
      padding: 2px 8px;
      border: 1px solid currentColor;
    }

    .badge.created { color: var(--accent2); }
    .badge.in_progress { color: var(--warn); }
    .badge.arrived, .badge.completed { color: var(--accent); }
    .badge.failed, .badge.cancelled, .badge.lost { color: var(--danger); }

    .stock-item {
      display: flex;
      align-items: center;
      gap: 12px;
      padding: 8px 0;
      border-bottom: 1px solid rgba(30,37,48,0.5);
    }

    .stock-ref { font-size: 12px; flex: 1; min-width: 0; overflow: hidden; text-overflow: ellipsis; }

    .stock-bar {
      width: 80px;
      height: 4px;
      background: var(--border);
    }

    .stock-fill {
      height: 100%;
      background: var(--accent);
      transition: width 0.3s;
    }

    .stock-fill.critical { background: var(--danger); }

    .stock-units { font-family: var(--mono); font-size: 12px; width: 52px; text-align: right; }
    .stock-units.critical { color: var(--danger); }
  `]
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
          b.creationDate.localeCompare(a.creationDate));

        this.lowStock = stock.filter(s => s.units < 10)
          .sort((a, b) => a.units - b.units);

        const activeShipments = shipments.filter(s =>
          s.status === 'CREATED' || s.status === 'IN_PROGRESS').length;

        const totalUnits = stock.reduce((acc, s) => acc + s.units, 0);

        this.kpis[0].value = skus.length.toString();
        this.kpis[1].value = totalUnits.toLocaleString();
        this.kpis[2].value = activeShipments.toString();
        this.kpis[4].value = locations.length.toString();
        this.kpis[5].value = this.lowStock.length.toString();
      }
    });
  }
}
