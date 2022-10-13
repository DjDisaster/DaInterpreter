package interpreter.dainterpreter.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;

// extends EventRegister

public class Register extends EventRegister {
    public static HashMap<String, Event> events = new HashMap<String, Event>();
    public static void register(Event thisevent, String syntax, ArrayList ray) {
        events.put(syntax, thisevent);
        Bukkit.broadcastMessage("Registered event " + thisevent.getEventName() + " with syntax " + syntax);
        //EventRegister.registerEvent(thisevent, syntax, ray);
    }

    public static void LoadEvents() {
        BasicEvents.joinEvent();
    }

    public static Boolean compareSyntax(String s) {
        // compare syntax to events
        for (String key : events.keySet()) {
            if (key.equals(s)) {
                return true;
            }
        }
        return false;
    }
    // compare syntax to event

}

