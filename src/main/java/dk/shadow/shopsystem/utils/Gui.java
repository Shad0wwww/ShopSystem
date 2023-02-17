package dk.shadow.shopsystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Gui {
    private Inventory inv = null;



    public void ExampleGui() {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 9, "Example");

        // Put the items into the inventory
        initializeItems();
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "Example Sword", "§aFirst line of the lore", "§bSecond line of the lore"));
        inv.addItem(createGuiItem(Material.IRON_HELMET, "§bExample Helmet", "§aFirst line of the lore", "§bSecond line of the lore"));
    }

    // Nice little method to create a gui item with a custom name, and description
    public static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Chat.colored(name));

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    // Nice little method to create a gui item with a custom name, and description
    public static ItemStack createItemStack(final ItemStack item, final String name, List<String> lore) {
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Chat.colored(name));

        // Set the lore of the item
        meta.setLore(Chat.getColored(lore));

        item.setItemMeta(meta);

        return item;
    }
    public static ItemStack createItemStackWithNoYml(final ItemStack item, final String name, final String... lore) {
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Chat.colored(name));

        // Set the lore of the item
        meta.setLore(Chat.getColored(Arrays.asList(lore)));

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack addItemsToplayer(Material material,Integer i) {
        ItemStack item = new ItemStack(material, i);

        // Set the name of the item
        ItemMeta meta = item.getItemMeta();

        // Set the lore of the item

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createItemGlass(Material material, int GlassColor, String displayName, String... loreString) {
        List<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(material, 1, (short) GlassColor);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Chat.colored(displayName));

        for (String s : loreString)
            lore.add(Chat.colored(s));

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }




    // You can open the inventory with this
    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }







}
