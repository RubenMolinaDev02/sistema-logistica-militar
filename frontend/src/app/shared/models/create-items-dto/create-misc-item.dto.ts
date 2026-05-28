import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_MISC_ITEM_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Item Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Item Image',
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
          label: 'Item Type',
          type: 'SELECT',

          value: '',

          options: [

            'HELMET_COVER',
            'EYE_PROTECTION',
            'EAR_PROTECTION',
            'BACKPACK',
            'WEBBING',
            'SLING',
            'BELT',
            'POUCH',
            'MEDICAL_KIT',
            'TOOL_KIT',
            'TOOL',
            'OTHER'

          ]
        }

      ]
    }

  ]

};