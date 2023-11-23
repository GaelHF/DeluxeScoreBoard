package ca.vaxs.scoreboard.commands;

import ca.vaxs.scoreboard.listeners.OnJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.vaxs.scoreboard.Main;
import net.md_5.bungee.api.ChatColor;

public class ScoreboardCommand implements CommandExecutor {

    private Main main;

    public ScoreboardCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(args.length >= 1)
            {
                if(args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(ChatColor.GOLD + "COMMANDS:");
                    player.sendMessage(ChatColor.YELLOW + "/dsb toggle" + ChatColor.AQUA + " [Enable and disable the scoreboard]");
                    return true;
                }

                //Toggle
                if(args[0].equalsIgnoreCase("toggle")) {
                    boolean isEnable = main.getConfig().getBoolean("scoreboard.enable");
                    if(isEnable) {
                        main.getConfig().set("scoreboard.enable", false);
                        player.sendMessage(main.getConfig().getString("prefix").replace("&", "§") + ChatColor.GOLD + " Scoreboard is now: " + ChatColor.AQUA + "disable");
                        return true;
                    }
                    else {
                        main.getConfig().set("scoreboard.enable", true);
                        player.sendMessage(main.getConfig().getString("prefix").replace("&", "§") + ChatColor.GOLD + " Scoreboard is now: " + ChatColor.AQUA + "enable");
                        return true;
                    }


                }
            }


            else if(args.length == 0) {
                player.sendMessage(main.getConfig().getString("prefix").replace("&", "§") + ChatColor.RED+" Try: " + ChatColor.YELLOW + "/dsb help");
                return true;
            }


        }

        return false;
    }

}
