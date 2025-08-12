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
