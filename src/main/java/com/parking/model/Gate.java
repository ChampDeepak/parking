package com.parking.model;

/**
 * Represents an entry gate on the ground floor (level 0).
 * Gates are the origin points for distance calculation to slots.
 */
public class Gate {
    private final String id;
    private final Location location;

    public Gate(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId()         { return id; }
    public Location getLocation() { return location; }

    @Override
    public String toString() {
        return "Gate{id='" + id + "', location=" + location + "}";
    }
}
