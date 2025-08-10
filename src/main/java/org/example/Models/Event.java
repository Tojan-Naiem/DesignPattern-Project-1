package org.example.Models;


import java.time.LocalDateTime;

public class Event {
    private int id;
    private LocalDateTime createdAt;
    private String eventType;
    public Event(){
 this.createdAt=LocalDateTime.now();
    }
    public Event (int id,String eventType){
        this.id=id;
        this.eventType=eventType;
        this.createdAt=LocalDateTime.now();
    }
    public int getId() {
        return this.id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventType() {
        return this.eventType.toString();
    }
}
