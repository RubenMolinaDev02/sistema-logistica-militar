import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_BARREL_ATTACHMENT_MODEL: ItemFormModel = {
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
      title: 'Classification',
      fields: [
        {
          key: 'type',
          label: 'Attachment Type',
          type: 'SELECT',
          value: '',
          options: [
            'SUPPRESSOR',
            'MUZZLE_BRAKE',
            'FLASH_HIDER',
            'COMPENSATOR',
            'THREAD_PROTECTOR'
          ]
        }
      ]
    },

    {
      title: 'Compatibility',
      fields: [
        {
          key: 'compatiblePlatformsIds',
          label: 'Compatible Platforms',
          type: 'SELECT_REMOTE_MULTIPLE',
          value: [],
          endpoint: '/armory/platforms'
        },
        {
          key: 'compatibleCaliber',
          label: 'Compatible Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: '/armory/calibers'
        }
      ]
    }
  ]
};