import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { ItemFormModel } from "../../models/form-dto/form-structure.dto";
import { DynamicFieldComponent } from "../dynamic-field-component/dynamic-field.component";

@Component({
  selector: 'app-dynamic-form',
  standalone: true,
  imports: [CommonModule, DynamicFieldComponent],
  templateUrl: './dynamic-form.component.html'
})
export class DynamicFormComponent {

  @Input() model!: ItemFormModel;

}