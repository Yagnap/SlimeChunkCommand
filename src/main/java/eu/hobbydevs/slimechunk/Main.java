package eu.hobbydevs.slimechunk;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public final class Main extends JavaPlugin {

    /**
     * players who are signed up for the radar and the range of the radar
     */
    public static HashMap<Player, Integer> radar = new HashMap<>();
    public static HashMap<Player, Location> positions = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("sc").setExecutor(new SlimeChunkCommand());
        getCommand("scradar").setExecutor(new SlimeChunkRadar());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);

        new BukkitRunnable() {
            public void run() {
                for(Player p : radar.keySet()) {
                    Location loc = p.getLocation();

                    Chunk oldPos = positions.get(p).getChunk();
                    Chunk newPos = loc.getChunk();

                    // if chunk is not the same
                    if(newPos.getX() != oldPos.getX() || newPos.getZ() != oldPos.getZ()) {
                        String radarOutput = "";
                        int range = radar.get(p);

                        int x = loc.getBlockX();
                        int z = loc.getBlockZ();

                        for(int i = -range; i <= range; i++) {
                            for(int j = -range; j <= range; j++) {
                                String color;
                                long seed = p.getWorld().getSeed();

                                // player coordinates to chunk coordinates
                                int xPosition = (int) Math.ceil(((double)(x + j * 16))/16);
                                int zPosition = (int) Math.ceil(((double)(z + i * 16))/16);

                                Random rnd = new Random(seed + (long) (xPosition * xPosition * 0x4c1906) + (long) (xPosition * 0x5ac0db) + (long) (zPosition * zPosition) * 0x4307a7L + (long) (zPosition * 0x5f24f) ^ 0x3ad8025f);
                                if(rnd.nextInt(10) == 0) {
                                    color = i == 0 && j == 0 ? "§a" : "§2";
                                } else {
                                    color = i == 0 && j == 0 ? "§c" : "§4";
                                }
                                radarOutput +=  color + "\u25A0";

                            }
                            radarOutput += "\n";
                        }

                        p.sendMessage("--\n" + radarOutput + "§e\u25B2N");

                        positions.remove(p);
                        positions.put(p, loc);

                    }

                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
