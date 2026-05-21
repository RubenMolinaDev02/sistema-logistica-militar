export interface WeaponModel {
    id:                       string;
    reference:                string;
    name:                     string;
    image:                    string;
    type:                     string[];
    effectiveDistance:        number;
    fireRate?:                number;
    barrelLength:             number;
    fireMode:                 string[];
    action:                   string;
    status:                   string;
    compatibleWithSupressor?: boolean;
    hasBayonetMount?:         boolean;
}