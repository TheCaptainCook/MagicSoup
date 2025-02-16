package github.com.thecaptaincook.magicSoup;

import github.com.thecaptaincook.magicSoup.commands.SoupCommand;
import github.com.thecaptaincook.magicSoup.listeners.SoupConsumptionListener;
import github.com.thecaptaincook.magicSoup.utils.BossBarManager;
import github.com.thecaptaincook.magicSoup.recipes.SoupRecipe;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicSoup extends JavaPlugin {
    private static MagicSoup instance;
    private BossBarManager bossBarManager;

    @Override
    public void onEnable() {
        instance = this;
        
        // Save default config
        saveDefaultConfig();
        
        // Initialize managers
        this.bossBarManager = new BossBarManager(this);
        
        // Register recipe
        new SoupRecipe(this).register();
        
        // Register events
        Bukkit.getPluginManager().registerEvents(new SoupConsumptionListener(this), this);
        
        // Register commands
        getCommand("magicsoup").setExecutor(new SoupCommand(this));
        
        getLogger().info("MagicSoup has been enabled!");
    }

    @Override
    public void onDisable() {
        // Clean up boss bars
        if (bossBarManager != null) {
            bossBarManager.cleanup();
        }
        
        getLogger().info("MagicSoup has been disabled!");
    }

    public static MagicSoup getInstance() {
        return instance;
    }

    public BossBarManager getBossBarManager() {
        return bossBarManager;
    }
}