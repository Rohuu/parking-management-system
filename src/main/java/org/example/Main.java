package org.example;

import org.example.domain.ParkingLot;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static ParkingLot parkingLot;

    public static void main(String[] args) {
        printIntroduction();

        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        while (!exitRequested) {
            String command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("exit")) {
                exitRequested = true;
            } else {
                executeCommand(command);
            }
        }
    }

    private static void executeCommand(String command) {
        try {
            if (parkingLot == null && !command.startsWith("create_parking_lot")) {
                throw new IllegalStateException("Please create a parking lot first.");
            }

            // Compile regular expressions for commands
            Pattern createPattern = Pattern.compile("create_parking_lot (\\d+)");
            Pattern parkPattern = Pattern.compile("park (\\S+) (\\S+)");
            Pattern leavePattern = Pattern.compile("leave (\\d+)");
            Pattern registrationNoPattern = Pattern.compile("registration_numbers_for_cars_with_colour (\\S+)");
            Pattern statusPattern = Pattern.compile("status");
            Pattern exitPattern = Pattern.compile("exit");

            Matcher createMatcher = createPattern.matcher(command);
            Matcher parkMatcher = parkPattern.matcher(command);
            Matcher leaveMatcher = leavePattern.matcher(command);
            Matcher registrationNoMatcher = registrationNoPattern.matcher(command);
            Matcher statusMatcher = statusPattern.matcher(command);
            Matcher exitMatcher = exitPattern.matcher(command);

            // Execute corresponding command
            if (createMatcher.matches()) {
                executeCreateParkingLotCommand(createMatcher);
            } else if (parkMatcher.matches()) {
                executeParkCarCommand(parkMatcher);
            } else if (leaveMatcher.matches()) {
                executeLeaveCommand(leaveMatcher);
            } else if (registrationNoMatcher.matches()) {
                executeRegistrationNumbersCommand(registrationNoMatcher);
            } else if (statusMatcher.matches()) {
                executeStatusCommand();
            } else if (exitMatcher.matches()) {
                executeExitCommand();
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void executeCreateParkingLotCommand(Matcher matcher) {
        int capacity = Integer.parseInt(matcher.group(1));
        parkingLot = new ParkingLot(capacity);
        System.out.println("Created a parking lot with " + capacity + " slots");
    }

    private static void executeParkCarCommand(Matcher matcher) {
        String registrationNumber = matcher.group(1);
        String color = matcher.group(2);
        int parkedSlotNum = parkingLot.parkCar(registrationNumber, color);
        if (parkedSlotNum > 0) {
            System.out.println("Allocated slot number: " + parkedSlotNum);
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    private static void executeLeaveCommand(Matcher matcher) {
        int slotNo = Integer.parseInt(matcher.group(1));
        int freeSlotNum = parkingLot.leave(slotNo);
        if (freeSlotNum > 0) {
            System.out.println("Slot number " + freeSlotNum + " is free");
        }
    }

    private static void executeRegistrationNumbersCommand(Matcher matcher) {
        String color = matcher.group(1);
        List<String> registrationNumbers = parkingLot.getRegistrationNumbersForColor(color);
        if (!registrationNumbers.isEmpty()) {
            System.out.println(registrationNumbers);
        } else {
            System.out.println("No cars found with color " + color);
        }
    }

    private static void executeStatusCommand() {
        parkingLot.showParkingStatus();
    }

    private static void executeExitCommand() {
        parkingLot.exitConsole();
    }

    private static void printIntroduction() {
        System.out.println("***************************************************************************************");
        System.out.println("**********************  WELCOME TO PARKING SYSTEM APPLICATION  ************************");
        System.out.println("*******************************  SAMPLE INPUT COMMANDS  *******************************");
        System.out.println("***************************************************************************************");
        System.out.println("$ create_parking_lot {capacity}");
        System.out.println("$ park {registration_no} {color}");
        System.out.println("$ leave {slot_number}");
        System.out.println("$ registration_numbers_for_cars_with_colour {color}");
        System.out.println("$ status");
        System.out.println("$ exit");
        System.out.println("--Enter a command--");
    }

}