import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inventory',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterOutlet],
  template: `
    <div class="page">
      <header class="page-header">
        <div class="page-label">INVENTORY SERVICE</div>
        <h1 class="page-title">Inventario</h1>
      </header>
      <nav class="sub-nav">
        <a routerLink="skus" routerLinkActive="active">SKUs</a>
        <a routerLink="stock" routerLinkActive="active">Stock</a>
        <a routerLink="units" routerLinkActive="active">Unidades serializadas</a>
      </nav>
      <router-outlet />
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');
    :host { --accent: #00e5a0; --border: #1e2530; --text: #c8d4e0; --text-dim: #4a5568;
      --mono: 'Share Tech Mono', monospace; --sans: 'Barlow', sans-serif;
      display: block; font-family: var(--sans); color: var(--text); }
    .page { padding: 32px; }
    .page-header { margin-bottom: 20px; }
    .page-label { font-family: var(--mono); font-size: 10px; letter-spacing: 3px; color: var(--accent); margin-bottom: 4px; }
    .page-title { font-size: 28px; font-weight: 700; }
    .sub-nav { display: flex; gap: 0; margin-bottom: 28px; border-bottom: 1px solid var(--border); }
    .sub-nav a { padding: 10px 20px; text-decoration: none; color: var(--text-dim); font-size: 13px; font-weight: 600; letter-spacing: 1px; text-transform: uppercase; border-bottom: 2px solid transparent; transition: all 0.15s; font-family: var(--mono); font-size: 11px; }
    .sub-nav a.active, .sub-nav a:hover { color: var(--accent); border-bottom-color: var(--accent); }
  `]
})
export class InventoryComponent {}
