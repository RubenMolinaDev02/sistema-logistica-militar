import { FormFieldDto } from "./form-dto/form-field.dto";

export interface FormSectionDto {
  title: string;
  fields: FormFieldDto[];
}

export interface ItemFormModel {
  sections: FormSectionDto[];
}