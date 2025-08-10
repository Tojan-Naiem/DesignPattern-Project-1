package org.example.View;

import org.example.Controller.EventBus;
import org.example.Models.Event;
import org.example.Models.User;
import org.example.Services.Preferences.SpecificTime;

class UserView {
    private final EventBus eventBus;
    private final EventDisplayView eventDisplayView;

    public UserView(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventDisplayView = new EventDisplayView();
    }

    public void displayUserDashboard(User user) {
        while (true) {
            printUserMenu();
            int choice = InputHandler.getIntegerInput();

            if (!InputHandler.validateRange(choice, 1, 5)) {
                ConsoleUI.printErrorMessage("Invalid option! Please select 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayUserSubscriptions(user);
                    break;
                case 2:
                    handleEventSubscription(user);
                    break;
                case 3:
                    displayUserNotifications(user);
                    break;
                case 4:
                    configureUserPreferences(user);
                    break;
                case 5:
                    ConsoleUI.printInfoMessage("Returning to main menu...");
                    return;
            }
        }
    }

    private void printUserMenu() {
        System.out.println("\n" + ConsoleUI.PURPLE + ConsoleUI.BOLD + "🌟 USER DASHBOARD 🌟" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.CYAN + "┌──────────────────────────────────────┐" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.GREEN + "│  1. 📋 View My Subscriptions         │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.BLUE + "│  2. ➕ Subscribe to Event            │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "│  3. 📬 View Notifications            │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.PURPLE + "│  4. ⚙️ Manage Preferences            │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.RED + "│  5. 🚪 Return to Main Menu           │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.CYAN + "└──────────────────────────────────────┘" + ConsoleUI.RESET);
        System.out.print(ConsoleUI.YELLOW + "Select an option: " + ConsoleUI.RESET);
    }

    private void displayUserSubscriptions(User user) {
        System.out.println("\n" + ConsoleUI.BLUE + ConsoleUI.BOLD + "📋 YOUR SUBSCRIPTIONS 📋" + ConsoleUI.RESET);

        if (user.getRegisteredEvent().isEmpty()) {
            ConsoleUI.printInfoMessage("You haven't subscribed to any events yet.");
            ConsoleUI.printInfoMessage("Use option 2 to subscribe to events!");
            return;
        }

        ConsoleUI.printSuccessMessage("Your active subscriptions:");
        user.getRegisteredEvent().forEach(event -> {
            System.out.println(ConsoleUI.PURPLE + "  🎫 " + event.getEventType() + ConsoleUI.RESET);
        });
    }

    private void handleEventSubscription(User user) {
        System.out.println("\n" + ConsoleUI.GREEN + ConsoleUI.BOLD + "🎫 EVENT SUBSCRIPTION 🎫" + ConsoleUI.RESET);
        eventDisplayView.displayAllEvents();

        int eventId = InputHandler.getIntegerInput("Enter event ID to subscribe: ");
        Event event = eventDisplayView.getEventById(eventId);

        if (event == null) {
            ConsoleUI.printErrorMessage("Event not found! Please check the ID.");
            return;
        }

        eventBus.subscribe(user, event);
        ConsoleUI.printSuccessMessage("Successfully subscribed to: " + event.getEventType());
    }

    private void displayUserNotifications(User user) {
        System.out.println("\n" + ConsoleUI.YELLOW + ConsoleUI.BOLD + "📬 YOUR NOTIFICATIONS 📬" + ConsoleUI.RESET);
        user.getNotifications();
    }

    private void configureUserPreferences(User user) {
        System.out.println("\n" + ConsoleUI.PURPLE + ConsoleUI.BOLD + "⚙️ PREFERENCE MANAGEMENT ⚙️" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "┌─────────────────────────────────────┐" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.BLUE + "│  1. 🔕 Configure Quiet Hours        │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.GREEN + "│  2. ⭐ Priority Settings (Soon)     │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "└─────────────────────────────────────┘" + ConsoleUI.RESET);

        int choice = InputHandler.getIntegerInput("Select preference type: ");

        switch (choice) {
            case 1:
                configureQuietHours(user);
                break;
            case 2:
                ConsoleUI.printWarningMessage("Priority settings will be available in future updates!");
                break;
            default:
                ConsoleUI.printErrorMessage("Invalid choice!");
        }
    }

    private void configureQuietHours(User user) {
        System.out.println("\n" + ConsoleUI.BLUE + ConsoleUI.BOLD + "🌙 QUIET HOURS CONFIGURATION 🌙" + ConsoleUI.RESET);
        ConsoleUI.printInfoMessage("Set hours when you don't want to receive notifications.");

        int startHour = InputHandler.getIntegerInput("Start hour (0-23): ");
        int endHour = InputHandler.getIntegerInput("End hour (0-23): ");

        if (!InputHandler.validateRange(startHour, 0, 23) || !InputHandler.validateRange(endHour, 0, 23)) {
            ConsoleUI.printErrorMessage("Invalid hours! Please use 0-23 format.");
            return;
        }

        SpecificTime quietTime = new SpecificTime(startHour, endHour);
        user.addPreference(quietTime);

        ConsoleUI.printSuccessMessage("Quiet hours configured successfully!");
        ConsoleUI.printInfoMessage("Do Not Disturb: " + startHour + ":00 to " + endHour + ":00");
    }
}

