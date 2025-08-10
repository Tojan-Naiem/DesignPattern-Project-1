package org.example.Controller;

import org.example.Models.Event;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Schedule Test")
class ScheduleTest {
    Schedule schedule;
    EventBus eventBus;
    @BeforeAll
    static void setUP(){
        System.out.println("Schedule  Test Setup");

    }


    @BeforeEach
    @DisplayName("Set Up")
    void init(){
        eventBus=mock(EventBus.class);
        schedule=new Schedule(eventBus);
    }
    @Test
    @DisplayName("Set Up with parameter constructor")
    void initWithConstructor(){
        schedule=new Schedule(eventBus);
    }
    @Test
    @DisplayName("start method with event and time")
    void start() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Event event=new Event();
        event.setEventType("Task");
        schedule.start(event,10);
        verify(eventBus).publish(event);
    }

}