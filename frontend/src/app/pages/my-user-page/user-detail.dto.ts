import { ItemDetailModel } from "../../shared/models/detail/detailModel"; 

export function mapToUserDetail(item: any): ItemDetailModel {

  return {
    id: item.id,
    reference: item.reference,
    name: `${item.firstName} ${item.lastName}`,
    image: item.avatarUrl,

    sections: [

      /*
       * GENERAL INFO
       */
      {
        title: 'General',
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
          }
        ]
      },

      /*
       * PERSONAL INFO
       */
      {
        title: 'Personal',
        fields: [
          {
            key: 'dni',
            label: 'DNI',
            value: item.dni,
            type: 'TEXT'
          },
          {
            key: 'phoneNumber',
            label: 'Phone Number',
            value: item.phoneNumber,
            type: 'NUMBER'
          },
          {
            key: 'soldierNumber',
            label: 'Soldier Number',
            value: item.soldierNumber,
            type: 'TEXT'
          }
        ]
      },

      /*
       * MILITARY INFO
       */
      {
        title: 'Military',
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
            key: 'location',
            label: 'Location',
            value: item.location?.name,
            type: 'OBJECT'
          }
        ]
      },

      /*
       * SYSTEM INFO
       */
      {
        title: 'System',
        fields: [
          {
            key: 'createdAt',
            label: 'Created At',
            value: item.createdAt,
            type: 'TEXT'
          }
        ]
      }
    ]
  };
}