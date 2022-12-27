package dev.nevah5.mc.lobbyplugin.inventory.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HotbarItem {
    public ItemStack getNavigator(){
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(String.format("%sᐅ %s%sNavigator %sᐊ", ChatColor.DARK_GRAY, ChatColor.BLUE,
                ChatColor.BOLD, ChatColor.DARK_GRAY));
        compass.setItemMeta(meta);
        return compass;
    }

    public ItemStack getLobbySelector(){
        ItemStack compass = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(String.format("%sᐅ %s%sLobby Selector %sᐊ", ChatColor.DARK_GRAY, ChatColor.YELLOW,
                ChatColor.BOLD, ChatColor.DARK_GRAY));
        compass.setItemMeta(meta);
        return compass;
    }
}
