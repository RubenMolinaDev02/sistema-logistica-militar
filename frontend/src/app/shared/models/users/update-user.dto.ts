import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_USER_MODEL: ItemFormModel = {
  sections: [
    {
      title: 'Personal Information',
      fields: [
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
          key: 'dni',
          label: 'DNI',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'soldierNumber',
          label: 'Soldier Number',
          type: 'TEXT',
          value: ''
        }
      ]
    },

    {
      title: 'Account',
      fields: [
        {
          key: 'email',
          label: 'Email',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'username',
          label: 'Username',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'avatarUrl',
          label: 'Avatar',
          type: 'IMAGE_UPLOAD',
          value: ''
        },
        {
          key: 'active',
          label: 'Active',
          type: 'BOOLEAN',
          value: true
        }
      ]
    },

    {
      title: 'Military Information',
      fields: [
        {
          key: 'rank',
          label: 'Rank',
          type: 'SELECT',
          value: '',
          options: [
            'RECRUIT',
            'PRIVATE',
            'PRIVATE_FIRST_CLASS',
            'SPECIALIST',
            'CORPORAL',
            'SERGEANT',
            'STAFF_SERGEANT',
            'SERGEANT_FIRST_CLASS',
            'MASTER_SERGEANT',
            'FIRST_SERGEANT',
            'SERGEANT_MAJOR',
            'SECOND_LIEUTENANT',
            'FIRST_LIEUTENANT',
            'CAPTAIN',
            'MAJOR',
            'LIEUTENANT_COLONEL',
            'COLONEL',
            'BRIGADIER_GENERAL',
            'MAJOR_GENERAL',
            'LIEUTENANT_GENERAL',
            'GENERAL',
            'CIVILIAN_CONTRACTOR',
            'ADMINISTRATOR',
            'SYSTEM_OPERATOR'
          ]
        },
        {
          key: 'role',
          label: 'Role',
          type: 'SELECT',
          value: '',
          options: [
            'INFANTRY',
            'COMBAT_MEDIC',
            'RADIO_OPERATOR',
            'FORWARD_OBSERVER',
            'ENGINEER',
            'TANK_CREW',
            'DRIVER',
            'VEHICLE_COMMANDER',
            'LOGISTICS',
            'SUPPLY_SPECIALIST',
            'ARMORER',
            'MECHANIC',
            'IT_SPECIALIST',
            'TEAM_LEADER',
            'SQUAD_LEADER',
            'PLATOON_SERGEANT',
            'PLATOON_LEADER',
            'COMPANY_COMMANDER',
            'SPECIAL_FORCES',
            'PILOT',
            'DRONE_OPERATOR',
            'OTHER'
          ]
        },
        {
          key: 'locationId',
          label: 'Location',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: '/organization/locations'
        }
      ]
    },

    {
      title: 'Contact',
      fields: [
        {
          key: 'phoneNumber',
          label: 'Phone Number',
          type: 'NUMBER',
          value: 0
        }
      ]
    }
  ]
};