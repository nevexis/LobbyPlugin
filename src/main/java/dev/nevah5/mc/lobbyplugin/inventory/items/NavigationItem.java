package dev.nevah5.mc.lobbyplugin.inventory.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NavigationItem {
    public ItemStack getSpawnItem(){
        ItemStack item = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format(
                "%s▶ %s%sSpawn %s%s◀",
                ChatColor.DARK_GRAY, ChatColor.YELLOW, ChatColor.BOLD, ChatColor.RESET,
                ChatColor.DARK_GRAY)
        );
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "≫ Hint: /spawn also works :)");
        lore.add("");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getVTMCItem(){
        ItemStack item = new ItemStack(Material.BLACK_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format(
                "%s▶ %s%sVTMC %s%s(1.19.3) %s◀",
                ChatColor.DARK_GRAY, ChatColor.GOLD, ChatColor.BOLD, ChatColor.RESET,
                ChatColor.GRAY, ChatColor.DARK_GRAY)
        );
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "≫ This Server is private for VT.");
        lore.add("");
        lore.add(String.format("%s▶ Server Owners ◀", ChatColor.GRAY));
        lore.add(ChatColor.GRAY+"⬦ JustSpruce");
        lore.add(ChatColor.GRAY+"⬦ Nevah5");
        lore.add("");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getOwnServerItem(){
        ItemStack item = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format(
                "%s▶ %s%sCreate your own Server %s%s(Latest Version) %s◀",
                ChatColor.DARK_GRAY, ChatColor.GREEN, ChatColor.BOLD, ChatColor.RESET,
                ChatColor.GRAY, ChatColor.DARK_GRAY)
        );
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "≫ This feature is still in development.");
        lore.add(ChatColor.GRAY + "≫ Once it is finished, friends of Nevah5 will be able.");
        lore.add(ChatColor.GRAY + "≫ to create their own servers to play on.");
        lore.add("");
        lore.add(String.format("%s▶ contact@nevah5.dev ◀", ChatColor.GRAY));
        lore.add("");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }
}
