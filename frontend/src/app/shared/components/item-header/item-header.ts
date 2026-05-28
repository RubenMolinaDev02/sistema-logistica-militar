import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-item-header',
  imports: [],
  templateUrl: './item-header.html',
  styleUrl: './item-header.css',
})
export class ItemHeader {
    @Input() title = '';
    @Input() subtitle = '';
    @Input() text = '';
}
