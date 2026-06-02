import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_MAGAZINE_MODEL: ItemFormModel = {
  sections: [
    /*
     * BASIC INFO
     */
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

    /*
     * COMPATIBILITY
     */
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
          key: 'caliberId',
          label: 'Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: '/armory/calibers'
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
          key: 'capacity',
          label: 'Capacity',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'type',
          label: 'Magazine Type',
          type: 'SELECT',
          value: '',
          options: [
            'BOX',
            'DRUM',
            'STICK',
            'BELT',
            'STRIPPER_CLIP',
            'SPEED_LOADER',
            'OTHER'
          ]
        }
      ]
    }
  ]
};