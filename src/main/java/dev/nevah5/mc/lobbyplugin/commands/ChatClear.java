package dev.nevah5.mc.lobbyplugin.commands;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClear implements CommandExecutor {
    LobbyConfig lobbyConfig;
    public ChatClear(LobbyConfig lobbyConfig){ this.lobbyConfig = lobbyConfig; }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        for (int i = 0; i < 300; i++){
            Bukkit.broadcastMessage("");
        }
        Bukkit.broadcastMessage(String.format("%sThe chat has been cleared by %s.", lobbyConfig.SERVER_PREFIX,
                sender.getName()));
        Bukkit.broadcastMessage("");
        return true;
    }
}
