package Controller;

import org.example.Controller.EventBus;
import org.example.Data.Data;
import org.example.Models.Event;
import org.example.Models.User;
import org.example.Services.Notification.NotificationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@DisplayName("Event Bus Test ")
class EventBusTest {
    EventBus eventBus;
    @BeforeAll
    static void setUP(){
        System.out.println("Event Bus Test Setup");

    }

    @Test
    @DisplayName("is instantiated  Even Bus ")
    void isInitializeWithNew(){
        new EventBus();
    }
    @BeforeEach
    @DisplayName("Set Up")
    void init(){
        eventBus=new EventBus();
    }
    @Test
    @DisplayName("Subscribe null user")
    void subscribeNullUser(){
        User user=null;
        Event event=mock(Event.class);
        assertThrows(NullPointerException.class,()->{
            eventBus.subscribe(user,event);
        });

    }
    @Test
    @DisplayName("Subscribe with null event")
    void subscribeNullEvent(){
        User user=mock(User.class);
        Event event=null;
        assertThrows(NullPointerException.class,()->{
            eventBus.subscribe(user,event);
        });

    }
    @Test
    @DisplayName("Subscribe with null event and null user")
    void subscribeNullEventNullUser(){
        User user=null;
        Event event=null;
        assertThrows(NullPointerException.class,()->{
            eventBus.subscribe(user,event);
        });

    }

    @Test
    @DisplayName("Subscribe with  event unfound in the event list and  user - false")
    void subscribeUserWithUnfoundEvent(){
        User user=new User();
        Event event=mock(Event.class);
        Data.users.add(user);
        assertThrows(NullPointerException.class,
                ()->
        {
            eventBus.subscribe(user,event);
        });
    }
    @Test
    @DisplayName("Subscribe with user unfound in the user list and  event - false")
    void subscribeEventWithUnfoundUser(){
        User user=mock(User.class);
        Event event=new Event();
        Data.events.add(event);
        assertThrows(NullPointerException.class,
                ()->
                {
                    eventBus.subscribe(user,event);
                });
    }
    @Test
    @DisplayName("Subscribe with  unfound user and event")
    void subscribeWithUnfoundUserEvent(){
        User user=mock(User.class);
        Event event=mock(Event.class);
        assertThrows(NullPointerException.class,
                ()->
                {
                    eventBus.subscribe(user,event);
                });
    }
    @Test
    @DisplayName("Subscribe with  event and  user - true")
    void subscribeUserEventReturnTrue(){
        User user=new User();
        Event event=new Event();
        user.setId(UUID.randomUUID().toString());
        event.setEventType("Task");

        Data.users.add(user);
        Data.events.add(event);
        eventBus.subscribe(user,event);
        assertTrue(Data.subscribers.get(event.getEventType()).contains(user));
        assertTrue(user.getRegisteredEvent().contains(event));

    }

    @Test
    @DisplayName("Publish null event")
    void publishNullEvent(){
        Event event=null;
        assertThrows(NullPointerException.class,()->{
            eventBus.publish(event);
        });
    }
    @Test
    @DisplayName("Publish event not in the list")
    void publishNotFoundEvent(){
        Event event=new Event();
        assertThrows(NullPointerException.class,()->{
            eventBus.publish(event);
        });
    }
    @Test
    @DisplayName("Publish event not in the list")
    void publishEventSubscribers() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Event event = new Event();
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        event.setEventType("Task");
        Data.events.add(event);
        Data.users.add(user);
        Data.subscribers.put(event.getEventType(), new ArrayList<>(List.of(user)));
        try (MockedStatic<NotificationManager> mocked = Mockito.mockStatic(NotificationManager.class)) {
            boolean result = eventBus.publish(event);

            // Assert
            assertTrue(result);
            mocked.verify(() -> NotificationManager.notify(event, List.of(user)));
        }
    }



}