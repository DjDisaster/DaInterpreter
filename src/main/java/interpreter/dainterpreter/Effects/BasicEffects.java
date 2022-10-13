package interpreter.dainterpreter.Effects;


import interpreter.dainterpreter.Main.Execute;
import interpreter.dainterpreter.Main.Variables;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;

public class BasicEffects<Variable> {
    public static String AddOne(ArrayList input) {
        Variables.Add(String.valueOf(input.get(0)), 1.0);
        System.out.println("ALL VARIABLES | " + Variables.variables.toString());
        Bukkit.broadcastMessage("Ran AddOne");
        Bukkit.broadcastMessage("Input: " + input.get(0));
        return "test";
    }
    public static String RemoveOne(ArrayList input) {
        Variables.Add(String.valueOf(input.get(0)), -1.0);
        System.out.println("ALL VARIABLES | " + Variables.variables.toString());
        Bukkit.broadcastMessage("Ran AddOne");
        Bukkit.broadcastMessage("Input: " + input.get(0));
        return "test";
    }
    public static String Set(String[] args) {
        Variables.Add(args[0], 1.0);
        System.out.println("ALL VARIABLES | " + Variables.variables.toString());
        return "test";
    }
    public static String BlowUpWorld() {
        Bukkit.broadcastMessage("Blowing up world");
        return "test";
    }
    public static void AddSyntax() {
        AddSyntax("%variable%++", "AddOne");
        AddSyntax("%variable%--", "RemoveOne");
        AddSyntax("%variable% = %object%", "Set");
    }
    public static void AddSyntax(String syntax, String thing) {
        Execute.effects.put(syntax, thing);
    }
}
