import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_PLATFORM_MODEL: ItemFormModel = {
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
          key: 'image',
          label: 'Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }
      ]
    },

    {
      title: 'Details',
      fields: [
        {
          key: 'description',
          label: 'Description',
          type: 'TEXT',
          value: ''
        }
      ]
    }
  ]
};