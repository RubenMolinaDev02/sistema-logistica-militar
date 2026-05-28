import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_STOCK_MODEL: ItemFormModel = {
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
        },
        {
          key: 'atachmentMethod',
          label: 'Attachment Method',
          type: 'SELECT',
          value: '',
          options: [
            'BUFFER_TUBE',
            'AK_FIXED',
            'AK_SIDE_FOLDING',
            'AK_UNDERFOLDER',
            'SHOTGUN_FIXED',
            'SHOTGUN_TELESCOPIC',
            'SHOTGUN_FOLDING',
            'CHASSIS_SYSTEM',
            'SIDE_FOLDING',
            'UNDERFOLDER',
            'OVERFOLDER',
            'BRACE',
            'PDW_STOCK',
            'PROPRIETARY',
            'NONE'
          ]
        },
        {
          key: 'type',
          label: 'Stock Type',
          type: 'SELECT',
          value: '',
          options: [
            'FIXED',
            'FOLDING_SIDE',
            'FOLDING_OVER',
            'FOLDING_UNDER',
            'TELESCOPIC'
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
          endpoint: 'platforms'
        }
      ]
    }
  ]
};