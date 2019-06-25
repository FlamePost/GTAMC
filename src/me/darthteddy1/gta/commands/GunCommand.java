package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class GunCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.OWNER) {
                openGunGUI(p);
            }
        }
        return true;
    }

    private void openGunGUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "§c§lGuns");
        inv.addItem(ItemUtils.createItemStack("§eBlow Gun §l» 1 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7A hand-carved blow gun"), Material.SUGAR_CANE));
        inv.addItem(ItemUtils.createItemStack("§eTaser §l» 2 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7A military grade taser"), Material.FEATHER));
        inv.addItem(ItemUtils.createItemStack("§eFlamethrower §l» 200 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7A pyromaniac's favorite weapon"), Material.FLINT_AND_STEEL));
        inv.addItem(ItemUtils.createItemStack("§5Enderstaff §l» 10 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7A staff forged from the skin", "§7of the enderdragon"), Material.CARROT_STICK));
        inv.addItem(ItemUtils.createItemStack("§eJetpack §l» 50 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7Fly through the skies", "§7of the enderdragon"), Material.DOUBLE_PLANT));
        inv.addItem(ItemUtils.createItemStack("§eMinigun §l» 500 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7So.. much.. firepower..."), Material.LEASH));
        inv.addItem(ItemUtils.createItemStack("§eBazooka §l» 1 «", Arrays.asList("§eTier: §c§lCHEATCODE", " ", "§7Explosions? Explosions."), Material.IRON_BARDING));

        inv.setItem(10,ItemUtils.createItemStack("§eTier1 Assault Rifle §l» 20 «", Arrays.asList(" ", "§7Tier: §e1", "§7Type: §eAssault Rifle", "§7Ammo Type: §cAssault Rifle §a(Iron Ingot)", " ", "§7A makeshift assault rifle"), Material.WOOD_PICKAXE));
        inv.setItem(19,ItemUtils.createItemStack("§6Tier2 Assault Rifle §l» 25 «", Arrays.asList(" ", "§7Tier: §62", "§7Type: §6Assault Rifle", "§7Ammo Type: §cAssault Rifle §a(Iron Ingot)", " ", "§7An assault rifle from the streets"), Material.STONE_PICKAXE));
        inv.setItem(28,ItemUtils.createItemStack("§aTier3 Assault Rifle §l» 25 «", Arrays.asList(" ", "§7Tier: §a3", "§7Type: §aAssault Rifle", "§7Ammo Type: §cAssault Rifle §a(Iron Ingot)", " ", "§7A police issued assault rifle"), Material.GOLD_PICKAXE));
        inv.setItem(37,ItemUtils.createItemStack("§dTier4 Assault Rifle §l» 30 «", Arrays.asList(" ", "§7Tier: §d4", "§7Type: §dAssault Rifle", "§7Ammo Type: §cAssault Rifle §a(Iron Ingot)", " ", "§7An assault rifle from the Cartel"), Material.IRON_PICKAXE));
        inv.setItem(46,ItemUtils.createItemStack("§bTier5 Assault Rifle §l» 35 «", Arrays.asList(" ", "§7Tier: §b5", "§7Type: §bAssault Rifle", "§7Ammo Type: §cAssault Rifle §a(Iron Ingot)", " ", "§7The best assault rifle out there"), Material.DIAMOND_PICKAXE));
        inv.setItem(9,ItemUtils.createItemStack("§eTier1 Shotgun §l» 4 «", Arrays.asList(" ", "§7Tier: §e1", "§7Type: §eShotgun", "§7Ammo Type: §cShotgun §a(Gold Ingot)", " ", "§7Standard-grade shotgun"), Material.WOOD_SPADE));
        inv.setItem(18,ItemUtils.createItemStack("§6Tier2 Shotgun §l» 2 «", Arrays.asList(" ", "§7Tier: §62", "§7Type: §6Shotgun", "§7Ammo Type: §cShotgun §a(Gold Ingot)", " ", "§7The classic double-barrel shotgun"), Material.STONE_SPADE));
        inv.setItem(27,ItemUtils.createItemStack("§aTier3 Shotgun §l» 5 «", Arrays.asList(" ", "§7Tier: §a3", "§7Type: §aShotgun", "§7Ammo Type: §cShotgun §a(Gold Ingot)", " ", "§7Not your average street shotgun"), Material.GOLD_SPADE));
        inv.setItem(36,ItemUtils.createItemStack("§dTier4 Shotgun §l» 5 «", Arrays.asList(" ", "§7Tier: §d4", "§7Type: §dShotgun", "§7Ammo Type: §cShotgun §a(Gold Ingot)", " ", "§7Military grade shotgun"), Material.IRON_SPADE));
        inv.setItem(45,ItemUtils.createItemStack("§bTier5 Shotgun §l» 6 «", Arrays.asList(" ", "§7Tier: §b5", "§7Type: §bShotgun", "§7Ammo Type: §cShotgun §a(Gold Ingot)", " ", "§7The best of the best"), Material.DIAMOND_SPADE));


        p.openInventory(inv);
    }

}
