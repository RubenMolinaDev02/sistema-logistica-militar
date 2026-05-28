import { CommonModule } from '@angular/common';
import {
  Component,
  EventEmitter,
  Input,
  Output
} from '@angular/core';

@Component({
  selector: 'app-ui-button',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './form-button.html'
})
export class UiButtonComponent {

  @Input() label = 'BUTTON';

  @Input() variant: 'primary' | 'secondary' | 'danger' = 'primary';

  @Input() type: 'button' | 'submit' = 'button';

  @Input() disabled = false;

  @Output() pressed = new EventEmitter<void>();

  onClick() {
    if (!this.disabled) {
      this.pressed.emit();
    }
  }
}