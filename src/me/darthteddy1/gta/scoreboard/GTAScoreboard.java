package me.darthteddy1.gta.scoreboard;

import me.darthteddy1.gta.economy.EconomyHandler;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.wanted.WantedLevelManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class GTAScoreboard {

    public static void sendScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("gtaboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§b§lGTAMC");
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        Score score9 = objective.getScore("§6§lServer: §rGTA-1");
        score9.setScore(10);
        Score score10 = objective.getScore("§a");
        score10.setScore(9);

        Score score7 = objective.getScore("§6§lUser: §r" + p.getName());
        score7.setScore(8);
        Score score8 = objective.getScore("§0");
        score8.setScore(7);

        Score score5 = objective.getScore("§6§lRank: " + RankManager.getRank(p).getPrefix());
        score5.setScore(6);
        Score score6 = objective.getScore("§1");
        score6.setScore(5);

        Score score = objective.getScore("§6§lWanted Level: §r" + WantedLevelManager.getWantedLevel(p).toString());
        score.setScore(4);
        Score score2 = objective.getScore("§2");
        score2.setScore(3);

        Score score3 = objective.getScore("§6§lBalance: §r$" + EconomyHandler.getBalance(p));
        score3.setScore(2);

        p.setScoreboard(board);
    }

}
