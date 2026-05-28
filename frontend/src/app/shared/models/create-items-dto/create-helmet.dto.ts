import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_HELMET_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Helmet Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Helmet Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * PROTECTION
     */
    {
      title: 'Protection',

      fields: [

        {
          key: 'level',
          label: 'Protection Level',
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

    /*
     * STATUS
     */
    {
      title: 'Status',

      fields: [

        {
          key: 'weight',
          label: 'Weight (kg)',
          type: 'NUMBER',
          value: 0
        },

        {
          key: 'status',
          label: 'Service Status',
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