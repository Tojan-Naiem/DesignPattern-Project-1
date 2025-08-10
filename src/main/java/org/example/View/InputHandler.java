package org.example.View;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntegerInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            ConsoleUI.printErrorMessage("Please enter a valid number!");
            scanner.nextLine(); // Clear invalid input
            return -1;
        }
    }

    public static String getStringInput() {
        try {
            return scanner.next();
        } catch (Exception e) {
            ConsoleUI.printErrorMessage("Invalid input! Please try again.");
            return null;
        }
    }

    public static String getStringInput(String prompt) {
        System.out.print(ConsoleUI.YELLOW + prompt + ConsoleUI.RESET);
        return getStringInput();
    }

    public static int getIntegerInput(String prompt) {
        System.out.print(ConsoleUI.YELLOW + prompt + ConsoleUI.RESET);
        return getIntegerInput();
    }

    public static boolean validateRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static void clearBuffer() {
        scanner.nextLine();
    }
}
