package github.com.thecaptaincook.magicSoup.listeners;

import github.com.thecaptaincook.magicSoup.MagicSoup;
import github.com.thecaptaincook.magicSoup.utils.BossBarManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoupConsumptionListener implements Listener {
    private final MagicSoup plugin;

    public SoupConsumptionListener(MagicSoup plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSoupConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (item.getType() == Material.BEETROOT_SOUP && isMagicSoup(item)) {
            if (!player.hasPermission(plugin.getConfig().getString("permissions.use"))) {
                event.setCancelled(true);
                player.sendMessage(plugin.getConfig().getString("messages.no-permission")
                        .replace("%prefix%", plugin.getConfig().getString("messages.prefix"))
                        .replace("&", "§"));
                return;
            }

            // Apply effects
            applyEffects(player);
            
            // Start boss bar
            plugin.getBossBarManager().startBossBar(player);
        }
    }

    private boolean isMagicSoup(ItemStack item) {
        if (!item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        return meta.hasDisplayName() && meta.getDisplayName().contains("Magic Soup");
    }

    private void applyEffects(Player player) {
        int duration = plugin.getConfig().getInt("settings.effect-duration") * 20; // Convert to ticks
        
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, duration, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, duration, 0));
    }
}