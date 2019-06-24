package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.Core;
import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.Statement;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.OWNER || RankManager.getRank(p) == Rank.MOD || RankManager.getRank(p) == Rank.HELPER) {
                if(args.length == 1) {
                    if(Bukkit.getPlayer(args[0]) != null) {
                        if(isMuted(Bukkit.getPlayer(args[0]))) {
                            unmute(Bukkit.getPlayer(args[0]));
                            p.sendMessage(MessageUtils.PREFIX_BAD + "You have unmuted " + args[0]);
                        } else {
                            mute(Bukkit.getPlayer(args[0]));
                            p.sendMessage(MessageUtils.PREFIX_GOOD + "You have muted " + args[0]);
                        }
                    }
                }
            } else {
                sender.sendMessage(MessageUtils.NO_PERMISSION);
            }
        }
        return true;
    }

    private void mute(Player p) {
        try {
            Statement statement = Core.getSQLConnection().createStatement();
            statement.executeUpdate("INSERT INTO mutes (`uuid`) VALUES ('" + p.getUniqueId() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unmute(Player p) {
        try {
            Statement statement = Core.getSQLConnection().createStatement();
            statement.executeUpdate("DELETE FROM mutes WHERE uuid = '" + p.getUniqueId() + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isMuted(Player p) {
        try {
            Statement statement = Core.getSQLConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM mutes WHERE uuid = '" + p.getUniqueId() + "';");
            if (res.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
