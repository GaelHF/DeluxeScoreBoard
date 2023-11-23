package ca.vaxs.scoreboard;

import org.bukkit.plugin.java.JavaPlugin;

import ca.vaxs.scoreboard.commands.ScoreboardCommand;
import ca.vaxs.scoreboard.listeners.OnJoinEvent;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[Deluxe ScoreBoard]: Plugin loaded");
        saveDefaultConfig();
        getCommand("dsb").setExecutor(new ScoreboardCommand(this));
        getServer().getPluginManager().registerEvents(new OnJoinEvent(this), this);
    }

}