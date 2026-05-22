export interface FormFieldDto {
  key: string;
  label: string;
  type: FieldType;

  value: any;

  required?: boolean;
  disabled?: boolean;

  options?: any[]; // selects
  unit?: string;
  endpoint?: string;
}

export type FieldType =
  | 'TEXT'
  | 'NUMBER'
  | 'BOOLEAN'
  | 'SELECT'
  | 'ARRAY'
  | 'OBJECT'
  | 'MULTISELECT'
  | 'SELECT_REMOTE';