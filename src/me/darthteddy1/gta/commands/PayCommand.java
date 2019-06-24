package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.economy.EconomyHandler;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 2) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    Player pp = Bukkit.getPlayer(args[0]);
                    Player p = (Player) sender;
                    int amt = Integer.parseInt(args[1]);
                    if(amt > 0) {
                        if(EconomyHandler.getBalance(p) >= amt) {
                            EconomyHandler.setBalance(p, EconomyHandler.getBalance(p)-amt);
                            EconomyHandler.setBalance(pp, EconomyHandler.getBalance(pp)+amt);
                            p.sendMessage(MessageUtils.PREFIX_GOOD + "You have sent $" + amt + " to §c" + pp.getName());
                            pp.sendMessage(MessageUtils.PREFIX_GOOD + "You have received $" + amt + " from §c" + pp.getName());
                        } else {
                            p.sendMessage(MessageUtils.PREFIX_BAD + "You do not have enough $ for this transaction");
                        }
                    } else {
                        p.sendMessage(MessageUtils.PREFIX_BAD + "The amount must be greater than 0");
                    }
                }
            }
        }
        return true;
    }

}
