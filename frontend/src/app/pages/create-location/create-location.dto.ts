import { ItemFormModel } from "../../shared/models/form-structure.dto"; 

export const CREATE_LOCATION_MODEL: ItemFormModel = {
  sections: [
    {
      title: 'Basic Information',
      fields: [
        {
          key: 'name',
          label: 'Location Name',
          type: 'TEXT',
          value: ''
        },

        {
          key: 'country',
          label: 'Country',
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
          label: 'Location Type',
          type: 'SELECT',
          value: '',
          options: [
            'WAREHOUSE',
            'BASE'
          ]
        },

        {
          key: 'status',
          label: 'Status',
          type: 'SELECT',
          value: '',
          options: [
            'ACTIVE',
            'INACTIVE',
            'UNDER_MAINTENANCE',
            'LOCKED'
          ]
        }
      ]
    },

    {
      title: 'Capacity',
      fields: [
        {
          key: 'maxTroops',
          label: 'Maximum Troops',
          type: 'NUMBER',
          value: 0
        }
      ]
    }
  ]
};