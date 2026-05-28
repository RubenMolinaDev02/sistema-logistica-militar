import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_CALIBER_MODEL: ItemFormModel = {
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
        }
      ]
    },

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
          label: 'Standard',
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