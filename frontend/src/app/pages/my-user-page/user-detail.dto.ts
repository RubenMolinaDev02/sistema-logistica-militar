import { ItemDetailModel } from "../../shared/models/detail/detailModel"; 

export function mapToUserDetail(item: any): ItemDetailModel {

  return {
  id: item.id,
  name: item.username,
  image: item.avatarUrl,
  reference: item.soldierNumber,

  sections: [
    {
      title: 'Personal Information',
      fields: [
        {
          key: 'soldierNumber',
          label: 'Soldier Number',
          value: item.soldierNumber,
          type: 'TEXT'
        },
        {
          key: 'firstName',
          label: 'First Name',
          value: item.firstName,
          type: 'TEXT'
        },
        {
          key: 'lastName',
          label: 'Last Name',
          value: item.lastName,
          type: 'TEXT'
        },
        {
          key: 'dni',
          label: 'DNI',
          value: item.dni,
          type: 'TEXT'
        }
      ]
    },

    {
      title: 'Account',
      fields: [
        {
          key: 'username',
          label: 'Username',
          value: item.username,
          type: 'TEXT'
        },
        {
          key: 'email',
          label: 'Email',
          value: item.email,
          type: 'TEXT'
        },
        {
          key: 'active',
          label: 'Active',
          value: item.active,
          type: 'BOOLEAN'
        },
        {
          key: 'createdAt',
          label: 'Created At',
          value: item.createdAt,
          type: 'TEXT'
        }
      ]
    },

    {
      title: 'Military Information',
      fields: [
        {
          key: 'rank',
          label: 'Rank',
          value: item.rank,
          type: 'TEXT'
        },
        {
          key: 'role',
          label: 'Role',
          value: item.role,
          type: 'TEXT'
        },
        {
          key: 'locationId',
          label: 'Location',
          value: item.locationResponse.name,
          type: 'TEXT'
        }
      ]
    },

    {
      title: 'Contact',
      fields: [
        {
          key: 'phoneNumber',
          label: 'Phone Number',
          value: item.phoneNumber,
          type: 'NUMBER'
        }
      ]
    }
  ],
};
}