import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_OPTIC_MODEL: ItemFormModel = {
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
      title: 'Mount & Type',
      fields: [
        {
          key: 'mountType',
          label: 'Mount Type',
          type: 'SELECT',
          value: '',
          options: [
            'PICATINNY',
            'WEAVER',
            'DOVETAIL',
            'TOP_RAIL',
            'SIDE_RAIL',
            'INTEGRATED',
            'PROPRIETARY',
            'NONE'
          ]
        },
        {
          key: 'type',
          label: 'Optic Type',
          type: 'SELECT',
          value: '',
          options: [
            'RED_DOT',
            'RED_DOT_PISTOL',
            'HOLOGRAPHIC',
            'VARIABLE_SCOPE',
            'FIXED_SCOPE',
            'NIGHT_VISION',
            'THERMAL',
            'IRON_SIGHTS',
            'OTHER'
          ]
        }
      ]
    },

    {
      title: 'Zoom',
      fields: [
        {
          key: 'minZoom',
          label: 'Min Zoom',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'maxZoom',
          label: 'Max Zoom',
          type: 'NUMBER',
          value: 0
        }
      ]
    }
  ]
};