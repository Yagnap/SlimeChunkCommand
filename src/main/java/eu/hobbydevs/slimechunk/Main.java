package eu.hobbydevs.slimechunk;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("sc").setExecutor(new SlimeChunkCommand());
        getCommand("slimechunk").setExecutor(new SlimeChunkCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
