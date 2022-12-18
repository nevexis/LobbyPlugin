package dev.nevah5.mc.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LobbyConfig {
    public final String PREFIX = ChatColor.DARK_GRAY + "| " + ChatColor.AQUA + "Server " + ChatColor.DARK_GRAY +
        ">> "+ChatColor.LIGHT_PURPLE;
    public List<Player> playersEnableBuilding = new ArrayList<>();
}
