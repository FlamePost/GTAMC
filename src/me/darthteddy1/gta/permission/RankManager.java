package me.darthteddy1.gta.permission;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class RankManager implements Listener {

    public static HashMap<Player, Rank> ranks = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(Rank.getRankByPlayer(p) != null) {
            ranks.put(p, Rank.getRankByPlayer(p));
        } else {
            Rank.setRank(p, Rank.DEFAULT);
            ranks.put(p, Rank.DEFAULT);
        }
        p.setPlayerListName(getRank(p).getPrefix() + " §e" + p.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
            ranks.remove(e.getPlayer());
    }

    public static Rank getRank(Player p) {
        return ranks.get(p);
    }
}
