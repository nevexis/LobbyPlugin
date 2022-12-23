package dev.nevah5.mc.lobbyplugin.tools;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;

public class PlayerTool {
    org.bukkit.entity.Player player;
    LobbyConfig lobbyConfig = new LobbyConfig();

    public PlayerTool(org.bukkit.entity.Player player){
        this.player = player;
    }

    public String getPlayerPrefix(){
        if(player.hasPermission("group.owner")) {
            return lobbyConfig.OWNER_PREFIX;
        } else if(player.hasPermission("group.admin")) {
            return lobbyConfig.ADMIN_PREFIX;
        } else if (player.hasPermission("group.mod")) {
            return lobbyConfig.MOD_PREFIX;
        } else if (player.hasPermission("group.friend")) {
            return lobbyConfig.FRIEND_PREFIX;
        }
        return lobbyConfig.DEFAULT_PREFIX;
    }
}
