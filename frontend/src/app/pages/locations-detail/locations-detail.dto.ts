import { ItemDetailModel } from "../../shared/models/detail/detailModel";

export function mapToLocationDetail(item: any): ItemDetailModel {

  return {
    id: item.id,
    reference: item.reference,
    name: item.name,

    sections: [
      {
        title: 'General Information',
        fields: [
          {
            key: 'name',
            label: 'Name',
            value: item.name,
            type: 'TEXT'
          },
          {
            key: 'reference',
            label: 'Reference',
            value: item.reference,
            type: 'TEXT'
          },
          {
            key: 'country',
            label: 'Country',
            value: item.country,
            type: 'TEXT'
          }
        ]
      },

      {
        title: 'Classification',
        fields: [
          {
            key: 'type',
            label: 'Location Type',
            value: item.type,
            type: 'TEXT'
          },
          {
            key: 'status',
            label: 'Status',
            value: item.status,
            type: 'TEXT'
          }
        ]
      },

      {
        title: 'Capacity',
        fields: [
          {
            key: 'maxTroops',
            label: 'Maximum Troops',
            value: item.maxTroops,
            type: 'NUMBER'
          }
        ]
      }
    ]
  };
}