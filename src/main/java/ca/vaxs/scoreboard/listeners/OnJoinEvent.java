package ca.vaxs.scoreboard.listeners;

import java.util.List;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import ca.vaxs.scoreboard.Main;

public class OnJoinEvent implements Listener {

    private static Main main = null;

    public OnJoinEvent(Main main) {
        OnJoinEvent.main = main;
    }
    public static void loadScoreboard(Player p){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("title", "dummy");
        objective.setDisplayName(main.getConfig().getString("scoreboard.title").replace("&", "§"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        List<String> lines = main.getConfig().getStringList("scoreboard.lines");
        int numberOfLines = lines.size();
        int n = 1;
        while(n <= numberOfLines) {
            String text = lines.get(numberOfLines - n).replace("&", "§");
            Score score = objective.getScore(PlaceholderAPI.setPlaceholders(p, text));
            score.setScore(n);
            n++;
            p.setScoreboard(scoreboard);
        }
    }

    public static void unloadScoreboard(Player p){
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(main.getConfig().getBoolean("scoreboard.enable")) {
            loadScoreboard(player);
        }
        else {
            return;
        }
    }

    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(main.getConfig().getBoolean("scoreboard.enable")) {
            unloadScoreboard(player);
        }
        else {
            return;
        }
    }

}
