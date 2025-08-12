# Event-Driven Notification System

A Java-based event notification system implementing the Observer pattern with asynchronous processing.

## UML Class Diagram

```mermaid
classDiagram
    class EventBus {
        -observers: List<IObserver>
        -eventQueue: Queue<Event>
        +subscribe(observer)
        +unsubscribe(observer)
        +publishEvent(event)
        +notifyObservers(event)
    }

    class IObserver {
        <<interface>>
        +update(event)
    }

    class User {
        -email: String
        -password: String
        -role: UserRole
        -preferences: IPreference
        -subscribedEventTypes: List<String>
        +subscribe(eventType)
        +unsubscribe(eventType)
        +update(event)
    }

    class Event {
        -id: String
        -type: String
        -title: String
        -description: String
        -timestamp: LocalDateTime
        -priority: Priority
    }

    class Publisher {
        -eventBus: EventBus
        +publishEvent(event)
    }

    class Schedule {
        -scheduledEvents: Map
        -eventBus: EventBus
        +scheduleEvent(event, time)
        +processScheduledEvents()
    }

    class NotificationManager {
        -instance: NotificationManager
        -users: List<User>
        -eventBus: EventBus
        +getInstance()
        +sendNotification(user, event)
    }

    class IPreference {
        <<interface>>
        +shouldReceiveNotification(event)
        +getQuietHours()
    }

    class DefaultPreference {
        -quietHours: TimeRange
        -mutedEventTypes: Set<String>
        +shouldReceiveNotification(event)
    }

    class ConsoleUI {
        -notificationManager: NotificationManager
        -eventBus: EventBus
        -currentUser: User
        +start()
        +showMainMenu()
    }

    class DataStore {
        -users: Map<String, User>
        -events: List<Event>
        +saveUser(user)
        +getUser(email)
    }

    %% Relationships
    User ..|> IObserver
    DefaultPreference ..|> IPreference
    User --> IPreference
    User --> UserRole
    Event --> Priority
    Publisher --> EventBus
    Schedule --> EventBus
    EventBus --> IObserver
    NotificationManager --> User
    NotificationManager --> EventBus
    ConsoleUI --> NotificationManager
    ConsoleUI --> EventBus
    DataStore --> User
    DataStore --> Event
```

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

## Key Component Interactions

### Observer Pattern Flow
1. **EventBus** acts as the central hub managing all observers (Users)
2. **Publisher** creates events and sends them to EventBus
3. **EventBus** notifies all subscribed Users about relevant events
4. **NotificationManager** processes notifications based on user preferences

### User Preference System
- **IPreference** interface allows different notification strategies
- **DefaultPreference** for regular users with muting and quiet hours
- **AdminPreference** for administrators with priority notifications
- **TimeRange** utility for quiet hours functionality

### Data Flow
1. Admin creates event via **ConsoleUI**
2. **Publisher** publishes event to **EventBus**
3. **EventBus** notifies subscribed **Users**
4. **NotificationManager** applies user **Preferences**
5. Notifications delivered based on user settings

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
