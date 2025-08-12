# Event-Driven Notification System

A Java-based event notification system implementing the Observer pattern with asynchronous processing.

## Architecture Layer Diagrams

### Complete System Overview

```mermaid
classDiagram
    class Main {
        +main(args)
    }

    class EventBus {
        +subscribe(user, event)
        +publish(event)
    }

    class Publisher {
        -eventBus: EventBus
        +publish(event)
    }

    class Event {
        -id: int
        -eventType: String
        +getId()
        +getEventType()
    }

    class User {
        -id: String
        -email: String
        -isAdmin: boolean
        +addEvent(event)
    }

    class Data {
        +users: List
        +events: List
        +subscribers: Map
    }

    class NotificationManager {
        +notify(event, subscribers)
    }

    class IPreference {
        <<interface>>
        +filter()
    }

    class SpecificTime {
        -startWorkHour: int
        -endWorkHour: int
        +filter()
    }

    class MainController {
        -eventBus: EventBus
        -publisher: Publisher
        +initialize()
    }

    class UserView {
        -eventBus: EventBus
        +displayUserDashboard(user)
    }

    class AdminView {
        -publisher: Publisher
        +displayAdminDashboard()
    }

    %% Relationships
    Main --> MainController
    Publisher --> EventBus
    EventBus --> Data
    User --> Event
    SpecificTime ..|> IPreference
    MainController --> UserView
    MainController --> AdminView
```

### View Layer
```mermaid
classDiagram
    class MainController {
        -eventBus: EventBus
        -publisher: Publisher
        -authView: AuthenticationView
        -userView: UserView
        -adminView: AdminView
        +initialize()
        +handleLogin()
        +handleRegistration()
    }

    class AuthenticationView {
        +displayLoginScreen() User
        +displayRegistrationScreen() User
        -authenticateUser() User
    }

    class UserView {
        -eventBus: EventBus
        -eventDisplayView: EventDisplayView
        +displayUserDashboard(User user)
        -handleEventSubscription(User user)
        -configureUserPreferences(User user)
    }

    class AdminView {
        -publisher: Publisher
        -eventDisplayView: EventDisplayView
        +displayAdminDashboard()
        -handleEventCreation()
        -handleEventPublication()
    }

    class EventDisplayView {
        +displayAllEvents()
        +getEventById(int id) Event
    }

    class InputHandler {
        -scanner: Scanner
        +getIntegerInput() int
        +getStringInput() String
        +validateRange() boolean
    }

    class ConsoleUI {
        +printWelcomeBanner()
        +printMainMenu()
        +printSuccessMessage()
        +printErrorMessage()
    }

    %% View Layer Internal Relationships
    MainController --> AuthenticationView : uses
    MainController --> UserView : uses
    MainController --> AdminView : uses
    UserView --> EventDisplayView : uses
    AdminView --> EventDisplayView : uses
    UserView --> InputHandler : uses
    AdminView --> InputHandler : uses
    AuthenticationView --> InputHandler : uses
    UserView --> ConsoleUI : uses
    AdminView --> ConsoleUI : uses
    AuthenticationView --> ConsoleUI : uses
```

### Controller Layer
```mermaid
classDiagram
    class EventBus {
        +subscribe(User user, Event event)
        +publish(Event event) boolean
    }

    class Publisher {
        -eventBus: EventBus
        +Publisher()
        +Publisher(EventBus eventBus)
        +publish(Event event)
    }

    class Schedule {
        -eventBus: EventBus
        -scheduledExecutorService: ScheduledExecutorService
        +Schedule(EventBus eventBus)
        +start(Event event, long time)
        +stop()
    }

    %% Controller Layer Internal Relationships
    Publisher --> EventBus : uses
    Schedule --> EventBus : uses
```

### Service Layer
```mermaid
classDiagram
    class NotificationManager {
        -executorService: ExecutorService
        +notify(Event event, List<User> subscribers)
    }

    class IPreference {
        <<interface>>
        +filter() Predicate<User>
    }

    class SpecificTime {
        -startWorkHour: int
        -endWorkHour: int
        +SpecificTime(int startWorkHour, int endWorkHour)
        +getEndWorkHour() int
        +getStartWorkHour() int
        +filter() Predicate<User>
    }

    %% Service Layer Internal Relationships
    SpecificTime ..|> IPreference : implements
```

### Model Layer
```mermaid
classDiagram
    class Event {
        -id: int
        -createdAt: LocalDateTime
        -eventType: String
        +Event()
        +Event(int id, String eventType)
        +getId() int
        +getCreatedAt() LocalDateTime
        +getEventType() String
        +setEventType(String eventType)
    }

    class User {
        -id: String
        -email: String
        -password: String
        -isAdmin: boolean
        -registeredEvent: List<Event>
        -notifications: Map<String, Boolean>
        -preferences: List<IPreference>
        +User()
        +User(String email, String password, boolean isAdmin)
        +addEvent(Event event) boolean
        +addNotification(String msg, boolean isMuted)
        +getNotifications()
        +addPreference(IPreference preference)
    }

    %% Model Layer Internal Relationships
    User --> Event : contains list of
```

### Data Layer
```mermaid
classDiagram
    class Data {
        +users: List<User>
        +events: List<Event>
        +subscribers: Map<String, List<User>>
    }
```

### Cross-Layer Communication
```mermaid
graph TB
    subgraph "📱 View Layer"
        MC[MainController]
        UV[UserView]
        AV[AdminView]
        AU[AuthenticationView]
    end
    
    subgraph "🎮 Controller Layer"
        EB[EventBus]
        PUB[Publisher]
        SCH[Schedule]
    end
    
    subgraph "⚙️ Service Layer"
        NM[NotificationManager]
        ST[SpecificTime]
    end
    
    subgraph "📊 Model Layer"
        U[User]
        E[Event]
    end
    
    subgraph "💾 Data Layer"
        D[Data]
    end

    MC --> EB
    MC --> PUB
    UV --> EB
    AV --> PUB
    EB --> NM
    EB --> D
    AU --> D
    U --> ST
    NM --> U
    NM --> E
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
