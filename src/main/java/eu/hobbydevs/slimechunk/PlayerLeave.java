package eu.hobbydevs.slimechunk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if(Main.radar.containsKey(p)) Main.radar.remove(p);
        Main.positions.remove(p);
    }

}
