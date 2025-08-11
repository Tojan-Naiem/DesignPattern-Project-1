package org.example.Services.Preferences;

import org.example.Models.User;

import java.time.LocalTime;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class SpecificTime implements IPreference{
    private final int startWorkHour;
    private final int endWorkHour;
    private static Logger log =Logger.getLogger(SpecificTime.class.getName());

    public SpecificTime(int startWorkHour, int endWorkHour) {
        this.startWorkHour = startWorkHour;
        this.endWorkHour = endWorkHour;
        log.info("Setting up dafault time");
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
