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

    public void createParkingLot(Integer capacity) {
        parkingSlots = new ArrayList<>(capacity);
    }

}
