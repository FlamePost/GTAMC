package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.economy.EconomyHandler;
import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DebugRankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
         Rank.setRank(Bukkit.getPlayer(args[0]), Rank.OWNER);
        } else if(sender instanceof Player) {
            Player p = (Player) sender;
            RankManager.ranks.put(p, Rank.getRankByPlayer(p));
            EconomyHandler.setBalanceMYSQL(p, 100);
        }
        return true;
    }


}
