package com.parking.model;

public class SlotType {
    private final String name;
    private final double hourlyRate;
    private final int sizeOrder; // 1=small, 2=medium, 3=large — useful for "can this slot fit this vehicle?"

    public SlotType(String name, double hourlyRate, int sizeOrder) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.sizeOrder = sizeOrder;
    }

    public String getName()        { return name; }
    public double getHourlyRate()  { return hourlyRate; }
    public int getSizeOrder()      { return sizeOrder; }

    @Override
    public String toString() {
        return "SlotType{name='" + name + "', hourlyRate=" + hourlyRate + "}";
    }
}