package org.example.Controller;

import org.example.Models.Event;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule {
    private EventBus eventBus;
    private ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(10);
    public Schedule(EventBus eventBus){
        this.eventBus=eventBus;
    }
    public void start(Event event, long time){
        if(event==null) throw new NullPointerException("Event is null");
        scheduledExecutorService.scheduleAtFixedRate(
                ()->{
                    try {
                        eventBus.publish(event);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },0,time, TimeUnit.SECONDS
        );

    }
    public void stop(){
        scheduledExecutorService.shutdown();
    }
}
