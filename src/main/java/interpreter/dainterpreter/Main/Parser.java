package interpreter.dainterpreter.Main;

import interpreter.dainterpreter.Events.CheckEvent;
import interpreter.dainterpreter.Events.Register;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.units.qual.A;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {
    public static HashMap<String,List<List<String>>> Parsed = new HashMap<String, List<List<String>>>();
    public static void Parse(String file) throws IOException {
        String[] result = FileUtils.readFile(file);
        Bukkit.broadcastMessage("Parsing " + file);
        ArrayList lines = new ArrayList();
        String event = "";
        // new arraylist named input
        ArrayList input = new ArrayList();
        for (int i = 0; i < result.length; i++) {
            int indentation = result[i].length() - result[i].replace("\t", "").length();
            result[i] = result[i].replace("\t", "");
            // add result[i] to a list

            if (indentation == 0) {
                // if event != ""
                if (!event.equals("")) {
                    if (Parsed.get(event) == null) {
                        Parsed.put(event, lines);
                    } else {
                        List<List<String>> newlines = Parsed.get(event);
                        newlines.addAll(lines);
                        Parsed.put(event, newlines);
                    }
                    event = "";
                    lines = new ArrayList();

                }
                event = result[i];
                Bukkit.broadcastMessage("Event: " + event);
            } else {
                lines.add(result[i]);
            }
        }
        if (Parsed.get(event) == null) {
            Parsed.put(event, lines);
        } else {
            List<List<String>> newlines = Parsed.get(event);
            newlines.addAll(lines);
            Parsed.put(event, newlines);
        }
        Bukkit.broadcastMessage("Parser" + Parser.Parsed.toString());
    }
}
