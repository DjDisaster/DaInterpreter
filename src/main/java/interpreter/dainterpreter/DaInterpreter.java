package interpreter.dainterpreter;

import interpreter.dainterpreter.Effects.BasicEffects;
import interpreter.dainterpreter.Events.BasicEvents;
import interpreter.dainterpreter.Events.Register;
import interpreter.dainterpreter.Events.EventRegister;
import interpreter.dainterpreter.Main.FileUtils;
import interpreter.dainterpreter.Main.Parser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Arrays;

public final class DaInterpreter extends JavaPlugin {

    public String Numbertype = "";
    public String Savetype = "";
    @Override
    public void onEnable() {
        // register event in TestEvent.java
        Register.LoadEvents();
        // register event in EventRegister.java
        getServer().getPluginManager().registerEvents(new EventRegister(), this);
        getServer().getPluginManager().registerEvents(new BasicEvents(), this);

        String[] files = FileUtils.getValidFiles();

        BasicEffects.AddSyntax();
        //Register.register(new PlayerJoinEvent(null, "yo"), "player join");

        for (String s : files) {
            try {
                Parser.Parse(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bukkit.broadcastMessage("Files: " + Arrays.toString(files));


        getLogger().info("DaInterpreter has been enabled!");
        // make folder
        getDataFolder().mkdirs();
        // create file in DaInterpreter folder
        File file = new File(getDataFolder(), "Config.txt");
        // create a folder in getDaFolder
        File folder = new File(getDataFolder(), "Scripts");
        // path to file
        folder.mkdirs();
        // make file in DaFolder
        File file2 = new File(folder, "-Info.txt");
        // write to file
        try {
            FileWriter writer = new FileWriter(file2);
            writer.write("" +
                    "Should you delete this file it will be re-created." + "\r"
                    + "This file is for information purposes. " + "\r"
                    + "Make a file in Scripts (just outside here)" + "\r"
                    + "and name it whatever you want. " + "\r"
                    + "End the file with .da" + "\r" + "\r"
                    + "\r\r\r"
                    + "To learn the basics join this discord: https://discord.gg/kjUFTmKf3m" + "\r"
                    + "This project was inspired by Skript." + "\r"
                    + "This project is still in development." + "\r"
                    + "Thank you for using DaInterpreter!" + "\r"
                    + "If you have any questions or suggestions join the discord." + "\r"
                    + "If you find any bugs please report them on the discord." + "\r"
                    + "If you want to help with the project ask in the discord." + "\r"+ "\r"
                    + "What are you waiting for? Get started!" + "\r"+ "\r"
                    + "Any file starting with a - will be ignored." + "\r"


            );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //write to file
        // write to Config.txt
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("# This is a Configuration file" + "\r"
                        + "# You are able to configure things from here!" + "\r"
                        + "# Have fun!" + "\r"
                        + "#VERSION: 1.0" + "\r"
                        + "\r\r\r\r"
                        + "# Valid Types: Int, Double, Float, BigDecimal, All" + "\r"
                        + "# INT LIMIT: 2147483647" + "\r"
                        + "# DOUBLE LIMIT: 1.6e308" + "\r"
                        + "# FLOAT LIMIT: 1.7e308" + "\r"
                        + "# BigDecimal LIMIT: NONE" + "\r"
                        + "# Using All will swap between a Double and BigDecimal" + "\r"
                        + "# All will be a double while under 1.6e308 and BigDecimal over." + "\r"
                        + "# Please keep in mind that BigDecimals are SLOWER than Doubles." + "\r"
                        + "Number Type: " + "Double"
                        + "\r\r\r\r"
                        + "#Data Storage System" + "\r" + "" // hashmap or treemap
                        + "# Valid Types: HashMap, TreeMap" + "\r"
                        + "# HashMap is faster, but TreeMap is more accurate. " + "\r"
                        + "Data Storage Type: " + "HashMap"
                        + "\r\r\r\r"
                        + "VERSION 1.0"
                        + "DᴀIɴᴛᴇʀᴘʀᴇᴛᴇʀ"
                );
                // next line
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // loop through file
        // if line starts with #, ignore
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("#") || line.startsWith(" ")) {
                    continue;
                }
                // if line starts with Number Type, set number type
                if (line.startsWith("Number Type: ")) {
                    Numbertype = line.substring(13);
                    // broadcast type
                    // check if type is valid
                    if (Numbertype.equalsIgnoreCase("Int") || Numbertype.equalsIgnoreCase("Double") || Numbertype.equalsIgnoreCase("Float") || Numbertype.equalsIgnoreCase("BigDecimal") || Numbertype.equalsIgnoreCase("All")) {
                        getLogger().warning("Config: Loaded Numbertype as: " + Numbertype);
                    } else {
                        // log config error
                        getLogger().log(java.util.logging.Level.SEVERE, "Config Error: Invalid Number Type!");
                        getLogger().warning("Using default type: Double");
                        Numbertype = "Double";
                    }
                }
                // if line starts with Data Storage Type, set data storage type
                if (line.startsWith("Data Storage Type: ")) {
                    Savetype = line.substring(19);
                    // broadcast type
                    // check if type is valid
                    if (Savetype.equalsIgnoreCase("HashMap") || Savetype.equalsIgnoreCase("TreeMap")) {
                        getLogger().warning("Config: Loaded Savetype as: " + Savetype);
                    } else {
                        // log config error
                        getLogger().log(java.util.logging.Level.SEVERE, "Config Error: Invalid Data Storage Type!");
                        getLogger().warning("Using default type: HashMap");
                        Savetype = "HashMap";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read files in folder
    }

    @Override
    public void onDisable() {
        // log
        getLogger().info("DaInterpreter has been disabled!");
        // Plugin shutdown logic
    }
}
