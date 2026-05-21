import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LocationService } from '../../core/services/api.location.service';
import { LocationResponse } from '../../shared/models/models';

@Component({
  selector: 'app-locations',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="page">
      <header class="page-header">
        <div>
          <div class="page-label">LOCATION SERVICE</div>
          <h1 class="page-title">Localizaciones</h1>
        </div>
        <button class="btn-primary">+ Nueva localización</button>
      </header>

      <div class="loading" *ngIf="loading">Cargando...</div>

      <div class="loc-grid" *ngIf="!loading">
        <div class="loc-card" *ngFor="let loc of locations">
          <div class="loc-type">{{ loc.type }}</div>
          <div class="loc-name">{{ loc.name }}</div>
          <div class="loc-ref mono">{{ loc.reference }}</div>
          <div class="loc-meta" *ngIf="loc.country">
            <span class="dim">{{ loc.country }}</span>
          </div>
          <div class="loc-status" *ngIf="loc.status">
            <span class="badge">{{ loc.status }}</span>
          </div>
        </div>
        <div class="empty" *ngIf="locations.length === 0">Sin localizaciones registradas</div>
      </div>
    </div>
  `,
  styles: [`
    @import url('https://fonts.googleapis.com/css2?family=Share+Tech+Mono&family=Barlow:wght@300;400;600;700&display=swap');
    :host { --accent: #00e5a0; --accent2: #0088ff; --border: #1e2530; --surface: #111418; --surface2: #181c22;
      --text: #c8d4e0; --text-dim: #4a5568; --mono: 'Share Tech Mono', monospace; --sans: 'Barlow', sans-serif;
      display: block; font-family: var(--sans); color: var(--text); }
    .page { padding: 32px; }
    .page-header { display: flex; justify-content: space-between; align-items: flex-end; margin-bottom: 28px; }
    .page-label { font-family: var(--mono); font-size: 10px; letter-spacing: 3px; color: var(--accent); margin-bottom: 4px; }
    .page-title { font-size: 28px; font-weight: 700; }
    .btn-primary { padding: 10px 20px; background: var(--accent); color: #000; border: none; font-family: var(--mono); font-size: 12px; letter-spacing: 2px; cursor: pointer; font-weight: 700; }
    .loc-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(240px, 1fr)); gap: 16px; }
    .loc-card { background: var(--surface); border: 1px solid var(--border); padding: 20px; position: relative; transition: border-color 0.15s; }
    .loc-card:hover { border-color: rgba(0,229,160,0.3); }
    .loc-type { font-family: var(--mono); font-size: 9px; letter-spacing: 3px; color: var(--accent); margin-bottom: 8px; }
    .loc-name { font-size: 16px; font-weight: 700; margin-bottom: 6px; }
    .loc-ref { font-size: 11px; color: var(--text-dim); margin-bottom: 10px; }
    .loc-meta { font-size: 12px; color: var(--text-dim); margin-bottom: 6px; }
    .badge { font-family: var(--mono); font-size: 10px; padding: 2px 8px; border: 1px solid rgba(0,136,255,0.4); color: var(--accent2); }
    .mono { font-family: var(--mono); }
    .dim { color: var(--text-dim); }
    .empty { grid-column: 1/-1; text-align: center; color: var(--text-dim); font-family: var(--mono); font-size: 12px; padding: 40px 0; }
    .loading { font-family: var(--mono); font-size: 12px; color: var(--text-dim); padding: 16px 0; }
  `]
})
export class LocationsComponent implements OnInit {
  locations: LocationResponse[] = [];
  loading = true;
  constructor(private locationSvc: LocationService) {}
  ngOnInit() {
    this.locationSvc.getAll().subscribe({
      next: d => { this.locations = d; this.loading = false; },
      error: () => this.loading = false
    });
  }
}
