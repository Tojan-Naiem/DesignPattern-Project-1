package Controller;

import org.example.Controller.EventBus;
import org.example.Controller.Publisher;
import org.example.Models.Event;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@DisplayName("Publisher Test")
class PublisherTest {
    Publisher publisher;
    EventBus eventBus;
    @BeforeAll
    static void setUP(){
        System.out.println("Publisher  Test Setup");

    }


    @BeforeEach
    @DisplayName("Set Up")
    void init(){
        eventBus=mock(EventBus.class);
        publisher=new Publisher(eventBus);
    }
    @Test
    @DisplayName("Set Up with parameter constructor")
    void initWithConstructor(){
        publisher=new Publisher(eventBus);
    }
    @Test
    @DisplayName("Publish with null event")
    void publishWithNullEvent(){
        Event event=null;
        assertThrows(NullPointerException.class,()->{
            publisher.publish(event);
        });
    }
    @Test
    @DisplayName("Publish with  event")
    void publishWithEvent() throws Exception{
        Event event=new Event();
        event.setEventType("Task");
        publisher.publish(event);
        verify(eventBus).publish(event);
    }


}