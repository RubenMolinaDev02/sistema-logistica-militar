import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_ARMOR_PLATE_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Plate Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Plate Image',
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

            'LEVEL_IIIA',
            'LEVEL_III',
            'LEVEL_IV'

          ]
        },

        {
          key: 'material',
          label: 'Plate Material',
          type: 'SELECT',

          value: '',

          options: [

            'STEEL',
            'TITANIUM',
            'KEVLAR_COMPOSITE',
            'CERAMIC_COMPOSITE',
            'STEEL_POLYMER_HYBRID',
            'OTHER'

          ]
        }

      ]
    },

    /*
     * SPECIFICATIONS
     */
    {
      title: 'Specifications',

      fields: [

        {
          key: 'weight',
          label: 'Weight',
          type: 'NUMBER',
          value: 0,
          unit: 'kg'
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