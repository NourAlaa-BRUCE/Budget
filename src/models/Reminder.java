package src.models;

import java.time.LocalDateTime;

public class Reminder {
    private String title;
    private LocalDateTime dateTime;

    public Reminder(String title, LocalDateTime dateTime) {
        this.title    = title;
        this.dateTime = dateTime;
    }

    public String getTitle()           { return title; }
    public LocalDateTime getDateTime(){ return dateTime; }

    @Override
    public String toString() {
        return dateTime + " | " + title;
    }
}
