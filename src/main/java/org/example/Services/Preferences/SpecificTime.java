package org.example.Services.Preferences;

import org.example.Models.User;

import java.time.LocalTime;
import java.util.function.Predicate;

public class SpecificTime implements IPreference{
    private final int startWorkHour;
    private final int endWorkHour;

    public SpecificTime(int startWorkHour, int endWorkHour) {
        this.startWorkHour = startWorkHour;
        this.endWorkHour = endWorkHour;
    }

    public int getEndWorkHour() {
        return endWorkHour;
    }

    public int getStartWorkHour() {
        return startWorkHour;
    }

    @Override
    public Predicate<User> filter() {
        LocalTime now = LocalTime.now();
        return user -> {
            LocalTime start = LocalTime.of(startWorkHour, 0);
            LocalTime end = LocalTime.of(endWorkHour, 0);
            return !now.isBefore(start) && !now.isAfter(end);
        };    }
}
