package org.example.Controller;

import org.example.Models.Event;
import org.example.Models.User;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Schedule {
    private static Logger log =Logger.getLogger(Schedule.class.getName());

    private EventBus eventBus;
    private ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(10);
    public Schedule(EventBus eventBus){
        this.eventBus=eventBus;
        log.info(" Schedule created with the event bus : "+ LocalDateTime.now().toString());

    }
    public void start(Event event, long time){
        if(event==null) throw new NullPointerException("Event is null");
        log.info(LocalDateTime.now().toString()+" Start publishing the Schedule ");

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
