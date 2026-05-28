import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_MANUFACTURER_MODEL: ItemFormModel = {
  sections: [
    {
      title: 'Basic',
      fields: [
        {
          key: 'reference',
          label: 'Reference',
          type: 'TEXT',
          value: '',
          disabled: true
        },
        {
          key: 'name',
          label: 'Name',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'country',
          label: 'Country',
          type: 'TEXT',
          value: ''
        }
      ]
    },

    {
      title: 'Media',
      fields: [
        {
          key: 'logo',
          label: 'Logo',
          type: 'IMAGE_UPLOAD',
          value: ''
        }
      ]
    }
  ]
};