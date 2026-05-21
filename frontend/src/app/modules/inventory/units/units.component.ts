import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryService } from '../../../core/services/api.inventory.service';
import { SerializedUnitResponse } from '../../../shared/models/models';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-units',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div>
      <div class="toolbar">
        <span class="count">Búsqueda por número de serie</span>
        <div class="search-box">
          <input [(ngModel)]="serial" placeholder="Nº de serie..." (keyup.enter)="search()" />
          <button class="btn-primary" (click)="search()">Buscar</button>
        </div>
      </div>
      <div class="result" *ngIf="unit">
        <div class="result-row"><span class="label">ID</span><span class="mono">{{ unit.id }}</span></div>
        <div class="result-row"><span class="label">Nº de serie</span><span class="mono accent">{{ unit.serialNumber }}</span></div>
        <div class="result-row"><span class="label">SKU ID</span><span class="mono">{{ unit.skuId }}</span></div>
        <div class="result-row"><span class="label">Stock ID</span><span class="mono">{{ unit.stockId || '—' }}</span></div>
        <div class="result-row"><span class="label">Batch ID</span><span class="mono dim">{{ unit.batchId }}</span></div>
      </div>
      <div class="empty" *ngIf="notFound">Unidad no encontrada</div>
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');
    :host { --accent: #00e5a0; --border: #1e2530; --surface: #111418; --text: #c8d4e0; --text-dim: #4a5568;
      --mono: 'Share Tech Mono', monospace; display: block; color: var(--text); }
    .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 28px; }
    .count { font-family: var(--mono); font-size: 12px; color: var(--text-dim); }
    .search-box { display: flex; gap: 8px; }
    .search-box input { background: var(--surface); border: 1px solid var(--border); color: var(--text); padding: 8px 14px; font-family: var(--mono); font-size: 12px; width: 240px; outline: none; }
    .search-box input:focus { border-color: var(--accent); }
    .btn-primary { padding: 8px 16px; background: var(--accent); color: #000; border: none; font-family: var(--mono); font-size: 11px; letter-spacing: 2px; cursor: pointer; font-weight: 700; }
    .result { background: var(--surface); border: 1px solid var(--border); padding: 20px; max-width: 500px; }
    .result-row { display: flex; gap: 20px; padding: 10px 0; border-bottom: 1px solid rgba(30,37,48,0.5); align-items: center; }
    .label { font-family: var(--mono); font-size: 10px; letter-spacing: 2px; color: var(--text-dim); width: 100px; flex-shrink: 0; }
    .mono { font-family: var(--mono); font-size: 13px; }
    .accent { color: var(--accent); }
    .dim { color: var(--text-dim); }
    .empty { font-family: var(--mono); font-size: 12px; color: var(--text-dim); padding: 32px 0; }
  `]
})
export class UnitsComponent {
  serial = '';
  unit: SerializedUnitResponse | null = null;
  notFound = false;
  constructor(private inventory: InventoryService) {}
  search() {
    if (!this.serial.trim()) return;
    this.notFound = false;
    this.unit = null;
    this.inventory.getUnitBySerial(this.serial).subscribe({
      next: u => this.unit = u,
      error: () => this.notFound = true
    });
  }
}
