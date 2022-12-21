package dev.nevah5.mc.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LobbyConfig {
    public final String SERVER_PREFIX = String.format("%s%sServer %s >> %s",
            ChatColor.AQUA, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public final String OWNER_PREFIX = String.format("%s%s| %s%sOwner %s >> %s", ChatColor.DARK_GRAY, ChatColor.BOLD,
            ChatColor.RED, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public final String ADMIN_PREFIX = String.format("%s%s| %s%sAdmin %s >> %s", ChatColor.DARK_GRAY, ChatColor.BOLD,
            ChatColor.YELLOW, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public final String MOD_PREFIX = String.format("%s%s| %s%sMod %s >> %s", ChatColor.DARK_GRAY, ChatColor.BOLD,
            ChatColor.BLUE, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public final String FRIEND_PREFIX = String.format("%s%s| %s%sFriend %s >> %s", ChatColor.DARK_GRAY, ChatColor.BOLD,
            ChatColor.GREEN, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public final String DEFAULT_PREFIX = String.format("%s%s| %s%sPlayer %s >> %s", ChatColor.DARK_GRAY, ChatColor.BOLD,
            ChatColor.GRAY, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public List<Player> playersEnableBuilding = new ArrayList<>();
}
