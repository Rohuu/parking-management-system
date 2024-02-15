package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ParkingSlot {

    private int slotNumber;

    private boolean isAvailable;

    private Car car;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isAvailable = true;
    }

    public void removeVehicle() {
        car = null;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return slotNumber + " " + car.toString();
    }

}
