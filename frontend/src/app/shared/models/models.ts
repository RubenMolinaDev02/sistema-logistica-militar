// ── Enums ──────────────────────────────────────────────────────────────────
export type ItemType =
  | 'WEAPON' | 'AMMO' | 'MAGAZINE' | 'ARMOR_VEST' | 'ARMOR_PLATE'
  | 'ATTACHMENT' | 'OPTIC' | 'GAS_MASK' | 'GAS_MASK_FILTER' | 'HOLSTER'
  | 'TEXTILE' | 'GRENADE' | 'BAYONET' | 'BARREL_ATTACHMENT' | 'HANDGUARD'
  | 'HELMET' | 'MISC_ITEM' | 'NVG' | 'WEAPON_STOCK';

export type ShipmentStatus =
  | 'CREATED' | 'IN_PROGRESS' | 'ARRIVED' | 'COMPLETED' | 'FAILED' | 'LOST' | 'CANCELLED';

export type LocationType = 'WAREHOUSE' | 'BASE';

// ── Information service ────────────────────────────────────────────────────
export interface ItemResponse {
  id: string;
  reference: string;
  name: string;
  image?: string;
  //type: ItemType;
}

// ── Inventory service ──────────────────────────────────────────────────────
export interface SkuResponse {
  id: string;
  itemId: string;
  itemType: ItemType;
  reference: string;
  attributes: Record<string, any>;
  stock?: StockResponse[];
  active: boolean;
}

export interface StockResponse {
  id: string;
  reference: string;
  skuId: string;
  locationId: string;
  units: number;
}

export interface SerializedUnitResponse {
  id: string;
  batchId: string;
  skuId: string;
  stockId: string;
  serialNumber: string;
}

export interface ShipmentResponse {
  id: string;
  reference: string;
  supplierLocationId: string;
  destinyLocationId: string;
  status: ShipmentStatus;
  creationDate: string;
  arrivalDate?: string;
}

export interface BatchResponse {
  id: string;
  reference: string;
  shipmentId: string;
  genericItems: { skuId: string; quantity: number }[];
  unitIds: string[];
}

// ── Location service ───────────────────────────────────────────────────────
export interface LocationResponse {
  id: string;
  reference: string;
  name: string;
  type: LocationType;
  country?: string;
  status?: string;
}

// ── Dashboard ──────────────────────────────────────────────────────────────
export interface DashboardStats {
  totalSkus: number;
  totalStock: number;
  totalShipments: number;
  activeShipments: number;
  totalLocations: number;
  serializedUnits: number;
}
