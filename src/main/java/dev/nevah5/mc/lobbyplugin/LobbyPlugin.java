package dev.nevah5.mc.lobbyplugin;

import dev.nevah5.mc.lobbyplugin.commands.BuildCommand;
import dev.nevah5.mc.lobbyplugin.inventory.LobbyInventory;
import dev.nevah5.mc.lobbyplugin.inventory.items.NavigationItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.regex.Pattern;

public class LobbyPlugin extends JavaPlugin implements Listener {
    private LobbyConfig lobbyConfig;
    private final LobbyInventory lobbyInventory = new LobbyInventory();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        lobbyConfig = new LobbyConfig();
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new BuildCommand(lobbyConfig));
    }

    @EventHandler
    public void onBuild(BlockBreakEvent blockBreakEvent){
        if(!lobbyConfig.playersEnableBuilding.contains(blockBreakEvent.getPlayer()))
            blockBreakEvent.setCancelled(true);
    }

    @EventHandler
    public void onBlockInteract(BlockPlaceEvent blockPlaceEvent){
        if(!lobbyConfig.playersEnableBuilding.contains(blockPlaceEvent.getPlayer()))
            blockPlaceEvent.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent playerDropItemEvent){
        if(!lobbyConfig.playersEnableBuilding.contains(playerDropItemEvent.getPlayer()))
            playerDropItemEvent.setCancelled(true);
    }

    @EventHandler
    public void onHandChangeEvent(PlayerSwapHandItemsEvent playerSwapHandItemsEvent){
        if(!lobbyConfig.playersEnableBuilding.contains(playerSwapHandItemsEvent.getPlayer()))
            playerSwapHandItemsEvent.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent entityDamageEvent){
        entityDamageEvent.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent weatherChangeEvent){
        weatherChangeEvent.setCancelled(true);
        weatherChangeEvent.getWorld().setClearWeatherDuration(9999);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent){
        Location spawnLoc = new Location(playerJoinEvent.getPlayer().getWorld(), 0.5, 100, 0.5, 180f, 0);
        playerJoinEvent.setJoinMessage(null);
        playerJoinEvent.getPlayer().setSaturation(999999f);
        playerJoinEvent.getPlayer().setFoodLevel(20);
        playerJoinEvent.getPlayer().setHealth(20);
        playerJoinEvent.getPlayer().setInvulnerable(true);
        // Inventory
        playerJoinEvent.getPlayer().getInventory().clear();
        lobbyInventory.updatePlayer(playerJoinEvent.getPlayer());
        playerJoinEvent.getPlayer().teleport(spawnLoc);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent playerQuitEvent){
        playerQuitEvent.setQuitMessage(null);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent){
        Player player = (Player) inventoryClickEvent.getWhoClicked();

        NavigationItem items = new NavigationItem();
        // check if navigation
        if(inventoryClickEvent.getCurrentItem().equals(items.getVTMCItem())) {
            lobbyInventory.updatePlayer(player);
            player.closeInventory();
            player.teleport(new Location(player.getWorld(), 0.5, 98, -37.5, 180, 0));
            return;
        };

        if(!lobbyConfig.playersEnableBuilding.contains(player)) {
            inventoryClickEvent.setCancelled(true);
            inventoryClickEvent.setCurrentItem(null);
            lobbyInventory.updatePlayer(player);
        }
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent inventoryDragEvent){
        Player player = (Player) inventoryDragEvent.getWhoClicked();
        if(!lobbyConfig.playersEnableBuilding.contains(player)) {
            inventoryDragEvent.setCancelled(true);
            lobbyInventory.updatePlayer(player);
        }
    }

    @EventHandler
    public void clickItem(PlayerInteractEvent playerInteractEvent){
        Player player = playerInteractEvent.getPlayer();
        if(playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR &&
        playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            lobbyInventory.openNavigation(player);
        }
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent asyncPlayerChatEvent){
        Player player = asyncPlayerChatEvent.getPlayer();
        String prefix = lobbyConfig.DEFAULT_PREFIX;

        // Set Prefix of player
        if(player.hasPermission("group.owner")) {
            prefix = lobbyConfig.OWNER_PREFIX;
        } else if(player.hasPermission("group.admin")) {
            prefix = lobbyConfig.ADMIN_PREFIX;
        } else if (player.hasPermission("group.mod")) {
            prefix = lobbyConfig.MOD_PREFIX;
        } else if (player.hasPermission("group.friend")) {
            prefix = lobbyConfig.FRIEND_PREFIX;
        }

        // Set message Format
        asyncPlayerChatEvent.setFormat(prefix + ChatColor.RESET + ChatColor.GRAY +
                "%s "+ChatColor.DARK_GRAY+">>"+ChatColor.RESET+" %s");

        // Replace message color codes
        if(player.hasPermission("lobby.chat.colors")){
            asyncPlayerChatEvent.setMessage(ChatColor.translateAlternateColorCodes('&',
                    asyncPlayerChatEvent.getMessage()));
        } else {
            asyncPlayerChatEvent.setMessage(ChatColor.translateAlternateColorCodes('&',
                    asyncPlayerChatEvent.getMessage()));
        }
    }
}
