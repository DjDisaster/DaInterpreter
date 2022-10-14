package interpreter.dainterpreter.Main;

import interpreter.dainterpreter.Effects.BasicEffects;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Class.forName;

public class Execute extends BasicEffects {
    public static HashMap<String, String> effects = new HashMap<String, String>();
    public static void Execute(String syntax, HashMap<String, String> args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] split = syntax.split("");
        // delete all cancel events from split
        for (int i = 0; i < split.length; i++) {
            if (split[i].equalsIgnoreCase("Cancel event")) {
                split[i] = "";
            }
        }
        ArrayList<String> Input = new ArrayList<>();
        ArrayList<String> Input2 = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("{")) {
                // loop until }
                String var = "";
                for (int j = i; j < split.length; j++) {
                    if (split[j].equals("}")) {
                        Input.add(var);
                        // remove characters in between { and }
                        for (int k = i; k < j; k++) {
                            split[k] = "";
                        }
                        split[i] = "%variable%";
                        split[j] = "";
                        break;
                    } else {
                        // if not } add to var
                        if (!split[j].equals("{")) {
                            var = var + split[j];
                        }
                    }
                }
                // broadcast var
            }
        }
        //rejoin split
        String joined = String.join("", split);
        Bukkit.broadcastMessage("Joined: " + joined);
        Bukkit.broadcastMessage("Input: " + Input.toString());
        Bukkit.broadcastMessage("Input2: " + Input2.toString());
        String i = effects.get(joined);
        Bukkit.broadcastMessage("I: " + i);
        // log effects map
        Bukkit.broadcastMessage("Effects: " + effects.toString());
        // loop through effects
        for (String key : effects.keySet()) {
            if (key.equalsIgnoreCase(joined)) {
                try {
                    Class<BasicEffects> daclass = BasicEffects.class;
                    Method method = daclass.getMethod(effects.get(key), ArrayList.class);
                    method.invoke(Input, Input);
                } catch (Exception e) {}
            }
        }

    }
}
