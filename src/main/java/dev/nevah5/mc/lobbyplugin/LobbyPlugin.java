package dev.nevah5.mc.lobbyplugin;

import dev.nevah5.mc.lobbyplugin.commands.BuildCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyPlugin extends JavaPlugin implements Listener {
    private LobbyConfig lobbyConfig;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        lobbyConfig = new LobbyConfig();
        this.getCommand("build").setExecutor(new BuildCommand(lobbyConfig));
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
}
