package dev.nevah5.mc.lobbyplugin.inventory;

import dev.nevah5.mc.lobbyplugin.inventory.items.NavigationItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyInventory {
    public void updatePlayer(Player player){
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(String.format("%sᐅ %s%sNavigator %sᐊ", ChatColor.DARK_GRAY, ChatColor.BLUE,
                ChatColor.BOLD, ChatColor.DARK_GRAY));
        compass.setItemMeta(meta);
        player.getInventory().setItem(0, compass);
    }

    public void openNavigation(Player player){
        NavigationItem items = new NavigationItem();
        Inventory inventory = Bukkit.createInventory(null, 9, "Navigation");
        inventory.setItem(0, items.getSpawnItem());
        inventory.setItem(4, items.getVTMCItem());
        inventory.setItem(8, items.getOwnServerItem());
        player.openInventory(inventory);
    }
}
