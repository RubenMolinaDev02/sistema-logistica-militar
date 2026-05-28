import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_NVG_MODEL: ItemFormModel = {
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
      title: 'Specifications',
      fields: [
        {
          key: 'generation',
          label: 'Generation',
          type: 'SELECT',
          value: '',
          options: [
            'GEN1',
            'GEN2',
            'GEN3',
            'GEN3_PLUS'
          ]
        },
        {
          key: 'range',
          label: 'Range',
          type: 'NUMBER',
          value: 0
        }
      ]
    },

    {
      title: 'Status',
      fields: [
        {
          key: 'status',
          label: 'Service Status',
          type: 'SELECT',
          value: '',
          options: [
            'ACTIVE',
            'LIMITED_USE',
            'RESERVE',
            'RETIRED',
            'CAPTURED'
          ]
        }
      ]
    }
  ]
};