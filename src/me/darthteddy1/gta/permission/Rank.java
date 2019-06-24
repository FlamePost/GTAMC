package me.darthteddy1.gta.permission;

import me.darthteddy1.gta.Core;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.Statement;

public enum Rank {


    OWNER("§4§lOWNER", "owner"),
    ADMIN("§c§lADMIN", "admin"),
    MOD("§6§lMOD", "mod"),
    HELPER("§b§lHELPER", "helper"),
    MAFIABOSS("§3§lMOB BOSS", "mafiaboss"),
    HITMAN("§2§lHITMAN", "hitman"),
    HOODLUM("§a§lHOODLUM", "hoodlum"),
    CROOK("§3§lCROOK", "crook"),
    DEFAULT("", "default");

    String pref;
    String nam;

     Rank(String prefix, String name) {
        pref = prefix;
        nam = name;
    }

    public String getPrefix() {
        return pref;
    }

    public String getStringedName() {
         return nam;
    }

    public static Rank getRankByPlayer(Player p) {
        try {
            Statement statement = Core.getSQLConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM ranks WHERE uuid = '" + p.getUniqueId() + "';");
            if (res.next()) {
                if (res.getString("uuid") != null) {
                    if (res.getString("rank").equalsIgnoreCase("default")) {
                        return Rank.DEFAULT;
                    } else if (res.getString("rank").equalsIgnoreCase("crook")) {
                        return Rank.CROOK;
                    } else if (res.getString("rank").equalsIgnoreCase("hoodlum")) {
                        return Rank.HOODLUM;
                    } else if (res.getString("rank").equalsIgnoreCase("hitman")) {
                        return Rank.HITMAN;
                    } else if (res.getString("rank").equalsIgnoreCase("mafiaboss")) {
                        return Rank.MAFIABOSS;
                    } else if (res.getString("rank").equalsIgnoreCase("helper")) {
                        return Rank.HELPER;
                    } else if (res.getString("rank").equalsIgnoreCase("mod")) {
                        return Rank.MOD;
                    } else if (res.getString("rank").equalsIgnoreCase("admin")) {
                        return Rank.ADMIN;
                    } else if (res.getString("rank").equalsIgnoreCase("owner")) {
                        return Rank.OWNER;
                    }
                }
            }
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

    public static void setRank(Player p, Rank r) {
             try {
                 if(getRankByPlayer(p) == null) {
                     Statement statement = Core.getSQLConnection().createStatement();
                     statement.executeUpdate("INSERT INTO ranks (`uuid`, `rank`) VALUES ('" + p.getUniqueId() + "', '" + r.getStringedName() + "');");
                 } else {
                     Statement statement = Core.getSQLConnection().createStatement();
                     statement.executeUpdate("UPDATE `ranks` SET `rank` = '" + r.getStringedName() + "' WHERE `uuid` = '" + p.getUniqueId() +    "';");
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
    }

