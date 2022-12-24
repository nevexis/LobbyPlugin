package dev.nevah5.mc.lobbyplugin.inventory;

import dev.nevah5.mc.lobbyplugin.inventory.items.HotbarItem;
import dev.nevah5.mc.lobbyplugin.inventory.items.NavigationItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LobbyInventory {
    public void updatePlayer(Player player){
        HotbarItem items = new HotbarItem();
        player.getInventory().setItem(0, items.getNavigator());
    }

    public void openNavigation(Player player){
        NavigationItem items = new NavigationItem();
        Inventory inventory = Bukkit.createInventory(null, 9, "Navigation");
        inventory.setItem(0, items.getSpawn());
        inventory.setItem(4, items.getVTMC());
        inventory.setItem(8, items.getOwnServer());
        player.openInventory(inventory);
    }
}
