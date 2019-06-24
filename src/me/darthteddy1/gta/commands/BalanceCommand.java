package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.economy.EconomyHandler;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
                p.sendMessage(MessageUtils.PREFIX_GOOD + "§aBalance: $" + EconomyHandler.getBalance(p));
        }
        return true;
    }

}
