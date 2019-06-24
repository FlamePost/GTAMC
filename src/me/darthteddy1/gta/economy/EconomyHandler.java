package me.darthteddy1.gta.economy;

import me.darthteddy1.gta.Core;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class EconomyHandler {

    public static HashMap<Player, Integer> balances = new HashMap<>();

    public static Integer getBalanceMYSQL(Player p) {
        try {
            Statement statement = Core.getSQLConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM balances WHERE uuid = '" + p.getUniqueId() + "';");
            if (res.next()) {
                if (res.getString("uuid") != null) {
                    return res.getInt("bal");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public static Integer getBalance(Player p) {
        return balances.get(p);
    }

    public static void setBalance(Player p, Integer i) {
        try {
            if(balances.containsKey(p)) {
                balances.replace(p, balances.get(p), i);
            } else {
                balances.put(p,i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBalanceMYSQL(Player p, Integer i) {
        try {
            if(getBalanceMYSQL(p) == -1) {
                Statement statement = Core.getSQLConnection().createStatement();
                statement.executeUpdate("INSERT INTO balances (`uuid`, `bal`) VALUES ('" + p.getUniqueId() + "', '" + i + "');");
            } else {
                Statement statement = Core.getSQLConnection().createStatement();
                statement.executeUpdate("UPDATE `balances` SET `bal` = '" + i + "' WHERE `uuid` = '" + p.getUniqueId() +    "';");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

