import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_ARMOR_VEST_MODEL: ItemFormModel = {
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
      title: 'Protection',
      fields: [
        {
          key: 'softArmor',
          label: 'Soft Armor Level',
          type: 'SELECT',
          value: '',
          options: [
            'LEVEL_IIA',
            'LEVEL_II',
            'LEVEL_IIIA',
            'NONE'
          ]
        },
        {
          key: 'compatibleWithPlates',
          label: 'Compatible With Plates',
          type: 'BOOLEAN',
          value: false
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