package me.darthteddy1.gta.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {


    public static ItemStack createItemStack(String s, List<String> lore, Material m) {
        ItemStack i = new ItemStack(m);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(s);
        im.setLore(lore);
        i.setItemMeta(im);

        return i;
    }

}
