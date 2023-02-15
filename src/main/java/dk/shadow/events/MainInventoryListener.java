package dk.shadow.events;

import dk.shadow.ShopSystem;
import dk.shadow.shops.BlockMenu;
import dk.shadow.shops.Main;
import dk.shadow.utils.Chat;
import dk.shadow.utils.Gui;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainInventoryListener implements Listener {
    String MainMenuname = Chat.colored(ShopSystem.maingui.getConfig().getString("GuiMenu.GuiName"));
    String contains = Chat.colored(ShopSystem.config.getConfig().getString("containsINv"));
    String blockname = Chat.colored(ShopSystem.blockmenu.getConfig().getString("GuiMenu.GuiName"));

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();


        if(e.getClickedInventory().getName().equals(Chat.colored(MainMenuname))) {
            e.setCancelled(true);
            int i = 0;
            for (String slot : ShopSystem.mainguiYML.getConfigurationSection("GuiMenu.Slot").getKeys(false)) {

                String itemName = Chat.colored(ShopSystem.mainguiYML.getString("GuiMenu.Slot."+slot+".name"));

                if(itemName.toLowerCase().contains("blocks")) {
                    i = Integer.parseInt(slot);
                }
            }
            if(e.getSlot() == i) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 5);
                BlockMenu.blockShopMenu(player);
            }

        }


        if(e.getClickedInventory().getName().equals(Chat.colored(blockname))) {
            e.setCancelled(true);
            if (e.getSlot() == 44) {
                Main.mainShopMenu(player);
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 5);
            }
        }


        if(e.getClickedInventory().getName().contains(contains)) {
            e.setCancelled(true);
            List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45);
            if(!(list.contains(e.getSlot()))) {

                if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasLore()) {

                    List<String> lore = e.getInventory().getItem(e.getRawSlot()).getItemMeta().getLore();
                    if (lore != null) {
                        List<String> uncoloredLore = new ArrayList<>();
                        for (String line : lore) {
                            uncoloredLore.add(ChatColor.stripColor(line));
                        }
                        // do something with the uncolored lore
                        String split = Chat.colored(uncoloredLore.get(1));
                        String[] lol = split.split(" ");


                        String prisString = lol[1].replaceAll("[$.]", "");


                        int pris = Integer.parseInt(prisString);

                        Material itemType = e.getCurrentItem().getType();

                        if (e.getClick().isLeftClick()) {
                            buyFromShop(player, String.valueOf(itemType), pris, 1);

                        }
                        if (e.isRightClick()) {
                            buyFromShop(player, String.valueOf(itemType), pris, 16);
                        }

                    }


                    // Do something with the pris and itemType variables
                }
            }
        }

    }

    private void buyFromShop(Player player, String s, Integer i, Integer a) {
        int price = i*a;
        if (player.getInventory().firstEmpty() != -1) {
            if(ShopSystem.econ.getBalance(player) >= price) {
                if (s.contains("generator")) {
                    player.getInventory().addItem(Gui.createItemStackWithNoYml(new ItemStack(Material.STAINED_CLAY), "&7&lCLAY &f&lGENERATOR", "&f"));
                    ShopSystem.econ.bankWithdraw(player.getName(), price);

                } else {
                    player.getInventory().addItem(Gui.addItemsToplayer(Material.valueOf(s), a));
                    ShopSystem.econ.bankWithdraw(player.getName(), price);

                }
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 5);
                player.sendMessage(Chat.colored(ShopSystem.configYML.getString("DukoebteMessage").replace("%amount%", String.valueOf(a)).replace("%item%", s)));
            } else  {
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 5);
                player.sendMessage(Chat.colored(ShopSystem.configYML.getString("DuHarikkeNokPenge").replace("%mangler%", String.valueOf(Math.round(price - ShopSystem.econ.getBalance(player))))));
            }
        } else {
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 5);
            player.sendMessage(Chat.colored(ShopSystem.configYML.getString("DuHarikkeNokPlads").replace("%mangler%", String.valueOf(Math.round(price - ShopSystem.econ.getBalance(player))))));
        }

    }

}
