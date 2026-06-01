import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_MY_PROFILE_MODEL: ItemFormModel = {
  sections: [
    {
      title: 'Personal Information',
      fields: [
        {
          key: 'email',
          label: 'Email',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'firstName',
          label: 'First Name',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'lastName',
          label: 'Last Name',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'phoneNumber',
          label: 'Phone Number',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'avatarUrl',
          label: 'Avatar',
          type: 'IMAGE_UPLOAD',
          value: ''
        }
      ]
    }
  ]
};