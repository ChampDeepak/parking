package com.parking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.registry.SlotTypeRegistry;

/**
 * Raw Data Transfer Object that mirrors the JSON structure exactly.
 * Jackson deserializes into this — it handles all the messy JSON field names.
 *
 * Then toDomain() converts it to a proper Slot (domain object).
 *
 * WHY SEPARATE DTO AND DOMAIN?
 * - Domain objects (Slot) are clean, immutable, and have no Jackson annotations
 * - DTOs are disposable — they exist only during parsing
 * - If JSON schema changes, you only touch the DTO, not your domain model
 */
public class SlotDTO {

    @JsonProperty("id")
    public String id;

    @JsonProperty("location")
    public Location location;   // Location has its own setters, Jackson handles it

    @JsonProperty("levelID")
    public int levelId;

    @JsonProperty("type")
    public String type;         // "SMALL", "MEDIUM", "LARGE" — raw string from JSON

    /** Convert raw DTO → domain Slot, resolving SlotType via registry */
    public Slot toDomain() {
        SlotType slotType = SlotTypeRegistry.resolve(this.type);
        return new Slot(id, location, levelId, slotType);
    }
}
