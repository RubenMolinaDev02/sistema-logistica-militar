import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_HANDGUARD_MODEL: ItemFormModel = {
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
          key: 'mountType',
          label: 'Mount Type',
          type: 'SELECT',
          value: '',
          options: [
            'PICATINNY',
            'MLOK',
            'KEYMOD',
            'NONE'
          ]
        },
        {
          key: 'material',
          label: 'Material',
          type: 'SELECT',
          value: '',
          options: [
            'WOOD',
            'BAKELITE',
            'POLYMER',
            'ALUMINUM',
            'STEEL',
            'OTHER'
          ]
        }
      ]
    },

    {
      title: 'Compatibility',
      fields: [
        {
          key: 'compatiblePlatformsIds',
          label: 'Compatible Platforms',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: '/armory/platforms'
        }
      ]
    }
  ]
};