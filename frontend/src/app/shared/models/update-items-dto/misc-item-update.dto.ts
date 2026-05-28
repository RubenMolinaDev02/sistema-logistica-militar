import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_MISC_ITEM_MODEL: ItemFormModel = {
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
          label: 'Item Type',
          type: 'SELECT',
          value: '',
          options: [
            'HELMET_COVER',
            'EYE_PROTECTION',
            'EAR_PROTECTION',
            'BACKPACK',
            'WEBBING',
            'SLING',
            'BELT',
            'POUCH',
            'MEDICAL_KIT',
            'TOOL_KIT',
            'TOOL',
            'OTHER'
          ]
        }
      ]
    }
  ]
};