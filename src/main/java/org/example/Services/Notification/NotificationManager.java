package org.example.Services.Notification;

import org.example.Models.Event;
import org.example.Models.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationManager {
    private static final ExecutorService executorService= Executors.newCachedThreadPool();
    public static void notify(Event event, List<User> subscribers) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        subscribers.forEach(subscriber -> {
            executorService.submit(()->subscriber.addNotification(event.getCreatedAt()+" You recevied a notification for the even  : "+event.getEventType(),false));
        });

    }
}
