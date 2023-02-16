package dk.shadow;

import dk.shadow.commands.ShopCommand;
import dk.shadow.events.MainInventoryListener;
import dk.shadow.utils.Config;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ShopSystem extends JavaPlugin {

    public static ShopSystem instance;
    public static Config config, maingui, blockmenu, menu2;
    public static FileConfiguration configYML, mainguiYML, blockmenuYML, menu2YML;
    public static Economy econ = null;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        //Config.yml
        if (!(new File(getDataFolder(), "config.yml")).exists())saveResource("config.yml", false);
        if (!(new File(getDataFolder(), "maingui.yml")).exists())saveResource("maingui.yml", false);
        if (!(new File(getDataFolder(), "menu1.yml")).exists())saveResource("menu1.yml", false);
        if (!(new File(getDataFolder(), "menu2.yml")).exists())saveResource("menu2.yml", false);
        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

        maingui = new Config(this, null, "maingui.yml");
        mainguiYML = maingui.getConfig();

        blockmenu = new Config(this, null, "menu1.yml");
        blockmenuYML = blockmenu.getConfig();

        menu2 = new Config(this, null, "menu2.yml");
        menu2YML = menu2.getConfig();

        //SetUpEcon
        setupEconomyPlugin();
        //RegisterCommands
        registerCommands();
        //Register events
        registerEvents();
    }
    public void registerCommands() {
        getCommand("shopsystem").setExecutor( new ShopCommand());
    }
    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new MainInventoryListener(), this);
    }

    public void setupEconomyPlugin() {
        if (!setupEconomy() ) {
            Bukkit.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            Bukkit.getLogger().severe(String.format(String.valueOf(getServer().getPluginManager().getPlugin("Vault"))));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupEconomy();
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
