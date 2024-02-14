package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingSlot {

    private int slotNumber;

    private boolean isAvailable;

    private Car car;

    public void removeVehicle() {
        car = null;
        this.isAvailable = false;
    }

    public void parkVehicle(Car car) {
        this.car = car;
        this.isAvailable = true;
    }

}
