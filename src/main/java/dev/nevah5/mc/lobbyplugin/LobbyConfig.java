package dev.nevah5.mc.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LobbyConfig {
    private File configFile;
    private LobbyPlugin plugin;
    public LobbyConfig(LobbyPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        this.plugin = plugin;
        // if file doesn't exist
        if(!configFile.exists()){
            List<String> configContent = new ArrayList<>();
            configContent.add("# LobbyConfig Version "+plugin.getDescription().getVersion());
            configContent.add("# -- Prefixes --");
            configContent.add("ServerPrefix: &b&lServer &8>> &d");
            configContent.add("OwnerPrefix: &b&lServer &8>> &d");
            configContent.add("AdminPrefix: &b&lServer &8>> &d");
            configContent.add("ModPrefix: &b&lServer &8>> &d");
            configContent.add("FriendPrefix: &b&lServer &8>> &d");
            configContent.add("PlayerPrefix: &b&lServer &8>> &d");
            try {
                this.writeConfigFile(configContent);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void writeConfigFile(List<String> lines) throws IOException {
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            configFile.createNewFile();
        }

        try{
            FileWriter fileWriter = new FileWriter(configFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
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
