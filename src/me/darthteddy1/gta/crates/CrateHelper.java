package me.darthteddy1.gta.crates;

import me.darthteddy1.gta.commands.SpawnCommand;
import me.darthteddy1.gta.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CrateHelper {

    static HashMap<Location, String> crates = new HashMap<>();

    public static void prepCrates() {
        crates.put(SpawnCommand.l, "ammo");
    }

    public static ItemStack getAmmoWin() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        if(randomNum >= 1 && randomNum <= 10) {
            return ItemUtils.createItemStack("§c§lSniper Ammo", Arrays.asList("§7Basic Sniper Ammo","§eSpecial Effects: §cNone"), Material.NETHER_BRICK_ITEM, 10);
        } else if (randomNum > 10 && randomNum <= 20) {
            return ItemUtils.createItemStack("§6§lShotgun Ammo", Arrays.asList("§7Basic Shotgun Ammo","§eSpecial Effects: §cNone"), Material.GOLD_INGOT, 10);
        } else if (randomNum > 20 && randomNum <= 30) {
            return ItemUtils.createItemStack("§r§lAssault Rifle Ammo", Arrays.asList("§7Basic Assault Rifle Ammo","§eSpecial Effects: §cNone"), Material.IRON_INGOT, 10);
        } else if (randomNum > 30 && randomNum <= 40) {
            return ItemUtils.createItemStack("§5§lPistol Ammo", Arrays.asList("§7Basic Pistol Ammo","§eSpecial Effects: §cNone"), Material.DIAMOND, 10);
        } else if (randomNum > 40 && randomNum <= 45) {
            return ItemUtils.createItemStack("§6§lShotgun Ammo", Arrays.asList("§7Basic Shotgun Ammo","§eSpecial Effects: §cNone"), Material.GOLD_INGOT, 25);
        } else if (randomNum > 45 && randomNum <= 50) {
            return ItemUtils.createItemStack("§c§lSniper Ammo", Arrays.asList("§7Basic Sniper Ammo","§eSpecial Effects: §cNone"), Material.NETHER_BRICK_ITEM, 25);
        } else if (randomNum > 50 && randomNum <= 55) {
            return ItemUtils.createItemStack("§r§lAssault Rifle Ammo", Arrays.asList("§7Basic Assault Rifle Ammo","§eSpecial Effects: §cNone"), Material.IRON_INGOT, 25);
        } else if (randomNum > 55 && randomNum <= 60) {
            return ItemUtils.createItemStack("§5§lPistol Ammo", Arrays.asList("§7Basic Pistol Ammo","§eSpecial Effects: §cNone"), Material.DIAMOND, 25);
        } else if (randomNum > 60 && randomNum <= 65) {
            return ItemUtils.createItemStack("§c§lSniper Ammo", Arrays.asList("§7Basic Sniper Ammo","§eSpecial Effects: §cNone"), Material.NETHER_BRICK_ITEM, 32);
        } else if (randomNum > 65 && randomNum <= 70) {
            return ItemUtils.createItemStack("§6§lShotgun Ammo", Arrays.asList("§7Basic Shotgun Ammo","§eSpecial Effects: §cNone"), Material.GOLD_INGOT, 32);
        } else if (randomNum > 70 && randomNum <= 75) {
            return ItemUtils.createItemStack("§r§lAssault Rifle Ammo", Arrays.asList("§7Basic Assault Rifle Ammo","§eSpecial Effects: §cNone"), Material.IRON_INGOT, 32);
        } else if (randomNum > 75 && randomNum <= 80) {
            return ItemUtils.createItemStack("§5§lPistol Ammo", Arrays.asList("§7Basic Pistol Ammo","§eSpecial Effects: §cNone"), Material.DIAMOND, 32);
        } else if (randomNum > 80 && randomNum <= 85) {
            return ItemUtils.createItemStack("§c§lSniper Ammo", Arrays.asList("§7Basic Sniper Ammo","§eSpecial Effects: §cNone"), Material.NETHER_BRICK_ITEM, 40);
        } else if (randomNum > 85 && randomNum <= 90) {
            return ItemUtils.createItemStack("§6§lShotgun Ammo", Arrays.asList("§7Basic Shotgun Ammo","§eSpecial Effects: §cNone"), Material.GOLD_INGOT, 40);
        } else if (randomNum > 90 && randomNum <= 95) {
            return ItemUtils.createItemStack("§r§lAssault Rifle Ammo", Arrays.asList("§7Basic Assault Rifle Ammo","§eSpecial Effects: §cNone"), Material.IRON_INGOT, 40);
        } else if (randomNum > 95 && randomNum <= 100) {
            return ItemUtils.createItemStack("§5§lPistol Ammo", Arrays.asList("§7Basic Pistol Ammo","§eSpecial Effects: §cNone"), Material.DIAMOND, 40);
        }
        return null;
    }

    public static ItemStack getAmmoKey() {
        return ItemUtils.createItemStack("§c§lAmmo Crate Key", Arrays.asList("§eA key that can be used", "§eto open an Ammo Crate at", "§espawn."), Material.TRIPWIRE_HOOK);
    }

}
