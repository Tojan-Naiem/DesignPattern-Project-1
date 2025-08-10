package org.example.Data;

import org.example.Models.Event;
import org.example.Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    public static List<User> users=new ArrayList<>();
    public static List<Event> events=new ArrayList<>();
    public static Map<String, List<User>> subscribers=new HashMap<>();


}
