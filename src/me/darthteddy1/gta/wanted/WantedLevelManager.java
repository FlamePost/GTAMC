package me.darthteddy1.gta.wanted;

import me.darthteddy1.gta.mobs.MobManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class WantedLevelManager implements Listener {

    private static HashMap<Player, Integer> wantedlevels = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            if (!wantedlevels.containsKey(e.getEntity().getKiller())) {
                wantedlevels.put(e.getEntity().getKiller(), 1);
            } else {
                wantedlevels.replace(e.getEntity().getKiller(), wantedlevels.get(e.getEntity().getPlayer()), wantedlevels.get(e.getEntity().getPlayer()) + 1);
            }
            if(wantedlevels.containsKey(e.getEntity())) {
                wantedlevels.remove(e.getEntity());
            }
            e.getEntity().getKiller().sendMessage(MessageUtils.PREFIX_BAD + "Your wanted level has increased to §b" + wantedlevels.get(e.getEntity().getPlayer()));
            MobManager.spawnCop(e.getEntity().getKiller());
            MobManager.spawnCop(e.getEntity().getKiller());
            MobManager.spawnCop(e.getEntity().getKiller());
        }
    }

    public static Integer getWantedLevel(Player p) {
        if(wantedlevels.containsKey(p)) {
            return wantedlevels.get(p);
        }
            return 0;
    }

}
