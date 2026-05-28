import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_ATTACHMENT_MODEL: ItemFormModel = {
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
          label: 'Attachment Type',
          type: 'SELECT',
          value: '',
          options: [
            'UNDERBARREL_WEAPON',
            'OPTIC',
            'SIGHT',
            'LIGHT',
            'LASER',
            'FOREGRIP',
            'OTHER'
          ]
        },

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
        }
      ]
    }
  ]
};