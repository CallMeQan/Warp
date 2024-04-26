package com.github.callmeqan.warp;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WarpItem {
    public static ItemStack warp;

    public static void init(){
        createWarp();
    }

    private static void createWarp(){
        ItemStack item = new ItemStack(Material.NETHERITE_HOE, 1);

        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Warp").color(TextColor.color(0, 255, 255)));
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        item.setItemMeta(meta);

        warp = item;
    }
}
