package dev.nevah5.mc.lobbyplugin.inventory;

import dev.nevah5.mc.lobbyplugin.inventory.items.NavigationItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LobbyInventory {
    public void updatePlayer(Player player){
        ItemStack compass = new ItemStack(Material.COMPASS);
        player.getInventory().setItem(0, compass);
    }

    public void openNavigation(Player player){
        NavigationItem items = new NavigationItem();
        Inventory inventory = Bukkit.createInventory(null, 9, "Navigation");
        inventory.setItem(4, items.getVTMCItem());
        player.openInventory(inventory);
    }
}
