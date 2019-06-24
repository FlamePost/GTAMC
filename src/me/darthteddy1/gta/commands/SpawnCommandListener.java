package me.darthteddy1.gta.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpawnCommandListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ()) return;
        if(SpawnCommand.spawning.contains(e.getPlayer())) {
            SpawnCommand.spawning.remove(e.getPlayer());
        }
    }

}
