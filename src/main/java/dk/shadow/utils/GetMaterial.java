package dk.shadow.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GetMaterial {

    public static ItemStack item(String s) {
        Material m =  Material.valueOf(s.toUpperCase());
        ItemStack stack = new ItemStack(m, 1);
        return stack;
    }
}
