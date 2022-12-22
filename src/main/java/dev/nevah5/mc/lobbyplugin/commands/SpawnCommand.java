package dev.nevah5.mc.lobbyplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            assert player != null;
            player.teleport(new Location(player.getWorld(), 0.5, 100, 0.5, 180f, 0));
        }
        return true;
    }
}
