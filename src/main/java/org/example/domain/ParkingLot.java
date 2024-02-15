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
        this.parkingSlots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.parkingSlots.add(new ParkingSlot(i + 1));
        }

        System.out.println("Created a parking lot with " + capacity + " slots");
    }

    public int parkCar(String registrationNumber, String color) {

        Car car = new Car(registrationNumber, color);
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isAvailable()) {
                slot.setAvailable(false);
                slot.setCar(car);
                return slot.getSlotNumber();
            }
        }
        return -1;
    }

    public void showParkingStatus() {
        System.out.println("Slot Registration-No Color");
        for (ParkingSlot slot : parkingSlots) {
            if (Objects.nonNull(slot.getCar())) {
                System.out.println(slot.toString());

            }
        }
    }

    public List<String> getRegistrationNumbersForColor(String color) {
        List<String> registrationNumbers = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            if (!isCarParked(slot.getCar())) {
                System.out.println("No such color car left in parking");
            } else if (slot.getCar().getColor().equals(color)) {
                registrationNumbers.add(slot.getCar().getRegistrationNumber());
            }
        }
        return registrationNumbers;
    }

    private boolean isCarParked(Car car) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getCar().equals(car)) {
                return true;
            }
        }
        return false;
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

    public int leave(int slotNo) {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotNumber() == slotNo) {
                slot.setAvailable(true);
                slot.setCar(null);
                return slot.getSlotNumber();
            }
        }
        return -1;
    }

    public void exitConsole() {
        System.exit(0);
    }
}
