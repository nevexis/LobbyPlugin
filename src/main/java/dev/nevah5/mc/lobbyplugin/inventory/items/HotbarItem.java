package dev.nevah5.mc.lobbyplugin.inventory.items;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import dev.nevah5.mc.lobbyplugin.tools.PlayerTool;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

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

    public ItemStack getPlayerSettings(LobbyConfig lobbyConfig, Player player){
        PlayerTool playerTool = new PlayerTool(player);
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwnerProfile(player.getPlayerProfile());
        meta.setDisplayName(playerTool.getPlayerPrefix(lobbyConfig) + ChatColor.GRAY + player.getName());
        item.setItemMeta(meta);
        return item;
    }
}
