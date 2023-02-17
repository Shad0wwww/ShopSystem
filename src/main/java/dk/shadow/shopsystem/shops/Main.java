package dk.shadow.shopsystem.shops;

import dk.shadow.shopsystem.ShopSystem;
import dk.shadow.shopsystem.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class Main {
    static String name = Chat.colored(ShopSystem.maingui.getConfig().getString("GuiMenu.GuiName"));
    public static void mainShopMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * ShopSystem.maingui.getConfig().getInt("GuiMenu.Rows"), name);
        for (String slot : ShopSystem.mainguiYML.getConfigurationSection("GuiMenu.Slot").getKeys(false)) {

            String jsonString = ShopSystem.mainguiYML.getString("GuiMenu.Slot."+slot+".item");
            String itemName = Chat.colored(ShopSystem.mainguiYML.getString("GuiMenu.Slot."+slot+".name"));
            List<String> lores = ShopSystem.mainguiYML.getStringList("GuiMenu.Slot."+slot+".lore");

            if (jsonString.equalsIgnoreCase("HEAD")) {
                inv.setItem(Integer.parseInt(slot), Gui.createItemStack(SkullCreator.itemFromUrl(ShopSystem.mainguiYML.getString("GuiMenu.Slot."+slot+".url")), itemName, lores));
            } else {
                inv.setItem(Integer.parseInt(slot), Gui.createItemStack(GetMaterial.item(jsonString), itemName, lores));
            }
        }

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, Gui.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor("&a"), "&f"));
        }
        for (int i = 36; i < 45; i++) {
            inv.setItem(i, Gui.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor("&f"), "&f"));
        }

        player.openInventory(inv);
    }
}
