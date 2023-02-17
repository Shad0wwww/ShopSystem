package dk.shadow.shopsystem.commands;

import dk.shadow.shopsystem.ShopSystem;
import dk.shadow.shopsystem.shops.Main;
import dk.shadow.shopsystem.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("events.shop")) {
            sender.sendMessage(Chat.colored(ShopSystem.configYML.getString("PermissionDeniedMessage")));
            return true;
        }
        if (args.length == 0) {
            Main.mainShopMenu(p);
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (!p.hasPermission("shopsystem.reload")) {
                sender.sendMessage(Chat.colored(ShopSystem.configYML.getString("PermissionDeniedMessage")));
                return true;
            }

            boolean success;
            try {
                ShopSystem.maingui.reloadConfig();
                ShopSystem.mainguiYML = ShopSystem.maingui.getConfig();

                ShopSystem.blockmenu.reloadConfig();
                ShopSystem.blockmenuYML = ShopSystem.blockmenu.getConfig();

                ShopSystem.menu2.reloadConfig();
                ShopSystem.menu2YML = ShopSystem.menu2.getConfig();

                ShopSystem.menu3.reloadConfig();
                ShopSystem.menu3YML = ShopSystem.menu3.getConfig();

                ShopSystem.config.reloadConfig();
                ShopSystem.configYML = ShopSystem.config.getConfig();

                success = true;

            } catch(Exception e){
                e.printStackTrace();
                success = false;
            }
            if(success) sender.sendMessage(Chat.colored("&aReload successfully completed"));
            if(!success) sender.sendMessage(Chat.colored("&cAn error occurred. Please check the console."));
        }

        return false;
    }
}
