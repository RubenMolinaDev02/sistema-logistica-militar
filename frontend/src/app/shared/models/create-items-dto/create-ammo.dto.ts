import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_AMMO_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Ammo Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'caliberId',
          label: 'Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'calibers'
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
          label: 'Ammo Type',
          type: 'SELECT',

          value: '',

          options: [

            'FULL_METAL_JACKET',
            'HOLLOW_POINT',
            'SOFT_POINT',
            'ARMOR_PIERCING',
            'ARMOR_PIERCING_INCENDIARY',
            'INCENDIARY',
            'TRACER',
            'SUBSONIC',
            'FRANGIBLE',
            'BUCKSHOT',
            'SLUG',
            'RUBBER',
            'BLANK',
            'TRAINING',

            'HIGH_EXPLOSIVE',
            'HIGH_EXPLOSIVE_ANTI_TANK',
            'FRAGMENTATION',
            'THERMOBARIC',
            'SMOKE',
            'ILLUMINATION',
            'FLASHBANG',

            'OTHER'

          ]
        }

      ]
    }

  ]

};