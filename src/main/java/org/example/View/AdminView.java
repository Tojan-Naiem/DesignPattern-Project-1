package org.example.View;


import org.example.Controller.Publisher;
import org.example.Data.Data;
import org.example.Models.Event;

public class AdminView {
    private final Publisher publisher;
    private final EventDisplayView eventDisplayView;

    public AdminView(Publisher publisher) {
        this.publisher = publisher;
        this.eventDisplayView = new EventDisplayView();
    }

    public void displayAdminDashboard() throws Exception {
        while (true) {
            printAdminMenu();
            int choice = InputHandler.getIntegerInput();

            if (!InputHandler.validateRange(choice, 1, 4)) {
                ConsoleUI.printErrorMessage("Invalid option! Please select 1-4.");
                continue;
            }

            switch (choice) {
                case 1:
                    eventDisplayView.displayAllEvents();
                    break;
                case 2:
                    handleEventCreation();
                    break;
                case 3:
                    handleEventPublication();
                    break;
                case 4:
                    ConsoleUI.printInfoMessage("Returning to main menu...");
                    return;
            }
        }
    }

    private void printAdminMenu() {
        System.out.println("\n" + ConsoleUI.RED + ConsoleUI.BOLD + "👑 ADMINISTRATOR DASHBOARD 👑" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "┌────────────────────────────────────┐" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.GREEN + "│  1. 📊 View All Events             │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.BLUE + "│  2. ➕ Create New Event            │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.PURPLE + "│  3. 📡 Publish Event               │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.RED + "│  4. 🚪 Return to Main Menu         │" + ConsoleUI.RESET);
        System.out.println(ConsoleUI.YELLOW + "└────────────────────────────────────┘" + ConsoleUI.RESET);
        System.out.print(ConsoleUI.CYAN + "Select an option: " + ConsoleUI.RESET);
    }

    private void handleEventCreation() {
        System.out.println("\n" + ConsoleUI.GREEN + ConsoleUI.BOLD + "✨ EVENT CREATION ✨" + ConsoleUI.RESET);

        int id = InputHandler.getIntegerInput("Event ID: ");
        String type = InputHandler.getStringInput("Event Type: ");

        if (type == null) {
            ConsoleUI.printErrorMessage("Invalid event type!");
            return;
        }

        Event event = new Event(id, type);
        Data.events.add(event);

        ConsoleUI.printSuccessMessage("Event '" + type + "' created successfully!");
        ConsoleUI.printInfoMessage("Event ID: " + id + " is now available in the system.");
    }

    private void handleEventPublication() throws Exception {
        System.out.println("\n" + ConsoleUI.PURPLE + ConsoleUI.BOLD + "📡 EVENT PUBLICATION 📡" + ConsoleUI.RESET);
        eventDisplayView.displayAllEvents();

        int eventId = InputHandler.getIntegerInput("Select event ID to publish: ");
        Event event = eventDisplayView.getEventById(eventId);

        if (event == null) {
            ConsoleUI.printErrorMessage("Event not found! Please check the ID.");
            return;
        }

        publisher.publish(event);
        ConsoleUI.printSuccessMessage("Event '" + event.getEventType() + "' published successfully!");
        ConsoleUI.printInfoMessage("All subscribers have been notified.");
    }
}
