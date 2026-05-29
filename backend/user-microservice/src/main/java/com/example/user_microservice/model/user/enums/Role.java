package com.example.user_microservice.model.user.enums;

public enum Role {
    INFANTRY,

    // Apoyo en combate
    COMBAT_MEDIC,
    RADIO_OPERATOR,
    FORWARD_OBSERVER,
    ENGINEER,

    // Vehículos
    TANK_CREW,
    DRIVER,
    VEHICLE_COMMANDER,

    // Logística y soporte
    LOGISTICS,
    SUPPLY_SPECIALIST,
    ARMORER,
    MECHANIC,

    // Tecnología e inteligencia
    IT_SPECIALIST,
    // Mando
    TEAM_LEADER,           // Líder de equipo (~4)
    SQUAD_LEADER,          // Líder de escuadra (~9)
    PLATOON_SERGEANT,      // Sargento de pelotón
    PLATOON_LEADER,        // Teniente
    COMPANY_COMMANDER,     // Capitán

    // Especiales
    SPECIAL_FORCES,
    PILOT,
    DRONE_OPERATOR,

    OTHER
}
