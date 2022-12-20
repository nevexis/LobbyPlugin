package dev.nevah5.mc.lobbyplugin;

import dev.nevah5.mc.lobbyplugin.commands.BuildCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent){
        Player player = (Player) inventoryClickEvent.getWhoClicked();
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
}
