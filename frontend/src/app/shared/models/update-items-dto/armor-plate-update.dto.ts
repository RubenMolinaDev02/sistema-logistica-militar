import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_ARMOR_PLATE_MODEL: ItemFormModel = {
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
      title: 'Classification',
      fields: [
        {
          key: 'level',
          label: 'Protection Level',
          type: 'SELECT',
          value: '',
          options: [
            'LEVEL_IIIA',
            'LEVEL_III',
            'LEVEL_IV'
          ]
        },

        {
          key: 'material',
          label: 'Material',
          type: 'SELECT',
          value: '',
          options: [
            'STEEL',
            'TITANIUM',
            'KEVLAR_COMPOSITE',
            'CERAMIC_COMPOSITE',
            'STEEL_POLYMER_HYBRID',
            'OTHER'
          ]
        }
      ]
    },

    {
      title: 'Performance',
      fields: [
        {
          key: 'weight',
          label: 'Weight',
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