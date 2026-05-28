import { ItemFormModel } from '../form-dto/form-structure.dto';

export const UPDATE_WEAPON_MODEL: ItemFormModel = {
  sections: [
    /*
     * BASIC INFO
     */
    {
      title: 'Basic',
      fields: [
        {
          key: 'name',
          label: 'Name',
          type: 'TEXT',
          value: ''
        },
        {
          key: 'reference',
          label: 'Reference',
          type: 'TEXT',
          value: '',
          disabled: true
        },
        {
          key: 'image',
          label: 'Weapon Image',
          type: 'IMAGE_UPLOAD',
          value: ''
        },
        {
          key: 'manufacturerId',
          label: 'Manufacturer',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'manufacturers'
        }
      ]
    },

    /*
     * CLASSIFICATION
     */
    {
      title: 'Classification',
      fields: [
        {
          key: 'type',
          label: 'Weapon Type',
          type: 'MULTISELECT',
          value: [],
          options: [
            'PISTOL',
            'REVOLVER',
            'ASSAULT_RIFLE',
            'BATTLE_RIFLE',
            'BOLT_ACTION_RIFLE',
            'CARBINE',
            'SHOTGUN',
            'SMG',
            'PDW',
            'SNIPER_RIFLE',
            'DESIGNATED_MARKSMAN_RIFLE',
            'LIGHT_MACHINE_GUN',
            'MULTIPURPOSE_MACHINE_GUN',
            'HEAVY_MACHINE_GUN',
            'DISPOSABLE_ROCKET_LAUNCHER',
            'ROCKET_LAUNCHER',
            'GRENADE_LAUNCHER'
          ]
        }
      ]
    },

    /*
     * PERFORMANCE
     */
    {
      title: 'Performance',
      fields: [
        {
          key: 'effectiveDistance',
          label: 'Effective Distance',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'fireRate',
          label: 'Fire Rate',
          type: 'NUMBER',
          value: 0
        },
        {
          key: 'barrelLength',
          label: 'Barrel Length',
          type: 'NUMBER',
          value: 0
        }
      ]
    },

    /*
     * MECHANICS
     */
    {
      title: 'Mechanics',
      fields: [
        {
          key: 'fireMode',
          label: 'Fire Mode',
          type: 'MULTISELECT',
          value: [],
          options: [
            'SINGLE_SHOT',
            'SEMI_AUTO',
            'BURST',
            'FULL_AUTO'
          ]
        },
        {
          key: 'action',
          label: 'Action Type',
          type: 'SELECT',
          value: '',
          options: [
            'GAS_OPERATED',
            'BOLT_ACTION',
            'RECOIL_OPERATED'
          ]
        },
        {
          key: 'sightMount',
          label: 'Sight Mount',
          type: 'SELECT',
          value: '',
          options: [
            'PICATINNY',
            'WEAVER',
            'DOVETAIL',
            'TOP_RAIL',
            'SIDE_RAIL',
            'INTEGRATED',
            'PROPRIETARY',
            'NONE'
          ]
        },
        {
          key: 'hasBayonetMount',
          label: 'Bayonet Mount',
          type: 'BOOLEAN',
          value: false
        },
        {
          key: 'compatibleWithSupressor',
          label: 'Suppressor Compatible',
          type: 'BOOLEAN',
          value: false
        }
      ]
    },

    /*
     * STOCK & RELATIONS
     */
    {
      title: 'Stock & Compatibility',
      fields: [
        {
          key: 'stockAttachmentSystem',
          label: 'Stock System',
          type: 'SELECT',
          value: '',
          options: [
            'BUFFER_TUBE',
            'AK_FIXED',
            'AK_SIDE_FOLDING',
            'AK_UNDERFOLDER',
            'SHOTGUN_FIXED',
            'SHOTGUN_TELESCOPIC',
            'SHOTGUN_FOLDING',
            'CHASSIS_SYSTEM',
            'SIDE_FOLDING',
            'UNDERFOLDER',
            'OVERFOLDER',
            'BRACE',
            'PDW_STOCK',
            'PROPRIETARY',
            'NONE'
          ]
        },
        {
          key: 'platformId',
          label: 'Platform',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'platforms'
        },
        {
          key: 'caliberId',
          label: 'Caliber',
          type: 'SELECT_REMOTE',
          value: '',
          endpoint: 'calibers'
        },
        {
          key: 'stockId',
          label: 'Stock',
          type: 'SELECT_REMOTE_DEPENDENT',
          value: '',
          endpoint: '/armory/stocks/compatible',
          nullable: true,
          dependsOn: ['platformId', 'stockAttachmentSystem']
        },
        {
          key: 'handguardId',
          label: 'Handguard',
          type: 'SELECT_REMOTE_DEPENDENT',
          value: '',
          endpoint: '/armory/handguards/compatible',
          nullable: true,
          dependsOn: ['platformId']
        },
        {
          key: 'status',
          label: 'Status',
          type: 'SELECT',
          value: '',
          options: [
            'ACTIVE',
            'LIMITED_USE',
            'RESERVE',
            'RETIRED',
            'CAPTURED'
          ]
        }
      ]
    }
  ]
};