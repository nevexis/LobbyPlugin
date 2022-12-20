package dev.nevah5.mc.lobbyplugin.inventory.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NavigationItem {
    public ItemStack getVTMCItem(){
        ItemStack item = new ItemStack(Material.BLACK_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(String.format(
                "%s▶ %s%sVTMC %s%s(1.19.3) %s◀",
                ChatColor.DARK_GRAY, ChatColor.GOLD, ChatColor.BOLD, ChatColor.RESET,
                ChatColor.GRAY, ChatColor.DARK_GRAY)
        );
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "≫ This Server is private for VT.");
        lore.add("");
        lore.add(String.format("%s▶ Server Owners ◀", ChatColor.GRAY));
        lore.add(ChatColor.GRAY+"⬦ JustSpruce");
        lore.add(ChatColor.GRAY+"⬦ Nevah5");
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }
}
