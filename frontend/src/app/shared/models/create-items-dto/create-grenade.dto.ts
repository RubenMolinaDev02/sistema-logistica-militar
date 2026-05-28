import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_GRENADE_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Grenade Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'image',
          label: 'Grenade Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }

      ]
    },

    /*
     * PERFORMANCE
     */
    {
      title: 'Performance',

      fields: [

        {
          key: 'fuzeDelay',
          label: 'Fuse Delay (s)',
          type: 'NUMBER',
          value: 0
        },

        {
          key: 'armingDistance',
          label: 'Arming Distance (m)',
          type: 'NUMBER',
          value: 0
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
          label: 'Grenade Type',
          type: 'SELECT',

          value: '',

          options: [

            'FRAGMENTATION',
            'SMOKE',
            'FLASHBANG',
            'INCENDIARY',
            'TEAR_GAS',
            'THERMITE'

          ]
        },

        {
          key: 'armingMethod',
          label: 'Arming Method',
          type: 'SELECT',

          value: '',

          options: [

            'IMPACT',
            'TIMER',
            'REMOTE'

          ]
        }

      ]
    },

    /*
     * SAFETY
     */
    {
      title: 'Safety',

      fields: [

        {
          key: 'lethal',
          label: 'Lethal',
          type: 'BOOLEAN',
          value: false
        }

      ]
    }

  ]

};