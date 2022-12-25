package dev.nevah5.mc.lobbyplugin.tools;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import org.bukkit.entity.Player;

public class PlayerTool {
    org.bukkit.entity.Player player;

    public PlayerTool(Player player){
        this.player = player;
    }

    public String getPlayerPrefix(LobbyConfig lobbyConfig){
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

    public void setPlayerTeam(TeamTool teamTool){
        if(player.hasPermission("group.owner")) {
            teamTool.getGroup_owner().addEntry(player.getName());
        } else if(player.hasPermission("group.admin")) {
            teamTool.getGroup_admin().addEntry(player.getName());
        } else if (player.hasPermission("group.mod")) {
            teamTool.getGroup_mod().addEntry(player.getName());
        } else if (player.hasPermission("group.friend")) {
            teamTool.getGroup_friend().addEntry(player.getName());
        } else {
            teamTool.getGroup_default().addEntry(player.getName());
        }
    }
}
