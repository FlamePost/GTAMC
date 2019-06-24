package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.mobs.MobManager;
import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.OWNER) {
                MobManager.spawnCop(p);
            } else {
                p.sendMessage(MessageUtils.NO_PERMISSION);
            }
        }
        return true;
    }

}
