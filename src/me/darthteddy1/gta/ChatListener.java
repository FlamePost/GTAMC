package me.darthteddy1.gta;

import me.darthteddy1.gta.commands.MuteCommand;
import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (MuteCommand.isMuted(p)) {
            e.setCancelled(true);
            p.sendMessage(MessageUtils.PREFIX_BAD + "You are muted, and cannot talk in chat!");
        } else {
            if (RankManager.getRank(p) == Rank.OWNER || RankManager.getRank(p) == Rank.ADMIN) {
                e.setFormat(RankManager.getRank(p).getPrefix() + " §e" + p.getName() + " §c" + ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            } else if (RankManager.getRank(p) == Rank.DEFAULT) {
                e.setFormat(RankManager.getRank(p).getPrefix() + " §e" + p.getName() + " §r" + e.getMessage());
            } else {
                e.setFormat(RankManager.getRank(p).getPrefix() + " §e" + p.getName() + " §7" + e.getMessage());
            }
        }
    }
}
