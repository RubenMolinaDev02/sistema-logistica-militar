import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_BARREL_ATTACHMENT_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Attachment Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Attachment Image',
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
          label: 'Attachment Type',
          type: 'SELECT',

          value: '',

          options: [

            'SUPPRESSOR',
            'MUZZLE_BRAKE',
            'FLASH_HIDER',
            'COMPENSATOR',
            'THREAD_PROTECTOR'

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
          key: 'compatiblePlatforms',
          label: 'Compatible Platforms',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: 'platforms'
        },

        {
          key: 'compatibleCaliber',
          label: 'Compatible Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'calibers'
        }

      ]
    }

  ]

};