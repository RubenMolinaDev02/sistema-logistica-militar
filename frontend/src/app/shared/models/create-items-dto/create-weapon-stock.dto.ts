import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_STOCK_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Stock Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Stock Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * CLASSIFICATION
     */
    {
      title: 'Classification',

      fields: [

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

    /*
     * COMPATIBILITY
     */
    {
      title: 'Compatibility',

      fields: [

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