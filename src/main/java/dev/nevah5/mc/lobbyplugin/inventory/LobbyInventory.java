package dev.nevah5.mc.lobbyplugin.inventory;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import dev.nevah5.mc.lobbyplugin.inventory.items.HotbarItem;
import dev.nevah5.mc.lobbyplugin.inventory.items.NavigationItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LobbyInventory {
    public void updatePlayer(LobbyConfig lobbyConfig, Player player){
        HotbarItem items = new HotbarItem();
        player.getInventory().setItem(0, items.getNavigator());
        player.getInventory().setItem(7, items.getPlayerSettings(lobbyConfig, player));
        player.getInventory().setItem(8, items.getLobbySelector());
    }

    public void openNavigation(Player player){
        NavigationItem items = new NavigationItem();
        Inventory inventory = Bukkit.createInventory(null, 9, "Navigation");
        inventory.setItem(0, items.getSpawn());
        inventory.setItem(4, items.getVTMC());
        inventory.setItem(8, items.getOwnServer());
        player.openInventory(inventory);
    }

    public void openLobbySelector(LobbyConfig config, Player player){
        String connected = config.getConfigurationString("LobbyName");

        NavigationItem items = new NavigationItem();
        Inventory inventory = Bukkit.createInventory(null, 9, "Lobby Selector");
        inventory.setItem(0, items.getLobby("Lobby1", "Lobby1".equals(connected)));
        inventory.setItem(1, items.getLobby("Lobby2", "Lobby2".equals(connected)));
        player.openInventory(inventory);
    }
}
