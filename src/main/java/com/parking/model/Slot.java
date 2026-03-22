package com.parking.model;

/**
 * Domain model for a parking slot.
 *
 * NOTE ON DESERIALIZATION STRATEGY:
 * Jackson can't directly instantiate SlotType (it's not in JSON as a full object).
 * So we use a two-step approach:
 *   Step 1 — Jackson deserializes into SlotDTO (raw strings)
 *   Step 2 — SlotDTO.toDomain() resolves the SlotType via registry and builds this Slot
 *
 * This keeps the domain model clean — Slot knows nothing about JSON or Jackson.
 */
public class Slot {
    private final String id;
    private final Location location;
    private final int levelId;
    private final SlotType slotType;
    private volatile boolean isAvailable; // volatile for visibility across threads

    public Slot(String id, Location location, int levelId, SlotType slotType) {
        this.id = id;
        this.location = location;
        this.levelId = levelId;
        this.slotType = slotType;
        this.isAvailable = true;
    }

    public String getId()          { return id; }
    public Location getLocation()  { return location; }
    public int getLevelId()        { return levelId; }
    public SlotType getSlotType()  { return slotType; }
    public boolean isAvailable()   { return isAvailable; }

    // Concurrency note: actual atomic booking will use CAS or synchronization
    // at the ParkingLot level — not here. Slot just holds state.
    public void setAvailable(boolean available) { this.isAvailable = available; }

    @Override
    public String toString() {
        return "Slot{id='" + id + "', level=" + levelId +
               ", location=" + location + ", type=" + slotType.getName() +
               ", available=" + isAvailable + "}";
    }
}