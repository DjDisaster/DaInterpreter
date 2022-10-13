package interpreter.dainterpreter.Events;

import interpreter.dainterpreter.DaInterpreter;
import interpreter.dainterpreter.Main.Execute;
import interpreter.dainterpreter.Main.Parser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.EventExecutor;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventRegister implements Listener {
    // check for player join
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String syntax = "On Join:";
        HashMap<String, Object> localvar = new HashMap<String, Object>();
        localvar.put("player", event.getPlayer().getName());
        Bukkit.broadcastMessage("Parser" + Parser.Parsed.toString());
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String syntax = "";
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            syntax = "On Left Click:";
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            syntax = "On Right Click:";
        }
        ArrayList thiskey = new ArrayList();
        for (String key : Parser.Parsed.keySet()) {
            if (key.equalsIgnoreCase(syntax)) {
                thiskey = (ArrayList) Parser.Parsed.get(key);
            }
        }
        // check if thiskey is set
        Bukkit.broadcastMessage("thiskey: " + thiskey);
        if (thiskey != null) {
            HashMap<String, String> localvar = new HashMap<String, String>();
            localvar.put("Player", event.getPlayer().getName());

            if (event.getClickedBlock() != null) {
                localvar.put("Block", event.getClickedBlock().getType().toString());
            }
            localvar.put("Action", event.getAction().toString());
            Bukkit.broadcastMessage("Parser" + Parser.Parsed.toString());
            for (int i = 0; i < thiskey.size(); i++) {
                Bukkit.broadcastMessage("THISKEY " + thiskey.get(i).toString());
                Execute.Execute(thiskey.get(i).toString(), localvar);
                Bukkit.broadcastMessage("LocalVar" + localvar.toString());
            }
        }
    }

    }
