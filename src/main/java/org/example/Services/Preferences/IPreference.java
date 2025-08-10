package org.example.Services.Preferences;

import org.example.Models.User;

import java.util.function.Predicate;

public interface IPreference {
    Predicate<User> filter( );
}
