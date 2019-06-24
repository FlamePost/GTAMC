package me.darthteddy1.gta.world;

import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if(e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(RankManager.getRank(e.getPlayer()) != Rank.OWNER && RankManager.getRank(e.getPlayer()) != Rank.ADMIN) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "You may not break blocks");
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(RankManager.getRank(e.getPlayer()) != Rank.OWNER && RankManager.getRank(e.getPlayer()) != Rank.ADMIN) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "You may not place blocks");
        }
    }

    @EventHandler
    public void onChestOpen(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.CHEST) {
                //TODO ranomize chests
            }
        }
    }

}
