package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.Core;
import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor {

    Location l = new Location(Bukkit.getWorld("world"), 24,  71, -172);
    public static ArrayList<Player> spawning = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
           if(RankManager.getRank(p) == Rank.OWNER || RankManager.getRank(p) == Rank.ADMIN) {
               p.teleport(l);
           } else {
               p.sendMessage(MessageUtils.PREFIX + "Sending you to spawn in 5 seconds... Don't move!");
               spawning.add(p);
               spawnTimer(p, 5);
           }
        }
        return true;
    }



    private void spawnTimer(Player p, Integer t) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                if (!spawning.contains(p)) {
                    p.sendMessage(MessageUtils.PREFIX_BAD + "Spawn cancelled (§7Movement§c)");
                } else {
                    if (t > 0) {
                        spawnTimer(p, t - 1);
                        p.sendMessage("§r" + t + "...");
                    } else {
                        p.teleport(l);
                        spawning.remove(p);
                    }
                }
            }
        }, 20);
    }

}
