package github.com.thecaptaincook.magicSoup.recipes;

import github.com.thecaptaincook.magicSoup.MagicSoup;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class SoupRecipe {
    private final MagicSoup plugin;

    public SoupRecipe(MagicSoup plugin) {
        this.plugin = plugin;
    }

    public void register() {
        NamespacedKey key = new NamespacedKey(plugin, "magic_soup");
        ShapedRecipe recipe = new ShapedRecipe(key, createMagicSoup());

        recipe.shape("RGL", "WBW", "   ");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('G', Material.GLOWSTONE_DUST);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('W', Material.POTION);
        recipe.setIngredient('B', Material.BOWL);

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
        
        soup.setItemMeta(meta);
        return soup;
    }
}