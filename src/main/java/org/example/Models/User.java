package org.example.Models;



import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int id;
    private String email;
    private String password;
    private boolean isAdmin=false;
    private List<Event> registeredEvent=new ArrayList<>();
    private Map<String,Boolean> notifications=new HashMap<>();
    public User(){

    }
    public User(String email,String password,boolean isAdmin){
        this.email=email;
        this.password=password;
        this.isAdmin=isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Event> getRegisteredEvent() {
        return registeredEvent;
    }

    public void setRegisteredEvent(List<Event> registeredEvent) {
        this.registeredEvent = registeredEvent;
    }
    public boolean addEvent(Event event){
        return registeredEvent.add(event);
    }
    public void addNotification(String msg,boolean isMuted){

        notifications.put(msg,isMuted);
    }

    public void getNotifications() {
            LocalTime now = LocalTime.now();

            System.out.println("🔔 Visible notifications for " + email + ":");
            for (Map.Entry<String, Boolean> entry : notifications.entrySet()) {
                String content = entry.getKey();
                boolean isMuted = entry.getValue();
                    System.out.println("✅ " + content);

            }

    }

    public void setNotifications(Map<String, Boolean> notifications) {
        this.notifications = notifications;
    }


}
