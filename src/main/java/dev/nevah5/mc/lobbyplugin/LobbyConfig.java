package dev.nevah5.mc.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LobbyConfig {
    public final String SERVER_PREFIX = String.format("%s%sServer %s>> %s",
            ChatColor.AQUA, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE);
    public final String OWNER_PREFIX = String.format("%s%sOwner %s%s| ",
            ChatColor.RED, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.BOLD);
    public final String ADMIN_PREFIX = String.format("%s%sAdmin %s%s| ",
            ChatColor.YELLOW, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.BOLD);
    public final String MOD_PREFIX = String.format("%s%sMod %s%s| ",
            ChatColor.BLUE, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.BOLD);
    public final String FRIEND_PREFIX = String.format("%s%sFriend %s%s| ",
            ChatColor.GREEN, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.BOLD);
    public final String DEFAULT_PREFIX = String.format("%s%sPlayer %s%s| ",
            ChatColor.GRAY, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.BOLD);
    public List<Player> playersEnableBuilding = new ArrayList<>();
}
