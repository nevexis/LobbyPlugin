package dev.nevah5.mc.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.regex.qual.Regex;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class LobbyConfig {
    private Map<String, String> config;
    public List<Player> playersEnableBuilding = new ArrayList<>();
    private File configFile;
    private LobbyPlugin plugin;

    public LobbyConfig(LobbyPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        this.plugin = plugin;
        // if file doesn't exist
        if(!configFile.exists()){
            try {
                this.writeConfigFile(this.getDefaultConfiguration());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        // read config
        try {
            this.readConfigFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> getConfigurationKeys(){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("ServerPrefix");
        keys.add("OwnerPrefix");
        keys.add("AdminPrefix");
        keys.add("ModPrefix");
        keys.add("FriendPrefix");
        keys.add("DefaultPrefix");
        return keys;
    }

    public List<String> getDefaultConfiguration(){
        List<String> configContent = new ArrayList<>();
        configContent.add("# LobbyConfig Version "+plugin.getDescription().getVersion());
        configContent.add("# Author: Nevah5");
        configContent.add("# -- Prefixes --");
        configContent.add("ServerPrefix: \"&b&lServer &8>> &d\"");
        configContent.add("OwnerPrefix: \"&c&lOwner &8&l| &r\"");
        configContent.add("AdminPrefix: \"&e&lAdmin &8&l| &r\"");
        configContent.add("ModPrefix: \"&9&lMod &8&l| &r\"");
        configContent.add("FriendPrefix: \"&a&lFriend &8&l| &r\"");
        configContent.add("DefaultPrefix: \"&7&lPlayer &8&l| &r\"");
        return configContent;
    }

    private void readConfigFile() throws IOException {
        if(!configFile.exists()) this.writeConfigFile(this.getDefaultConfiguration());

        FileReader fileReader = new FileReader(configFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = bufferedReader.lines().toList();
        bufferedReader.close();
        fileReader.close();

        this.convertLinesToConfigMap(lines);
    }

    private void convertLinesToConfigMap(List<String> lines){
        this.config = new HashMap<>();
        for (String key : getConfigurationKeys()){
            config.put(key, ChatColor.translateAlternateColorCodes(
                        '&',
                    lines.stream().filter((line) -> line.startsWith(key + ": "))
                            .map((line) -> line.replaceFirst(Pattern.quote(key+": "), ""))
                            .findFirst().get()
                            .replaceAll("\"", "")
                    )
            );
        }
    }

    private void writeConfigFile(List<String> lines) throws IOException {
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

    public String getConfigurationString(String key) {
        return config.get(key);
    }

    public boolean getConfigurationBoolean(String key){
        String value = config.get(key);
        return value.equals("true");
    }
}
