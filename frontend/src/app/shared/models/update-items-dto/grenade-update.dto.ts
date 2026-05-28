import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_GRENADE_MODEL: ItemFormModel = {
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
        },
        {
          key: 'image',
          label: 'Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        }
      ]
    },

    {
      title: 'Specifications',
      fields: [
        {
          key: 'fuzeDelay',
          label: 'Fuze Delay',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'armingDistance',
          label: 'Arming Distance',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'lethal',
          label: 'Lethal',
          type: 'BOOLEAN',
          value: false
        }
      ]
    },

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
    }
  ]
};