import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_BAYONET_MODEL: ItemFormModel = {
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
          key: 'bladeLength',
          label: 'Blade Length',
          type: 'NUMBER',
          value: 0
        },

        {
          key: 'type',
          label: 'Bayonet Type',
          type: 'SELECT',
          value: '',
          options: [
            'KNIFE',
            'SPIKE',
            'SWORD',
            'MULTI_TOOL',
            'CEREMONIAL',
            'OTHER'
          ]
        }
      ]
    },

    {
      title: 'Compatibility',
      fields: [
        {
          key: 'compatibleWeaponsIds',
          label: 'Compatible Weapons',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: '/armory/weapons'
        }
      ]
    }
  ]
};