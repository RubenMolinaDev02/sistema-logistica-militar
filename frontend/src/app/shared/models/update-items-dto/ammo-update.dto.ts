import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_AMMO_MODEL: ItemFormModel = {
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
          key: 'caliberId',
          label: 'Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'calibers'
        },

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