package me.darthteddy1.gta.mobs;

import net.minecraft.server.v1_8_R3.EntityPigZombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPigZombie;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;

public class MobManager {

    public static void spawnCop(Player pl) {
        Location l = pl.getLocation();
        PigZombie p = (PigZombie) l.getWorld().spawnEntity(l, EntityType.PIG_ZOMBIE);
        p.setCustomName("");
        p.setCustomNameVisible(true);
        p.setAngry(true);
        p.setBaby(false);
        p.setMaxHealth(25);
        p.setHealth(25);
        EntityPigZombie nmsCop = ((CraftPigZombie) p).getHandle();
        nmsCop.setEquipment(0, CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FEATHER)));
        nmsCop.setEquipment(1, CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.DIAMOND_HELMET)));
        nmsCop.setEquipment(2, CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.IRON_CHESTPLATE)));
        nmsCop.setEquipment(3, CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.IRON_LEGGINGS)));
        nmsCop.setEquipment(4, CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.DIAMOND_BOOTS)));
        nmsCop.setSprinting(true);

        p.setTarget(pl);
    }

}
