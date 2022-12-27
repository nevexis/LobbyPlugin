package dev.nevah5.mc.lobbyplugin;

import dev.nevah5.mc.lobbyplugin.commands.BuildCommand;
import dev.nevah5.mc.lobbyplugin.commands.ChatClearCommand;
import dev.nevah5.mc.lobbyplugin.commands.SpawnCommand;
import dev.nevah5.mc.lobbyplugin.inventory.LobbyInventory;
import dev.nevah5.mc.lobbyplugin.inventory.items.NavigationItem;
import dev.nevah5.mc.lobbyplugin.tools.PlayerTool;
import dev.nevah5.mc.lobbyplugin.tools.TeamTool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LobbyPlugin extends JavaPlugin implements Listener {
    private TeamTool teamTool;
    private LobbyConfig lobbyConfig;
    private final LobbyInventory lobbyInventory = new LobbyInventory();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        lobbyConfig = new LobbyConfig(this);
        teamTool = new TeamTool(lobbyConfig);
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new BuildCommand(lobbyConfig));
        Objects.requireNonNull(this.getCommand("cc")).setExecutor(new ChatClearCommand(lobbyConfig));
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new SpawnCommand());
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
        Player player = playerJoinEvent.getPlayer();
        Location spawnLoc = new Location(player.getWorld(), 0.5, 100, 0.5, 180f, 0);
        playerJoinEvent.setJoinMessage(null);
        player.setSaturation(999999f);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.setInvulnerable(true);
        // Inventory
        player.getInventory().clear();
        lobbyInventory.updatePlayer(lobbyConfig, player);
        player.teleport(spawnLoc);

        // welcome message ᐅᐊ
        for (int i = 0; i < 300; i++){
            player.sendMessage("");
        }
        player.sendMessage(String.format("         %sᐅ %s%smc.nevah5.dev %sᐊ", ChatColor.GRAY, ChatColor.BLUE,
                ChatColor.BOLD, ChatColor.GRAY));
        player.sendMessage(String.format("         %sWelcome, %s!", ChatColor.LIGHT_PURPLE,
                playerJoinEvent.getPlayer().getName()));
        player.sendMessage("");

        // tab list
        player.setPlayerListHeader(String.format(
                "         %sᐅ %s%smc.nevah5.dev %sᐊ         \n\n\n",
                ChatColor.GRAY, ChatColor.BLUE,
                ChatColor.BOLD, ChatColor.GRAY));
        player.setPlayerListFooter(String.format("\n          %s%sServer:%s  %s          \n\n", ChatColor.YELLOW,
                ChatColor.BOLD, ChatColor.GRAY, lobbyConfig.getConfigurationString("LobbyName")));
        player.setPlayerListName(new PlayerTool(player).getPlayerPrefix(lobbyConfig)+ChatColor.RESET+ChatColor.GRAY+player.getName());

        // team
        PlayerTool playerTool = new PlayerTool(player);
        playerTool.setPlayerTeam(teamTool);
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
        if(inventoryClickEvent.getCurrentItem().equals(items.getVTMC())) {
            lobbyInventory.updatePlayer(lobbyConfig, player);
            player.closeInventory();
            player.teleport(new Location(player.getWorld(), 0.5, 98, -37.5, 180, 0));
            return;
        } else if (inventoryClickEvent.getCurrentItem().equals(items.getOwnServer())) {
            inventoryClickEvent.setCancelled(true);
            return;
        } else if (inventoryClickEvent.getCurrentItem().equals(items.getSpawn())) {
            Location spawnLoc = new Location(player.getWorld(), 0.5, 100, 0.5, 180f, 0);
            player.teleport(spawnLoc);
            return;
        }

        if(!lobbyConfig.playersEnableBuilding.contains(player)) {
            inventoryClickEvent.setCancelled(true);
            inventoryClickEvent.setCurrentItem(null);
            lobbyInventory.updatePlayer(lobbyConfig, player);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent inventoryDragEvent){
        Player player = (Player) inventoryDragEvent.getWhoClicked();
        if(!lobbyConfig.playersEnableBuilding.contains(player)) {
            inventoryDragEvent.setCancelled(true);
            lobbyInventory.updatePlayer(lobbyConfig, player);
        }
    }

    @EventHandler
    public void clickItem(PlayerInteractEvent playerInteractEvent){
        Player player = playerInteractEvent.getPlayer();
        if(playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR &&
        playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            lobbyInventory.openNavigation(player);
        } else if (player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR){
            player.sendMessage(lobbyConfig.getConfigurationString("ServerPrefix")+"This feature is coming soon.");
        }
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent asyncPlayerChatEvent){
        Player player = asyncPlayerChatEvent.getPlayer();
        String prefix = new PlayerTool(player).getPlayerPrefix(lobbyConfig);

        // Set message Format
        asyncPlayerChatEvent.setFormat(prefix + ChatColor.RESET + ChatColor.GRAY +
                "%s "+ChatColor.DARK_GRAY+">>"+ChatColor.RESET+" %s");

        // Replace message color codes
        if(player.hasPermission("lobby.chat.colors")){
            asyncPlayerChatEvent.setMessage(ChatColor.translateAlternateColorCodes('&',
                    asyncPlayerChatEvent.getMessage()));
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent playerCommandPreprocessEvent){
        Player player = playerCommandPreprocessEvent.getPlayer();
        List<String> disallowedCommands = new ArrayList<>();
        disallowedCommands.add("bukkit:?");
        disallowedCommands.add("bukkit:about");
        disallowedCommands.add("bukkit:help");
        disallowedCommands.add("bukkit:pl");
        disallowedCommands.add("bukkit:plugins");
        disallowedCommands.add("bukkit:reload");
        disallowedCommands.add("bukkit:rl");
        disallowedCommands.add("bukkit:timings");
        disallowedCommands.add("bukkit:ver");
        disallowedCommands.add("bukkit:version");
        disallowedCommands.add("?");
        disallowedCommands.add("about");
        disallowedCommands.add("help");
        disallowedCommands.add("pl");
        disallowedCommands.add("plugins");
        disallowedCommands.add("reload");
        disallowedCommands.add("rl");
        disallowedCommands.add("timings");
        disallowedCommands.add("ver");
        disallowedCommands.add("version");
        disallowedCommands.add("bungee");
        disallowedCommands.add("icanhasbukkit");
        disallowedCommands.add("list");
        disallowedCommands.add("lp");
        disallowedCommands.add("luckperms");
        disallowedCommands.add("perm");
        disallowedCommands.add("permission");
        disallowedCommands.add("permissions");
        disallowedCommands.add("perms");
        disallowedCommands.add("luckperms:lp");
        disallowedCommands.add("luckperms:luckperms");
        disallowedCommands.add("luckperms:perm");
        disallowedCommands.add("luckperms:permission");
        disallowedCommands.add("luckperms:permissions");
        disallowedCommands.add("luckperms:perms");
        if(!player.isOp()){
            disallowedCommands.forEach(command -> {
                if(playerCommandPreprocessEvent.getMessage().toLowerCase().equalsIgnoreCase("/"+command)) {
                    player.sendMessage(lobbyConfig.getConfigurationString("ServerPrefix")+"You are not allowed to" +
                            " do that!");
                    playerCommandPreprocessEvent.setCancelled(true);
                }
            });
        }

    }
}
