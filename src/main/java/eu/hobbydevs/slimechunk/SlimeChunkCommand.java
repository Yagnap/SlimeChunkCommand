package eu.hobbydevs.slimechunk;

import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SlimeChunkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {

                player.sendMessage("§aThis chunk is " + (player.getLocation().getChunk().isSlimeChunk() ? "" : "§cnot§a ") + "a slimechunk.");

            } else {
                sender.sendMessage("Slime Chunks only exist in the overworld.");
            }

        } else {
            sender.sendMessage("This command can only be performed by a player.");
        }

        return false;
    }
}
