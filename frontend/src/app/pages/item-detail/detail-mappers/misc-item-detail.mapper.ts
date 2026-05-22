import { ItemDetailModel } from "../../../shared/models/detail/detailModel";

export function mapToMiscDetail(item: any): ItemDetailModel {

  return {
    id: item.id,
    reference: item.reference,
    name: item.name,
    image: item.image,

    sections: [

      {
        title: 'General',
        fields: [
          {
            key: 'type',
            label: 'Type',
            value: item.type,
            type: 'TEXT'
          }
        ]
      },

      {
        title: 'Identification',
        fields: [
          {
            key: 'reference',
            label: 'Reference',
            value: item.reference,
            type: 'TEXT'
          },
          {
            key: 'id',
            label: 'ID',
            value: item.id,
            type: 'TEXT'
          }
        ]
      }

    ]
  };
}