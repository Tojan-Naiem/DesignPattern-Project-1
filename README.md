# Event-Driven Notification System

A Java-based event notification system implementing the Observer pattern with asynchronous processing.

## Features

- **Event-Driven Architecture**: Centralized event bus for publishers and subscribers
- **Asynchronous Processing**: Non-blocking notification delivery
- **User Management**: Login, registration, and role-based access (Admin/User)
- **Notification Preferences**: Configurable quiet hours and filtering
- **Professional Console UI**: Clean interface with ANSI colors

## Quick Start

```bash
# Clone and build
git clone <repository-url>
cd EventDrivenNotificationSystem
mvn clean compile

# Run application
mvn exec:java -Dexec.mainClass="org.example.Main"

# Run tests
mvn test
```

## Default Test Accounts

| Role | Email | Password |
|------|--------|----------|
| Admin | `admin@system.com` | `admin123` |
| User | `user@system.com` | `user123` |
| Demo | `demo@system.com` | `demo123` |

## Design Patterns

- **Observer Pattern**: EventBus manages publisher-subscriber relationships
- **MVC Pattern**: Separation of Models, Views, and Controllers
- **Strategy Pattern**: IPreference interface for different notification preferences
- **Singleton Pattern**: Static NotificationManager for centralized notification handling

## Architecture

```
org.example/
├── Controller/     # EventBus, Publisher, Schedule
├── Models/        # Event, User
├── Services/      # NotificationManager, Preferences
├── View/          # UI components and controllers
└── Data/          # In-memory storage
```

## Testing

- **Unit Tests**: Core business logic with JUnit 5
- **Mock Testing**: Mockito for dependencies and external calls
- **Integration Tests**: Event flow and notification delivery
- **Coverage**: EventBus, Publisher, and Schedule components

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=EventBusTest
```

## Requirements

- Java 17+
- Maven 3.6+
- 512MB RAM minimum

## Key Usage

1. **Login/Register** → Choose user type (Admin/Regular)
2. **Subscribe to Events** → Select from available event types
3. **Configure Preferences** → Set quiet hours for notifications
4. **Admin Features** → Create and publish events to all subscribers
