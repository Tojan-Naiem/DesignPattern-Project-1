# Event-Driven Notification System

A Java-based event notification system implementing the Observer pattern with asynchronous processing.

## Architecture Layer Diagrams

### Complete System Overview

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

### Data Layer
```mermaid
classDiagram
    class Data {
        +users: List<User>
        +events: List<Event>
        +subscribers: Map<String, List<User>>
    }

    %% Note: Data is a static storage class
    %% No internal relationships - it's a simple data container
```

### Layer-to-Layer Connections
```mermaid
graph TB
    subgraph "View Layer"
        MC[MainController]
        UV[UserView]
        AV[AdminView]
        AU[AuthenticationView]
    end
    
    subgraph "Controller Layer"
        EB[EventBus]
        PUB[Publisher]
        SCH[Schedule]
    end
    
    subgraph "Service Layer"
        NM[NotificationManager]
        IP[IPreference]
        ST[SpecificTime]
    end
    
    subgraph "Model Layer"
        U[User]
        E[Event]
    end
    
    subgraph "Data Layer"
        D[Data]
    end

    %% View to Controller
    MC --> EB
    MC --> PUB
    UV --> EB
    AV --> PUB

    %% Controller to Service
    EB --> NM
    
    %% Controller to Data
    EB --> D
    
    %% View to Data
    AU --> D
    
    %% Model to Service
    U --> IP
    
    %% Service to Model (indirect)
    NM --> U
    NM --> E
```mermaid
classDiagram
    class Main {
        +main(String[] args)
    }

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
        +setCreatedAt(LocalDateTime createdAt)
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

    class Data {
        +users: List<User>
        +events: List<Event>
        +subscribers: Map<String, List<User>>
    }

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

    class MainController {
        -eventBus: EventBus
        -publisher: Publisher
        -authView: AuthenticationView
        -userView: UserView
        -adminView: AdminView
        +MainController()
        +initialize()
        -setupInitialData()
        -handleLogin()
        -handleRegistration()
        -routeUserToInterface(User user)
    }

    class AuthenticationView {
        +displayLoginScreen() User
        +displayRegistrationScreen() User
        -authenticateUser(String email, String password) User
    }

    class UserView {
        -eventBus: EventBus
        -eventDisplayView: EventDisplayView
        +UserView(EventBus eventBus)
        +displayUserDashboard(User user)
        -handleEventSubscription(User user)
        -configureUserPreferences(User user)
    }

    class AdminView {
        -publisher: Publisher
        -eventDisplayView: EventDisplayView
        +AdminView(Publisher publisher)
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
        +getIntegerInput(String prompt) int
        +getStringInput(String prompt) String
        +validateRange(int value, int min, int max) boolean
        +clearBuffer()
    }

    class ConsoleUI {
        +RESET: String
        +RED: String
        +GREEN: String
        +YELLOW: String
        +BLUE: String
        +PURPLE: String
        +CYAN: String
        +BOLD: String
        +printWelcomeBanner()
        +printMainMenu()
        +printSuccessMessage(String message)
        +printErrorMessage(String message)
        +printInfoMessage(String message)
    }

    %% Relationships
    Main --> MainController : creates
    
    Publisher --> EventBus : uses
    Schedule --> EventBus : uses
    
    EventBus --> Data : accesses
    EventBus --> NotificationManager : calls
    
    User --> Event : subscribes to
    User --> IPreference : has
    SpecificTime ..|> IPreference : implements
    
    MainController --> EventBus : creates
    MainController --> Publisher : creates
    MainController --> AuthenticationView : uses
    MainController --> UserView : uses
    MainController --> AdminView : uses
    
    UserView --> EventBus : uses
    UserView --> EventDisplayView : uses
    AdminView --> Publisher : uses
    AdminView --> EventDisplayView : uses
    
    AuthenticationView --> Data : accesses
    EventDisplayView --> Data : accesses
    
    Data --> User : stores
    Data --> Event : stores
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
