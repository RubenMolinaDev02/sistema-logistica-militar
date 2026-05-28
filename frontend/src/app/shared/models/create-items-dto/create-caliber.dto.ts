import { ItemFormModel } from '../form-dto/form-structure.dto';

export const CREATE_CALIBER_MODEL: ItemFormModel = {

  sections: [

    /*
     * BASIC
     */
    {
      title: 'Basic',

      fields: [

        {
          key: 'name',
          label: 'Caliber Name',
          type: 'TEXT',
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
          label: 'Caliber Type',
          type: 'SELECT',

          value: '',

          options: [

            'PISTOL',
            'REVOLVER',
            'RIFLE_MEDIUM',
            'RIFLE_LARGE',
            'RIFLE_SNIPER',
            'ANTI_MATERIAL',
            'SHOTGUN_BUCKSHOT',
            'SHOTGUN_SLUG',
            'ROCKET',
            'RIFLE_GRENADE'

          ]
        },

        {
          key: 'standard',
          label: 'Caliber Standard',
          type: 'SELECT',

          value: '',

          options: [

            'NATO',
            'SOVIET',
            'CHINESE',
            'WESTERN',
            'PROPRIETARY',
            'OTHER'

          ]
        }

      ]
    }

  ]

};