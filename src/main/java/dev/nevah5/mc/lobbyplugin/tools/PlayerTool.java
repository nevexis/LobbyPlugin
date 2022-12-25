package dev.nevah5.mc.lobbyplugin.tools;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import dev.nevah5.mc.lobbyplugin.LobbyPlugin;
import org.bukkit.entity.Player;

public class PlayerTool {
    org.bukkit.entity.Player player;
    LobbyConfig lobbyConfig;

    public PlayerTool(LobbyConfig config, Player player){
        lobbyConfig = config;
        this.player = player;
    }

    public String getPlayerPrefix(){
        if(player.hasPermission("group.owner")) {
            return lobbyConfig.getConfigurationString("OwnerPrefix");
        } else if(player.hasPermission("group.admin")) {
            return lobbyConfig.getConfigurationString("AdminPrefix");
        } else if (player.hasPermission("group.mod")) {
            return lobbyConfig.getConfigurationString("ModPrefix");
        } else if (player.hasPermission("group.friend")) {
            return lobbyConfig.getConfigurationString("FriendPrefix");
        }
        return lobbyConfig.getConfigurationString("DefaultPrefix");
    }
}
