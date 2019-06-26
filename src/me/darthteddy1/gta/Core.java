package me.darthteddy1.gta;

import me.darthteddy1.gta.cars.NMSUtils;
import me.darthteddy1.gta.cars.RidablePig;
import me.darthteddy1.gta.commands.*;
import me.darthteddy1.gta.economy.EconomyHandler;
import me.darthteddy1.gta.economy.EconomyListener;
import me.darthteddy1.gta.guns.GunListener;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.scoreboard.GTAScoreboard;
import me.darthteddy1.gta.wanted.WantedLevelManager;
import me.darthteddy1.gta.world.WorldListener;
import net.minecraft.server.v1_8_R3.EntityPig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.Statement;

import me.darthteddy1.gta.mysql.mysql.MySQL;

public class Core extends JavaPlugin {

    /*
    * Guns
    * Houses
    * Chests
    * Drugs
    * Wild TP
    * Trades
    * Missions
    *
    * */

    private MySQL MySQL = new MySQL("127.0.0.1", "3306", "gtamc", "root", "");
    private static Connection c = null;
    static Core _core;

    @Override
    public void onEnable() {
        try {
            c = MySQL.openConnection();
            Statement statement = c.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ranks (uuid VARCHAR(255), rank VARCHAR(255))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mutes (uuid VARCHAR(255))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS balances (uuid VARCHAR(255), bal INT)");

        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("[GTAMC] Error connecting to MySQL database");
            System.out.println(" ");
            e.printStackTrace();
            Bukkit.shutdown();
        }
        _core = this;
        getCommand("guns").setExecutor(new GunCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("drank").setExecutor(new DebugRankCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("summoncop").setExecutor(new SpawnCopCommand());
        getCommand("summoncar").setExecutor(new SpawnCarCommand());
        getCommand("setrank").setExecutor(new SetRankCommand());
        getCommand("bal").setExecutor(new BalanceCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getServer().getPluginManager().registerEvents(new GunListener(), this);
        getServer().getPluginManager().registerEvents(new RankManager(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        getServer().getPluginManager().registerEvents(new WantedLevelManager(), this);
        getServer().getPluginManager().registerEvents(new SpawnCommandListener(), this);
        getServer().getPluginManager().registerEvents(new EconomyListener(), this);

        NMSUtils.registerEntity("Pig", 90, EntityPig.class, RidablePig.class);



        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    GTAScoreboard.sendScoreboard(p);
                }
            }
        },20,20);
    }


     @Override
    public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.kickPlayer("§c§lReloading Server");
        }
        for(Player p : EconomyHandler.balances.keySet()) {
            EconomyHandler.setBalanceMYSQL(p, EconomyHandler.getBalance(p));
        }
    }


    public static Connection getSQLConnection() {
        return c;
    }

    public static Core getInstance() {
        return _core;
    }

}
