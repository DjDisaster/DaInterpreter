package interpreter.dainterpreter.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckEvent {
    public static HashMap<String, Event> Events = new HashMap<>();
    public static void checkEvent(String syntax, ArrayList ray) {
        // loop through Events
        for (String key : Events.keySet()) {
            if (key.equalsIgnoreCase(syntax)) {
                Bukkit.broadcastMessage("Event " + syntax + " found" + ray.toString());
                Register.register(Events.get(key), syntax, ray);
            }
        }
    }
}
