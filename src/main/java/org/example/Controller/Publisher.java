package org.example.Controller;

import org.example.Data.Data;
import org.example.Models.Event;

import java.util.Optional;
import java.util.function.Predicate;

public class Publisher {
    EventBus eventBus;
    public Publisher(){
        this.eventBus=new EventBus();
    }
    public Publisher(EventBus eventBus){
        this.eventBus=eventBus;
    }
    public void publish(Event event) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(event==null)throw new NullPointerException("Event is null");
        eventBus.publish(event);
    }
}
