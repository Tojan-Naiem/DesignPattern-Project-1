package org.example.View;

import org.example.Data.Data;
import org.example.Models.Event;

import java.util.Optional;

 public class EventDisplayView {

    public void displayAllEvents() {
        System.out.println("\n" + ConsoleUI.CYAN + ConsoleUI.BOLD + "🌟 AVAILABLE EVENTS 🌟" + ConsoleUI.RESET);

        if (Data.events.isEmpty()) {
            ConsoleUI.printInfoMessage("No events available in the system.");
            return;
        }

        ConsoleUI.printSuccessMessage("System Events:");
        for (Event event : Data.events) {
            System.out.println(ConsoleUI.BLUE + "  🎪 ID: " + event.getId() +
                    " | Type: " + event.getEventType() + ConsoleUI.RESET);
        }
    }

    public Event getEventById(int id) {
        Optional<Event> event = Data.events.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        return event.orElse(null);
    }
}
