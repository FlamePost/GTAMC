package me.darthteddy1.gta.drugs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DrugBackpack {

    public static ItemStack getDrugBackpack(Drug d, int ii) {
        ItemStack i = new ItemStack(Material.LEATHER);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("§a§lDrug Bag §7§l(" + d.getName() + "§7§l)");
        im.setLore(Arrays.asList("§7A backpack full of " + d.getName(), "§7It contains §a" + ii + "x §7of it", " ", "§cRight-Click to unpack it"));
        i.setItemMeta(im);

        return i;
    }

}
