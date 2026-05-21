import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InventoryService } from '../../../core/services/api.inventory.service';
import { StockResponse } from '../../../shared/models/models';

@Component({
  selector: 'app-stock',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div>
      <div class="toolbar">
        <span class="count">{{ stock.length }} registros de stock</span>
        <button class="btn-primary">+ Nuevo stock</button>
      </div>
      <div class="loading" *ngIf="loading">Cargando...</div>
      <table class="data-table" *ngIf="!loading">
        <thead>
          <tr><th>REFERENCIA</th><th>SKU ID</th><th>LOCALIZACIÓN</th><th>UNIDADES</th><th>ACCIONES</th></tr>
        </thead>
        <tbody>
          <tr *ngFor="let s of stock" [class.low]="s.units < 5">
            <td class="mono">{{ s.reference }}</td>
            <td class="mono dim">{{ s.skuId }}</td>
            <td class="dim small">{{ s.locationId }}</td>
            <td>
              <span class="units" [class.critical]="s.units < 5" [class.warn]="s.units < 20 && s.units >= 5">
                {{ s.units }}
              </span>
            </td>
            <td>
              <button class="btn-sm" (click)="increase(s.id)">+1</button>
              <button class="btn-sm" (click)="decrease(s.id)" [disabled]="s.units === 0">-1</button>
            </td>
          </tr>
          <tr *ngIf="stock.length === 0"><td colspan="5" class="empty">Sin registros de stock</td></tr>
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');
    :host { --accent: #00e5a0; --border: #1e2530; --danger: #ff3b3b; --warn: #ffaa00; --text: #c8d4e0; --text-dim: #4a5568;
      --mono: 'Share Tech Mono', monospace; display: block; color: var(--text); }
    .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
    .count { font-family: var(--mono); font-size: 12px; color: var(--text-dim); }
    .btn-primary { padding: 8px 16px; background: var(--accent); color: #000; border: none; font-family: var(--mono); font-size: 11px; letter-spacing: 2px; cursor: pointer; font-weight: 700; }
    .data-table { width: 100%; border-collapse: collapse; font-size: 13px; }
    .data-table th { text-align: left; font-family: var(--mono); font-size: 10px; letter-spacing: 2px; color: var(--text-dim); padding: 0 12px 10px 0; border-bottom: 1px solid var(--border); }
    .data-table td { padding: 11px 12px 11px 0; border-bottom: 1px solid rgba(30,37,48,0.4); }
    .data-table tr.low td { background: rgba(255,59,59,0.03); }
    .mono { font-family: var(--mono); font-size: 12px; }
    .dim { color: var(--text-dim); }
    .small { font-size: 12px; }
    .units { font-family: var(--mono); font-size: 16px; font-weight: 700; }
    .units.critical { color: var(--danger); }
    .units.warn { color: var(--warn); }
    .btn-sm { padding: 4px 10px; background: transparent; border: 1px solid var(--border); color: var(--text-dim); font-family: var(--mono); font-size: 12px; cursor: pointer; margin-right: 4px; transition: all 0.15s; }
    .btn-sm:hover:not([disabled]) { border-color: var(--accent); color: var(--accent); }
    .btn-sm[disabled] { opacity: 0.3; cursor: not-allowed; }
    .empty { text-align: center; color: var(--text-dim); font-family: var(--mono); font-size: 12px; padding: 32px 0; }
    .loading { font-family: var(--mono); font-size: 12px; color: var(--text-dim); padding: 16px 0; }
  `]
})
export class StockComponent implements OnInit {
  stock: StockResponse[] = [];
  loading = true;
  constructor(private inventory: InventoryService) {}
  ngOnInit() {
    this.inventory.getAllStock().subscribe({
      next: d => { this.stock = d; this.loading = false; },
      error: () => this.loading = false
    });
  }
  increase(id: string) {
    this.inventory.increaseStock(id, 1).subscribe(() => this.ngOnInit());
  }
  decrease(id: string) {
    this.inventory.decreaseStock(id, 1).subscribe(() => this.ngOnInit());
  }
}
