import { CommonModule } from "@angular/common";
import { Component, EventEmitter, Input, Output } from "@angular/core";
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

  @Output() submit = new EventEmitter<Record<string, any>>();

    submitForm() {
      const values: Record<string, any> = {};

      for (const section of this.model.sections) {
        for (const field of section.fields) {
          values[field.key] = field.value;
        }
      }

      this.submit.emit(values);
    }

  get formValues(): Record<string, any> {

  const values: Record<string, any> = {};

  for (const section of this.model.sections) {

    for (const field of section.fields) {

      values[field.key] = field.value;
    }
  }

  return values;
}

}