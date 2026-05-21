import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryService } from '../../../core/services/api.inventory.service';
import { SkuResponse } from '../../../shared/models/models';

@Component({
  selector: 'app-skus',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div>
      <div class="toolbar">
        <span class="count">{{ skus.length }} SKUs</span>
        <button class="btn-primary">+ Nuevo SKU</button>
      </div>
      <div class="loading" *ngIf="loading">Cargando...</div>
      <table class="data-table" *ngIf="!loading">
        <thead>
          <tr><th>REFERENCIA</th><th>ITEM ID</th><th>TIPO</th><th>ATRIBUTOS</th><th>ESTADO</th><th></th></tr>
        </thead>
        <tbody>
          <tr *ngFor="let s of skus">
            <td class="mono">{{ s.reference }}</td>
            <td class="mono dim">{{ s.itemId }}</td>
            <td><span class="tag">{{ s.itemType }}</span></td>
            <td class="dim small">{{ s.attributes | json }}</td>
            <td><span class="dot" [class.on]="s.active"></span> {{ s.active ? 'Activo' : 'Inactivo' }}</td>
            <td><button class="btn-sm danger" (click)="delete(s.id)">Eliminar</button></td>
          </tr>
          <tr *ngIf="skus.length === 0"><td colspan="6" class="empty">Sin SKUs registrados</td></tr>
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');
    :host { --accent: #00e5a0; --border: #1e2530; --danger: #ff3b3b; --text: #c8d4e0; --text-dim: #4a5568;
      --mono: 'Share Tech Mono', monospace; display: block; color: var(--text); }
    .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
    .count { font-family: var(--mono); font-size: 12px; color: var(--text-dim); }
    .btn-primary { padding: 8px 16px; background: var(--accent); color: #000; border: none; font-family: var(--mono); font-size: 11px; letter-spacing: 2px; cursor: pointer; font-weight: 700; }
    .data-table { width: 100%; border-collapse: collapse; font-size: 13px; }
    .data-table th { text-align: left; font-family: var(--mono); font-size: 10px; letter-spacing: 2px; color: var(--text-dim); padding: 0 12px 10px 0; border-bottom: 1px solid var(--border); }
    .data-table td { padding: 11px 12px 11px 0; border-bottom: 1px solid rgba(30,37,48,0.4); vertical-align: middle; }
    .mono { font-family: var(--mono); font-size: 12px; }
    .dim { color: var(--text-dim); }
    .small { font-size: 11px; max-width: 200px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }
    .tag { font-family: var(--mono); font-size: 10px; padding: 2px 8px; background: rgba(0,229,160,0.08); color: var(--accent); border: 1px solid rgba(0,229,160,0.2); }
    .dot { display: inline-block; width: 7px; height: 7px; border-radius: 50%; background: var(--border); margin-right: 6px; }
    .dot.on { background: var(--accent); box-shadow: 0 0 6px var(--accent); }
    .btn-sm { padding: 4px 10px; background: transparent; border: 1px solid var(--border); color: var(--text-dim); font-family: var(--mono); font-size: 10px; cursor: pointer; }
    .btn-sm.danger:hover { border-color: var(--danger); color: var(--danger); }
    .empty { text-align: center; color: var(--text-dim); font-family: var(--mono); font-size: 12px; padding: 32px 0; }
    .loading { font-family: var(--mono); font-size: 12px; color: var(--text-dim); padding: 16px 0; }
  `]
})
export class SkusComponent implements OnInit {
  skus: SkuResponse[] = [];
  loading = true;
  constructor(private inventory: InventoryService) {}
  ngOnInit() {
    this.inventory.getAllSkus().subscribe({
      next: d => { this.skus = d; this.loading = false; },
      error: () => this.loading = false
    });
  }
  delete(id: string) {
    if (confirm('¿Eliminar este SKU?')) {
      this.inventory.deleteSku(id).subscribe(() => this.ngOnInit());
    }
  }
}
