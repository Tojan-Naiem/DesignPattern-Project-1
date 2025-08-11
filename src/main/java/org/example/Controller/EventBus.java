package org.example.Controller;

import org.example.Data.Data;
import org.example.Models.Event;
import org.example.Models.User;
import org.example.Services.Notification.NotificationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EventBus {
    private static Logger log =Logger.getLogger(EventBus.class.getName());


    public void subscribe(User user, Event event){
        if(user==null||event==null){
            log.severe("user or event is null");
            throw new NullPointerException("user or event is null");

        }
        if(!Data.events.contains(event)||!Data.users.contains(user)){
            log.severe("user or event not found");
            throw new NullPointerException("Event is not found");
        }


        Data.subscribers.computeIfAbsent(event.getEventType(),key->new ArrayList<>()).add(user);

        Data.users.stream()
                .filter(u -> u.equals(user))
                .findFirst()
                .ifPresent(u -> {
                  u.addEvent(event);

                });
        log.info(" Subscriber "+user.getEmail()+" have been subscribed successfully to the event "+event.getEventType());

    }



    public  boolean publish(Event event) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(event==null) throw new NullPointerException("event is null");
        if(!Data.events.contains(event))throw new NullPointerException("event is not found");

        List<User> allUsers = Data.subscribers.values().stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        NotificationManager.notify(event,allUsers);

        return true;
    }
}
