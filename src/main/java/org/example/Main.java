package org.example;

import org.example.domain.ParkingLot;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        while (!exitRequested) {
            System.out.println("Enter a command:");
            String command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("exit")) {
                exitRequested = true;
            } else {
                executeCommand(command);
            }
        }
    }

    private static void executeCommand(String command) {

        ParkingLot parkingLot = null;

        Pattern createPattern = Pattern.compile("create_parking_lot (\\d+)");
        Matcher createMatcher = createPattern.matcher(command);

        Pattern parkPattern = Pattern.compile("park (\\S+) (\\S+)");
        Matcher parkMatcher = parkPattern.matcher(command);

        Pattern leavePattern = Pattern.compile("leave (\\d+)");
        Matcher leaveMatcher = leavePattern.matcher(command);

        Pattern registrationNoPattern = Pattern.compile("registration_numbers_for_cars_with_colour (\\S+)");
        Matcher registrationNoMatcher = registrationNoPattern.matcher(command);

        Pattern statusPattern = Pattern.compile("status");
        Matcher statusMatcher = statusPattern.matcher(command);

        Pattern exitPattern = Pattern.compile("exit");
        Matcher exitMatcher = exitPattern.matcher(command);

        if (createMatcher.matches()) {
            int capacity = Integer.parseInt(createMatcher.group(1));
            parkingLot = new ParkingLot(capacity);
        } else if (parkMatcher.matches()) {
            String registrationNumber = parkMatcher.group(1);
            String color = parkMatcher.group(2);
            parkingLot.parkCar(registrationNumber, color);
        } else if (leaveMatcher.matches()) {
            int slotNo = Integer.parseInt(leaveMatcher.group(1));
            parkingLot.leave(slotNo);
        } else if (registrationNoMatcher.matches()) {
            String color = registrationNoMatcher.group(0);
            parkingLot.getRegistrationNumbersForColor(color);
        }else if (statusMatcher.matches()) {
            parkingLot.showParkingStatus();
        }else if (exitMatcher.matches()) {
            parkingLot.exitConsole();
        }

    }

}
