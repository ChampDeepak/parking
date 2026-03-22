package com.parking.model;

/**
 * 3D coordinate in the parking lot.
 * x, y = horizontal position on a floor
 * z     = height (each level adds to z; two slots directly stacked share the same x,y)
 */
public class Location {
    private int x;
    private int y;
    private int z;

    // Jackson requires a no-arg constructor for deserialization
    public Location() {}

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setZ(int z) { this.z = z; }

    /**
     * Euclidean distance between two locations.
     * Used by the "find nearest slot from gate" feature.
     */
    public double distanceTo(Location other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        int dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}