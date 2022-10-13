package interpreter.dainterpreter.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class BasicEvents implements Listener {
    @EventHandler

    // abstract class for events
    public static void joinEvent() {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onJoin(PlayerJoinEvent event) {
                Player player = event.getPlayer();
            }
        }, interpreter.dainterpreter.DaInterpreter.getPlugin(interpreter.dainterpreter.DaInterpreter.class));
    }



    //CheckEvent.Events.put("On Join:", new PlayerJoinEvent(null, "yo"));

}
