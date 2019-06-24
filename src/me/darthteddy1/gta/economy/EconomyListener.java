package me.darthteddy1.gta.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EconomyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(EconomyHandler.getBalance(p) == null) {
            if(EconomyHandler.getBalanceMYSQL(p) == -1) {
                EconomyHandler.setBalance(p, 100);
                EconomyHandler.setBalanceMYSQL(p, 100);
            } else {
                EconomyHandler.setBalance(p, EconomyHandler.getBalanceMYSQL(p));
            }
        } else {
            EconomyHandler.setBalance(p, EconomyHandler.getBalanceMYSQL(p));
        }
    }

}
