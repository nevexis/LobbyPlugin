package dev.nevah5.mc.lobbyplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyInventory {
    public void updatePlayer(Player player){
        ItemStack compass = new ItemStack(Material.COMPASS);
        player.getInventory().setItem(0, compass);
    }
}
