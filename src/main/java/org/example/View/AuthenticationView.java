package org.example.View;

import org.example.Data.Data;
import org.example.Models.User;

public class AuthenticationView {

    public User displayLoginScreen() {
        System.out.println("\n" + ConsoleUI.BLUE + ConsoleUI.BOLD + "🔐 USER LOGIN 🛡️" + ConsoleUI.RESET);

        String email = InputHandler.getStringInput("📧 Email Address: ");
        String password = InputHandler.getStringInput("🔒 Password: ");

        User user = authenticateUser(email, password);
        if (user == null) {
            ConsoleUI.printErrorMessage("Invalid credentials! Please check your email and password.");
            return null;
        }

        ConsoleUI.printSuccessMessage("Welcome back, " + email + "!");
        return user;
    }

    public User displayRegistrationScreen() {
        System.out.println("\n" + ConsoleUI.GREEN + ConsoleUI.BOLD + "🎉 ACCOUNT REGISTRATION 🧙‍♂️" + ConsoleUI.RESET);
        ConsoleUI.printInfoMessage("Let's create your account!");

        String email = InputHandler.getStringInput("📧 Email Address: ");
        String password = InputHandler.getStringInput("🔒 Password: ");

        System.out.println(ConsoleUI.PURPLE + "\n🎭 Select Account Type:" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.BLUE + "  1. 👤 Regular User" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.RED + "  2. 👑 Administrator" + ConsoleUI.RESET);

        int roleChoice = InputHandler.getIntegerInput("Your choice (1-2): ");
        boolean isAdmin = (roleChoice == 2);

        User user = new User(email, password, isAdmin);
        Data.users.add(user);

        ConsoleUI.printSuccessMessage("Account created successfully!");
        ConsoleUI.printInfoMessage("Welcome to the system, " + email + "!");

        return user;
    }

    private User authenticateUser(String email, String password) {
        return Data.users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
