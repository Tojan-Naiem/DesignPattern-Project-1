package org.example.View;

public class ConsoleUI {
    // ANSI Color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static void printWelcomeBanner() {
        System.out.println(CYAN + BOLD);
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                              ║");
        System.out.println("║        🚀 Event-Driven Notification System 🌟               ║");
        System.out.println("║                                                              ║");
        System.out.println("║     Professional Event Management & Notification Platform   ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    public static void printMainMenu() {
        System.out.println("\n" + PURPLE + BOLD + "🌟 MAIN MENU 🌟" + RESET);
        System.out.println(YELLOW + "┌─────────────────────────────────┐" + RESET);
        System.out.println(GREEN + "│  1. 🔐 Login                    │" + RESET);
        System.out.println(BLUE + "│  2. ✨ Create Account           │" + RESET);
        System.out.println(RED + "│  3. 👋 Exit                     │" + RESET);
        System.out.println(YELLOW + "└─────────────────────────────────┘" + RESET);
        System.out.print(CYAN + "Please select an option: " + RESET);
    }

    public static void printGoodbyeMessage() {
        System.out.println(PURPLE + BOLD);
        System.out.println("\n✨ Thank you for using our Event Management System! ✨");
        System.out.println("🚀 Have a great day! 🌟");
        System.out.println("👋 Goodbye! 🌌" + RESET);
    }

    public static void printSuccessMessage(String message) {
        System.out.println(GREEN + "✅ " + message + RESET);
    }

    public static void printErrorMessage(String message) {
        System.out.println(RED + "❌ " + message + RESET);
    }

    public static void printInfoMessage(String message) {
        System.out.println(CYAN + "ℹ️ " + message + RESET);
    }

    public static void printWarningMessage(String message) {
        System.out.println(YELLOW + "⚠️ " + message + RESET);
    }
}


