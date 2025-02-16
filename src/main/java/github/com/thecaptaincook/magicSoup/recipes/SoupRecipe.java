package github.com.thecaptaincook.magicSoup.recipes;

import github.com.thecaptaincook.magicSoup.MagicSoup;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.inventory.ItemFlag.HIDE_ADDITIONAL_TOOLTIP;
import static org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS;

public class SoupRecipe {
    private final MagicSoup plugin;

    public SoupRecipe(MagicSoup plugin) {
        this.plugin = plugin;
    }

    public void register() {
        NamespacedKey key = new NamespacedKey(plugin, "magic_soup");
        ShapedRecipe recipe = new ShapedRecipe(key, createMagicSoup());

        recipe.shape("ABA", "RSG", "ALA");
        recipe.setIngredient('B', Material.BUCKET);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('S', Material.BEETROOT_SOUP);
        recipe.setIngredient('G', Material.GLOWSTONE);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);

        plugin.getServer().addRecipe(recipe);
    }

    public static ItemStack createMagicSoup() {
        ItemStack soup = new ItemStack(Material.MUSHROOM_STEW);
        ItemMeta meta = soup.getItemMeta();

        assert meta != null;
        meta.setDisplayName("§5Magic Soup");
        meta.setLore(Arrays.asList(
            "§7A mystical soup that grants",
            "§7special powers to those who",
            "§7consume it."
        ));

        meta.addEnchant(Enchantment.EFFICIENCY, 1, true);
        meta.addItemFlags(HIDE_ENCHANTS);
        meta.addItemFlags(HIDE_ADDITIONAL_TOOLTIP);

        soup.setItemMeta(meta);
        return soup;
    }
}