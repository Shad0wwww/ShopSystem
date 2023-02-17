package dk.shadow.shopsystem.shops;

import dk.shadow.shopsystem.ShopSystem;
import dk.shadow.shopsystem.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FarvetBlocks {

    String name = Chat.colored(ShopSystem.menu2YML.getString("GuiMenu.GuiName"));

    public void farvetShopMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9 * ShopSystem.menu2YML.getInt("GuiMenu.Rows"), name);
        for (String slot : ShopSystem.menu2YML.getConfigurationSection("GuiMenu.Slot").getKeys(false)) {

            String itemString = ShopSystem.menu2YML.getString("GuiMenu.Slot."+slot+".item");

            String itemName = Chat.colored(ShopSystem.menu2YML.getString("GuiMenu.Slot."+slot+".name"));
            List<String> lores = ShopSystem.menu2YML.getStringList("GuiMenu.Slot."+slot+".lore");


            UniversalMaterial universalMaterial = UniversalMaterial.valueOf(itemString);
            ItemStack itemStack = universalMaterial.getStack();







            inv.setItem(Integer.parseInt(slot), Gui.createItemStack(itemStack, itemName, lores));

        }

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, Gui.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor("&a"), "&f"));
        }
        for (int i = 36; i < 44; i++) {
            inv.setItem(i, Gui.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor("&f"), "&f"));
        }
        inv.setItem(44, Gui.createItemStackWithNoYml(SkullCreator.itemFromUrl("https://textures.minecraft.net/texture/f84f597131bbe25dc058af888cb29831f79599bc67c95c802925ce4afba332fc"), "&c&LTILBAGE", "&f", "&fKlik her for at komme tilbage", "&f"));

        player.openInventory(inv);
    }

}
