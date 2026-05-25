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
  nullable?: boolean;

  dependsOn?: string[];
}

export type FieldType =
  | 'TEXT'
  | 'NUMBER'
  | 'BOOLEAN'
  | 'SELECT'
  | 'ARRAY'
  | 'OBJECT'
  | 'MULTISELECT'
  | 'IMAGE_UPLOAD'
  | 'SELECT_REMOTE_DEPENDENT'
  | 'SELECT_REMOTE';