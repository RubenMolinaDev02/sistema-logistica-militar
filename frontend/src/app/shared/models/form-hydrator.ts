import { ItemFormModel } from '../models/form-dto/form-structure.dto';

export function hydrateForm(model: ItemFormModel, data: any): ItemFormModel {
  return {
    sections: model.sections.map(section => ({
      ...section,
      fields: section.fields.map(field => ({
        ...field,
        value: data?.[field.key] ?? getDefaultValue(field)
      }))
    }))
  };
}

function getDefaultValue(field: any) {
  switch (field.type) {
    case 'MULTISELECT':
      return [];
    case 'SELECT_REMOTE_MULTIPLE':
      return [];
    case 'BOOLEAN':
      return false;
    default:
      return '';
  }
}