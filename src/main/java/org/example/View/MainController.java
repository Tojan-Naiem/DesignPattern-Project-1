package org.example.View;

import org.example.Controller.EventBus;
import org.example.Controller.Publisher;
import org.example.Data.Data;
import org.example.Models.Event;
import org.example.Models.User;

public class MainController {
    private final EventBus eventBus;
    private final Publisher publisher;
    private final AuthenticationView authView;
    private final UserView userView;
    private final AdminView adminView;

    public MainController() {
        this.eventBus = new EventBus();
        this.publisher = new Publisher(eventBus);
        this.authView = new AuthenticationView();
        this.userView = new UserView(eventBus);
        this.adminView = new AdminView(publisher);
    }

    public void initialize() throws Exception {
        setupInitialData();
        ConsoleUI.printWelcomeBanner();

        while (true) {
            ConsoleUI.printMainMenu();
            int choice = InputHandler.getIntegerInput();

            if (!InputHandler.validateRange(choice, 1, 3)) {
                ConsoleUI.printErrorMessage("Invalid option! Please select 1-3.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegistration();
                    break;
                case 3:
                    ConsoleUI.printGoodbyeMessage();
                    return;
            }
        }
    }

    private void handleLogin() throws Exception {
        User user = authView.displayLoginScreen();
        if (user != null) {
            routeUserToInterface(user);
        }
    }

    private void handleRegistration() throws Exception {
        User user = authView.displayRegistrationScreen();
        routeUserToInterface(user);
    }

    private void routeUserToInterface(User user) throws Exception {
        if (user.isAdmin()) {
            adminView.displayAdminDashboard();
        } else {
            userView.displayUserDashboard(user);
        }
    }

    private void setupInitialData() {
        // Initialize default users
        Data.users.add(new User("admin@system.com", "admin123", true));
        Data.users.add(new User("user@system.com", "user123", false));
        Data.users.add(new User("demo@system.com", "demo123", false));

        // Initialize default events
        Data.events.add(new Event(1, "TASK_CREATED"));
        Data.events.add(new Event(2, "USER_LOGIN"));
        Data.events.add(new Event(3, "FILE_UPLOADED"));
        Data.events.add(new Event(4, "EMAIL_SENT"));
        Data.events.add(new Event(5, "ORDER_PLACED"));
    }
}
