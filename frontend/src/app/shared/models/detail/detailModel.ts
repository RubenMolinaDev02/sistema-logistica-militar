export interface ItemDetailModel {id: string;
  reference: string;
  name: string;
  image?: string;

  sections: DetailSectionDto[];
}

export interface DetailSectionDto {
  title: string;
  fields: DetailFieldDto[];
}

export interface DetailFieldDto {
  key: string;
  label: string;
  value: any;

  type:
    | 'TEXT'
    | 'NUMBER'
    | 'BOOLEAN'
    | 'ARRAY'
    | 'OBJECT'
    | 'IMAGE'
    | 'BADGE';

  unit?: string;
}