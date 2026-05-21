import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryService } from '../../core/services/api.inventory.service';
import { ShipmentResponse } from '../../shared/models/models';

@Component({
  selector: 'app-shipments',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="page">
      <header class="page-header">
        <div>
          <div class="page-label">INVENTORY SERVICE</div>
          <h1 class="page-title">Envíos</h1>
        </div>
        <button class="btn-primary">+ Nuevo envío</button>
      </header>

      <div class="filters">
        <button class="filter-btn" [class.active]="filter === 'ALL'" (click)="filter='ALL'">Todos</button>
        <button class="filter-btn" [class.active]="filter === 'CREATED'" (click)="filter='CREATED'">Creados</button>
        <button class="filter-btn" [class.active]="filter === 'IN_PROGRESS'" (click)="filter='IN_PROGRESS'">En tránsito</button>
        <button class="filter-btn" [class.active]="filter === 'COMPLETED'" (click)="filter='COMPLETED'">Completados</button>
        <button class="filter-btn" [class.active]="filter === 'FAILED'" (click)="filter='FAILED'">Fallidos</button>
      </div>

      <div class="loading" *ngIf="loading">Cargando...</div>

      <table class="data-table" *ngIf="!loading">
        <thead>
          <tr>
            <th>REFERENCIA</th>
            <th>ORIGEN</th>
            <th>DESTINO</th>
            <th>ESTADO</th>
            <th>CREACIÓN</th>
            <th>LLEGADA</th>
            <th>ACCIONES</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let s of filtered">
            <td class="mono">{{ s.reference }}</td>
            <td class="dim small">{{ s.supplierLocationId }}</td>
            <td class="dim small">{{ s.destinyLocationId }}</td>
            <td><span class="badge" [class]="s.status.toLowerCase()">{{ s.status }}</span></td>
            <td class="dim small">{{ s.creationDate | date:'dd/MM/yyyy' }}</td>
            <td class="dim small">{{ s.arrivalDate ? (s.arrivalDate | date:'dd/MM/yyyy') : '—' }}</td>
            <td>
              <button class="btn-sm" *ngIf="s.status === 'IN_PROGRESS'" (click)="complete(s.id)">Completar</button>
              <button class="btn-sm danger" *ngIf="s.status === 'CREATED'" (click)="delete(s.id)">Eliminar</button>
            </td>
          </tr>
          <tr *ngIf="filtered.length === 0">
            <td colspan="7" class="empty">Sin resultados</td>
          </tr>
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');
    :host {
      --bg: #0a0c0f; --surface: #111418; --surface2: #181c22; --border: #1e2530;
      --accent: #00e5a0; --accent2: #0088ff; --danger: #ff3b3b; --warn: #ffaa00;
      --text: #c8d4e0; --text-dim: #4a5568;
      --mono: 'Share Tech Mono', monospace; --sans: 'Barlow', sans-serif;
      display: block; font-family: var(--sans); color: var(--text);
    }
    .page { padding: 32px; }
    .page-header { display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 28px; }
    .page-label { font-family: var(--mono); font-size: 10px; letter-spacing: 3px; color: var(--accent); margin-bottom: 4px; }
    .page-title { font-size: 28px; font-weight: 700; }
    .btn-primary { padding: 10px 20px; background: var(--accent); color: #000; border: none; font-family: var(--mono); font-size: 12px; letter-spacing: 2px; cursor: pointer; font-weight: 700; }
    .filters { display: flex; gap: 8px; margin-bottom: 24px; }
    .filter-btn { padding: 6px 14px; background: transparent; border: 1px solid var(--border); color: var(--text-dim); font-family: var(--mono); font-size: 10px; letter-spacing: 2px; cursor: pointer; transition: all 0.15s; }
    .filter-btn.active, .filter-btn:hover { border-color: var(--accent); color: var(--accent); }
    .data-table { width: 100%; border-collapse: collapse; font-size: 13px; }
    .data-table th { text-align: left; font-family: var(--mono); font-size: 10px; letter-spacing: 2px; color: var(--text-dim); padding: 0 12px 10px 0; border-bottom: 1px solid var(--border); }
    .data-table td { padding: 12px 12px 12px 0; border-bottom: 1px solid rgba(30,37,48,0.5); }
    .mono { font-family: var(--mono); font-size: 12px; }
    .dim { color: var(--text-dim); }
    .small { font-size: 12px; }
    .badge { font-family: var(--mono); font-size: 10px; letter-spacing: 1px; padding: 2px 8px; border: 1px solid currentColor; }
    .badge.created { color: var(--accent2); }
    .badge.in_progress { color: var(--warn); }
    .badge.arrived, .badge.completed { color: var(--accent); }
    .badge.failed, .badge.cancelled, .badge.lost { color: var(--danger); }
    .btn-sm { padding: 4px 10px; background: transparent; border: 1px solid var(--border); color: var(--text-dim); font-family: var(--mono); font-size: 10px; cursor: pointer; transition: all 0.15s; margin-right: 6px; }
    .btn-sm:hover { border-color: var(--accent); color: var(--accent); }
    .btn-sm.danger:hover { border-color: var(--danger); color: var(--danger); }
    .empty { text-align: center; color: var(--text-dim); font-family: var(--mono); font-size: 12px; padding: 32px 0; }
    .loading { font-family: var(--mono); font-size: 12px; color: var(--text-dim); padding: 32px 0; }
  `]
})
export class ShipmentsComponent implements OnInit {
  shipments: ShipmentResponse[] = [];
  loading = true;
  filter = 'ALL';

  get filtered() {
    return this.filter === 'ALL'
      ? this.shipments
      : this.shipments.filter(s => s.status === this.filter);
  }

  constructor(private inventory: InventoryService) {}

  ngOnInit() {
    this.inventory.getAllShipments().subscribe({
      next: data => { this.shipments = data; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  complete(id: string) {
    this.inventory.completeShipment(id).subscribe(() => this.ngOnInit());
  }

  delete(id: string) {
    if (confirm('¿Eliminar este envío?')) {
      this.inventory.deleteShipment(id).subscribe(() => this.ngOnInit());
    }
  }
}
