import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_HELMET_MODEL: ItemFormModel = {
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
          key: 'level',
          label: 'Helmet Level',
          type: 'SELECT',
          value: '',
          options: [
            'NIJ_LEVEL_IIA',
            'NIJ_LEVEL_II',
            'NIJ_LEVEL_IIIA',
            'FRAGMENTATION_ONLY',
            'TRAINING',
            'OTHER'
          ]
        },
        {
          key: 'material',
          label: 'Material',
          type: 'SELECT',
          value: '',
          options: [
            'KEVLAR',
            'ARAMID_COMPOSITE',
            'FIBERGLASS_COMPOSITE',
            'STEEL',
            'TITANIUM',
            'COMPOSITE_HYBRID',
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
          label: 'Weight (kg)',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'status',
          label: 'Status',
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