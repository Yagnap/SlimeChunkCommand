package eu.hobbydevs.slimechunk;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlimeChunkRadar implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1) {
                int range;
                String rawArgument = args[0];
                if(isInt(rawArgument)) {
                    range = Integer.parseInt(rawArgument);
                    int maxViewDistance = Bukkit.getServer().getViewDistance();

                    if(range > maxViewDistance) {
                        sender.sendMessage("§cThe maximum size of the radar is §6" + maxViewDistance + "§c.");
                    } else if(range <= 0) {
                        sender.sendMessage("§cYou can only get a radar with a size greater than 0.");
                    } else {
                        Main.radar.put(p, range);
                        sender.sendMessage("§aSuccessfully added radar. To turn it off, try §6/scradar off§a.");
                    }
                } else if(rawArgument.contains("off")) {

                    if(Main.radar.containsKey(p)) {
                        Main.radar.remove(p);
                        sender.sendMessage("§cSuccessfully turned slime chunk radar off");
                    } else {
                        sender.sendMessage("§cYou can only turn the slime chunk radar off if you activated it before.");
                    }

                } else {
                    sender.sendMessage("§cTo activate the radar, type §6/scradar [range]§c " +
                            "with range being a positive integer number or §6/scradar off§c to turn it off.");
                }

            } else {
                sender.sendMessage("§cThis command needs as its only argument the range of the radar.");
            }
        } else {
            sender.sendMessage("This command can only be performed by a player.");
        }

        return false;
    }

    /**
     * Checks if a String can be converted to an Integer.
     * @param string The string which should be checked.
     * @return Returns false if the provided String is not an Integer, true if it is.
     */
    private boolean isInt(String string) {
       try {
           Integer.parseInt(string);
           return true;
       } catch(NumberFormatException e)  {
           return false;
       }
    }

}
