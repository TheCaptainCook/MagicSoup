package github.com.thecaptaincook.magicSoup.utils;

import github.com.thecaptaincook.magicSoup.MagicSoup;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BossBarManager {
    private final MagicSoup plugin;
    private final Map<UUID, BossBar> bossBars;
    private final Map<UUID, Integer> tasks;

    public BossBarManager(MagicSoup plugin) {
        this.plugin = plugin;
        this.bossBars = new HashMap<>();
        this.tasks = new HashMap<>();
    }

    public void startBossBar(Player player) {
        cleanup(player);

        String title = plugin.getConfig().getString("settings.boss-bar.title", "Magic Soup Effect")
                .replace("&", "§");
        BarColor color = BarColor.valueOf(plugin.getConfig().getString("settings.boss-bar.color", "PURPLE"));
        BarStyle style = BarStyle.valueOf(plugin.getConfig().getString("settings.boss-bar.style", "SOLID"));
        
        BossBar bossBar = Bukkit.createBossBar(title, color, style);
        bossBar.addPlayer(player);
        bossBar.setProgress(1.0);
        
        bossBars.put(player.getUniqueId(), bossBar);
        
        int duration = plugin.getConfig().getInt("settings.effect-duration", 300);
        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            private int timeLeft = duration;
            
            @Override
            public void run() {
                if (timeLeft <= 0) {
                    cleanup(player);
                    return;
                }
                
                double progress = (double) timeLeft / duration;
                bossBar.setProgress(progress);
                timeLeft--;
            }
        }, 0L, 20L);
        
        tasks.put(player.getUniqueId(), taskId);
    }

    public void cleanup(Player player) {
        UUID playerId = player.getUniqueId();
        
        if (tasks.containsKey(playerId)) {
            Bukkit.getScheduler().cancelTask(tasks.get(playerId));
            tasks.remove(playerId);
        }
        
        if (bossBars.containsKey(playerId)) {
            BossBar bossBar = bossBars.get(playerId);
            bossBar.removePlayer(player);
            bossBars.remove(playerId);
        }
    }

    public void cleanup() {
        for (Integer taskId : tasks.values()) {
            Bukkit.getScheduler().cancelTask(taskId);
        }
        
        for (BossBar bossBar : bossBars.values()) {
            bossBar.removeAll();
        }
        
        tasks.clear();
        bossBars.clear();
    }
}