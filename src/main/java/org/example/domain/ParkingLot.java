package org.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class ParkingLot {

    private int capacity;

    private List<ParkingSlot> parkingSlots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingSlots = new ArrayList<>();
        new ParkingSlot();
        System.out.println("Created a parking lot with " + capacity + " slots");
    }

    public void parkCar(String registrationNumber, String color) {
        Car car = new Car(registrationNumber, color);
        int availableSlot = findNextAvailableSlot(parkingSlots);
        ParkingSlot parkingSlot = new ParkingSlot(availableSlot, false, car);
        parkingSlots.add(parkingSlot);
        System.out.println("Allocated slot number: " + availableSlot);
    }

    private int findNextAvailableSlot(List<ParkingSlot> parkingSlots) {
        if (capacity == 0 || Objects.equals(capacity, parkingSlots.size())) {
            return -1;
        }
        if (parkingSlots.isEmpty()) {
            return 1;
        }
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isAvailable()) {
                return slot.getSlotNumber();
            }
        }
        return 0;
    }

    public void showParkingStatus() {
        System.out.println("Slot No. Registration No Colour");
        for (ParkingSlot slot : parkingSlots) {
            System.out.println(slot.toString());
        }
    }

    public List<String> getRegistrationNumbersForColor(String color) {
        List<String> registrationNumbers = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getCar().getColor().equals(color)) {
                registrationNumbers.add(slot.getCar().getRegistrationNumber());
            }
        }
        return registrationNumbers;
    }

    public List<ParkingSlot> getAllParkingSlotsForColor(String color) {
        List<ParkingSlot> slotsForGivenColor = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getCar().getColor().equals(color)) {
                slotsForGivenColor.add(slot);
            }
        }
        return slotsForGivenColor;
    }

    public int findSlotNumber(String registrationNumber) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getCar().getRegistrationNumber().equals(registrationNumber)) {
                return slot.getSlotNumber();
            }
        }
        return -1;
    }

    public void leave(int slotNo) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotNumber() == slotNo) {
                slot.setAvailable(true);
                slot.setCar(null);
            }
        }
    }

    public void exitConsole() {
        System.exit(0);
    }
}
