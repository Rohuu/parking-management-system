package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class ParkingLot {

    private int capacity;

    private List<ParkingSlot> parkingSlots;

    public void createParkingLot(int capacity) {
        parkingSlots = new ArrayList<>(capacity);
    }

    public void parkCar(String registrationNumber, String color) {
        Car car = new Car(registrationNumber, color);
        int availableSlot = findNextAvailableSlot(parkingSlots);
        ParkingSlot parkingSlot = new ParkingSlot(availableSlot, true, car);
        parkingSlots.add(parkingSlot);
    }

    private int findNextAvailableSlot(List<ParkingSlot> parkingSlots) {
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

    public void printStatus() {
        for (ParkingSlot slot : parkingSlots) {
            System.out.println(
                    slot.getSlotNumber() + " " + slot.getCar().getRegistrationNumber() + " " + slot.getCar().getColor()
            );
        }
    }

    public List<String> getRegistrationNumbersForColor(String color) {
        List<String> registrationNumbers = new ArrayList<>();
        for (ParkingSlot slot : parkingSlots) {
            if(slot.getCar().getColor().equals(color)){
                registrationNumbers.add(slot.getCar().getRegistrationNumber());
            }
        }
        return registrationNumbers;
    }

    public List<ParkingSlot> getAllParkingSlotsForColor(String color){
        List<ParkingSlot> slotsForGivenColor= new ArrayList<>();
        for (ParkingSlot slot:parkingSlots){
            if(slot.getCar().getColor().equals(color)){
                slotsForGivenColor.add(slot);
            }
        }
        return slotsForGivenColor;
    }

    public int findSlotNumber(String registrationNumber){
        for (ParkingSlot slot:parkingSlots){
            if(slot.getCar().getRegistrationNumber().equals(registrationNumber)){
                return slot.getSlotNumber();
            }
        }
        return 0;
    }

}
