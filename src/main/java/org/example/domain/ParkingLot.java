package org.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exception.SlotNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    }

    public int parkCar(String registrationNumber, String color) {

        Car car = new Car(registrationNumber, color);
        ParkingSlot availableSlot = parkingSlots.stream().filter(ParkingSlot::isAvailable).findFirst().orElse(null);

        if (availableSlot != null) {
            availableSlot.setAvailable(false);
            availableSlot.setCar(car);
            return availableSlot.getSlotNumber();
        }
        return -1;
    }

    public void showParkingStatus() {
        System.out.println("Slot Registration Color");
        parkingSlots.stream()
                .filter(slot -> Objects.nonNull(slot.getCar()))
                .forEach(slot -> System.out.println(slot.toString()));
    }

    public List<String> getRegistrationNumbersForColor(String color) {
        return parkingSlots.stream()
                .filter(slot -> Objects.nonNull(slot.getCar()) && slot.getCar().getColor().equals(color))
                .map(slot -> slot.getCar().getRegistrationNumber())
                .collect(Collectors.toList());
    }

    private boolean isCarParked(Car car) {
        for (ParkingSlot slot : parkingSlots) {
            if (Objects.nonNull(slot) && (slot.getCar().equals(car))) {
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

    public int findSlotNumber(String registrationNumber) throws SlotNotFoundException {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getCar().getRegistrationNumber().equals(registrationNumber)) {
                return slot.getSlotNumber();
            }
        }
        throw new SlotNotFoundException("Slot not found for registration number: " + registrationNumber);
    }

    public int leave(int slotNo) throws SlotNotFoundException {
        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotNumber() == slotNo) {
                slot.setAvailable(true);
                slot.setCar(null);
                return slot.getSlotNumber();
            }
        }
        throw new SlotNotFoundException("Slot " + slotNo + " not found in the parking lot.");
    }

    public void exitConsole() {
        System.exit(0);
    }

}
