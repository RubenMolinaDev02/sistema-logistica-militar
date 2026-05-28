import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemHeader } from "../../shared/components/item-header/item-header";

@Component({
  selector: 'app-dashboard-kpi',
  standalone: true,
  imports: [CommonModule, ItemHeader],
  templateUrl: './dashboard.component.html'
})
export class DashboardKpiComponent {

  @Input() label!: string;

  @Input() value!: string;

  @Input() sub!: string;

  @Input() accent = false;
}