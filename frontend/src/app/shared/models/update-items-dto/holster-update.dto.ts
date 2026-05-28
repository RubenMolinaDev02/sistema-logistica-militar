import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_HOLSTER_MODEL: ItemFormModel = {
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
          key: 'type',
          label: 'Holster Type',
          type: 'SELECT',
          value: '',
          options: [
            'BELT',
            'MOLLE',
            'DROP_LEG',
            'CONCEALED',
            'CHEST'
          ]
        },
        {
          key: 'universal',
          label: 'Universal',
          type: 'BOOLEAN',
          value: false
        }
      ]
    },

    {
      title: 'Compatibility',
      fields: [
        {
          key: 'compatibleWeaponIds',
          label: 'Compatible Weapons',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: 'weapons'
        }
      ]
    }
  ]
};