package eu.hobbydevs.slimechunk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Main.positions.put(event.getPlayer(), event.getPlayer().getLocation());
    }

}
