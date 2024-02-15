package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Car {

    private String registrationNumber;

    private String color;

    @Override
    public String toString() {
        return registrationNumber + " " + color;
    }
}
