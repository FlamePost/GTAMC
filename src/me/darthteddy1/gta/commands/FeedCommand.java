package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.OWNER || RankManager.getRank(p) == Rank.HELPER
                    || RankManager.getRank(p) == Rank.MOD || RankManager.getRank(p) == Rank.MAFIABOSS || RankManager.getRank(p) == Rank.HITMAN) {
                p.setFoodLevel(20);
                p.setSaturation(20);
                p.sendMessage(MessageUtils.PREFIX_GOOD + "§aYou have been fed");
            } else {
                p.sendMessage(MessageUtils.NO_PERMISSION);
                p.sendMessage("§7Want this perk and many others? You can purchase a rank at " + MessageUtils.WEBSTORE);
            }
        }
        return true;
    }

}
