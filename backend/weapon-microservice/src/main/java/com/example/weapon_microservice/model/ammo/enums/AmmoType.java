package com.example.weapon_microservice.model.ammo.enums;

public enum AmmoType{
    //Municion convencional
    FULL_METAL_JACKET,     // FMJ - estándar militar
    HOLLOW_POINT,          // HP - expansión (uso policial/defensa)
    SOFT_POINT,            // SP - expansión parcial
    ARMOR_PIERCING,        // AP - perforante
    ARMOR_PIERCING_INCENDIARY, // API - perforante + incendiaria
    INCENDIARY,            // Incendiaria
    TRACER,                // Trazadora
    SUBSONIC,              // Subsónica (para supresores)
    FRANGIBLE,             // Fragmentable (entrenamiento)
    BUCKSHOT,              // Perdigones (escopeta)
    SLUG,                  // Bala única de escopeta
    RUBBER,                // No letal (goma)
    BLANK,                 // Fogueo
    TRAINING,              // Munición de entrenamiento general
    //Explosivos
    HIGH_EXPLOSIVE,        // HE (granadas, RPG)
    HIGH_EXPLOSIVE_ANTI_TANK, // HEAT (RPG, misiles AT)
    FRAGMENTATION,         // FRAG (granadas)
    THERMOBARIC,           // Explosivo de presión
    SMOKE,                 // Humo
    ILLUMINATION,          // Iluminación
    FLASHBANG,
    OTHER
}
