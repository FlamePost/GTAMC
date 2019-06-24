package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.OWNER) {
                if(p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(MessageUtils.PREFIX_BAD + "§cYou are no longer flying");
                } else {
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(MessageUtils.PREFIX_GOOD + "§aYou are now flying");
                }
            } else {
                p.sendMessage(MessageUtils.NO_PERMISSION);
            }
        }
        return true;
    }

}
