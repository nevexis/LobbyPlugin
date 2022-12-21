package dev.nevah5.mc.lobbyplugin.commands;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {
    LobbyConfig lobbyConfig;

    public BuildCommand(LobbyConfig lobbyConfig){
        this.lobbyConfig = lobbyConfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player) {
            if(lobbyConfig.playersEnableBuilding.contains(((Player) sender).getPlayer())) {
                lobbyConfig.playersEnableBuilding.remove(((Player) sender).getPlayer());
                sender.sendMessage(lobbyConfig.SERVER_PREFIX +"Building has been disabled.");
                return true;
            }
            lobbyConfig.playersEnableBuilding.add(((Player) sender).getPlayer());
            sender.sendMessage(lobbyConfig.SERVER_PREFIX +"Building has been enabled.");
        }
        return true;
    }
}
