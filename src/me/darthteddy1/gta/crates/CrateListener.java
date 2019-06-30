package me.darthteddy1.gta.crates;

import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CrateListener implements Listener {

    @EventHandler
    public void onInt(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(e.getClickedBlock().getType() == Material.CHEST && CrateHelper.crates.get(e.getClickedBlock().getLocation()) != null) {
           if(CrateHelper.crates.get(e.getClickedBlock().getLocation()).equalsIgnoreCase("ammo")) {
               e.setCancelled(true);
               if (p.getItemInHand() != null && p.getItemInHand().isSimilar(CrateHelper.getAmmoKey())) {
                   if (p.getItemInHand().getAmount() > 1) {
                       p.sendMessage(MessageUtils.PREFIX_BAD + "You cannot open crates with multiple keys in hand.");

                   } else {
                       p.setItemInHand(CrateHelper.getAmmoWin());
                       p.updateInventory();
                       p.sendMessage(MessageUtils.PREFIX_GOOD + "You have opened an ammo crate!");
                   }
               } else {
                   p.sendMessage(MessageUtils.PREFIX_BAD + "You must be holding a key to open an Ammo crate.");
               }
           }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.CHEST && CrateHelper.crates.get(e.getBlock().getLocation()) != null) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MessageUtils.PREFIX_BAD + "You cannot destroy crates");
        }
        }

}
