package com.example.user_microservice.model.user.enums;

public enum Rank {

    // =========================
    // TROOPS / ENLISTED
    // =========================
    RECRUIT,
    PRIVATE,
    PRIVATE_FIRST_CLASS,
    SPECIALIST,
    CORPORAL,
    SERGEANT,

    // =========================
    // NCO (suboficiales)
    // =========================
    STAFF_SERGEANT,
    SERGEANT_FIRST_CLASS,
    MASTER_SERGEANT,
    FIRST_SERGEANT,
    SERGEANT_MAJOR,

    // =========================
    // OFFICERS (oficiales)
    // =========================
    SECOND_LIEUTENANT,
    FIRST_LIEUTENANT,
    CAPTAIN,
    MAJOR,
    LIEUTENANT_COLONEL,
    COLONEL,

    // =========================
    // GENERAL OFFICERS
    // =========================
    BRIGADIER_GENERAL,
    MAJOR_GENERAL,
    LIEUTENANT_GENERAL,
    GENERAL,

    // =========================
    // NON-COMBAT SYSTEM ROLES
    // =========================
    CIVILIAN_CONTRACTOR,
    ADMINISTRATOR,
    SYSTEM_OPERATOR
}