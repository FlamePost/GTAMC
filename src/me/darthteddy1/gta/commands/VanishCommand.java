package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class VanishCommand implements Listener,CommandExecutor {

    ArrayList<Player> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.MOD || RankManager.getRank(p) == Rank.OWNER) {
                if(vanished.contains(p)) {
                    for(Player pp : Bukkit.getOnlinePlayers()) {
                        pp.showPlayer(p);
                    }
                    sender.sendMessage(MessageUtils.PREFIX + "§aYou have unvanished!");
                    vanished.remove(p);
                } else {
                    for(Player pp : Bukkit.getOnlinePlayers()) {
                        pp.hidePlayer(p);
                    }
                    sender.sendMessage(MessageUtils.PREFIX + "§aYou have vanished!");
                    vanished.add(p);
                }
            } else {
                sender.sendMessage(MessageUtils.NO_PERMISSION);
            }
        }
        return true;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
            vanished.remove(p);
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(vanished.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(MessageUtils.PREFIX + "§cYou may not pickup items whilst in vanish");
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(vanished.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(MessageUtils.PREFIX + "§cYou may not drop items whilst in vanish");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (vanished.contains(p)) {
                e.setCancelled(true);
                p.sendMessage(MessageUtils.PREFIX + "§cYou may not damage players whilst in vanish");
            }
        }
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (vanished.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

}
