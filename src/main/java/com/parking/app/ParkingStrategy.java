package com.parking.app;

import com.parking.model.Ticket;
import com.parking.model.VehicleDto;

public interface ParkingStrategy {
    public Ticket park(VehicleDto vehicle,  String time, String slotType, String gateId); 
}
