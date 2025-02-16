package github.com.thecaptaincook.magicSoup.commands;

import github.com.thecaptaincook.magicSoup.MagicSoup;
import github.com.thecaptaincook.magicSoup.recipes.SoupRecipe;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SoupCommand implements CommandExecutor {
    private final MagicSoup plugin;

    public SoupCommand(MagicSoup plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give":
                handleGiveCommand(sender, args);
                break;
            case "reload":
                handleReloadCommand(sender);
                break;
            case "recipe":
                handleRecipeCommand(sender);
                break;
            default:
                sendHelp(sender);
        }

        return true;
    }

    private void handleGiveCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission(plugin.getConfig().getString("permissions.admin"))) {
            sender.sendMessage(plugin.getConfig().getString("messages.no-permission")
                    .replace("%prefix%", plugin.getConfig().getString("messages.prefix"))
                    .replace("&", "§"));
            return;
        }

        Player target;
        if (args.length > 1) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found!");
                return;
            }
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            sender.sendMessage("§cPlease specify a player!");
            return;
        }

        ItemStack magicSoup = SoupRecipe.createMagicSoup();
        target.getInventory().addItem(magicSoup);
        target.sendMessage(plugin.getConfig().getString("messages.given-soup")
                .replace("%prefix%", plugin.getConfig().getString("messages.prefix"))
                .replace("&", "§"));
    }

    private void handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission(plugin.getConfig().getString("permissions.admin"))) {
            sender.sendMessage(plugin.getConfig().getString("messages.no-permission")
                    .replace("%prefix%", plugin.getConfig().getString("messages.prefix"))
                    .replace("&", "§"));
            return;
        }

        plugin.reloadConfig();
        sender.sendMessage(plugin.getConfig().getString("messages.config-reloaded")
                .replace("%prefix%", plugin.getConfig().getString("messages.prefix"))
                .replace("&", "§"));
    }

    private void handleRecipeCommand(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players!");
            return;
        }

        sender.sendMessage("§5=== Magic Soup Recipe ===");
        sender.sendMessage("§7- 1 Bowl");
        sender.sendMessage("§7- 1 Redstone Dust");
        sender.sendMessage("§7- 1 Glowstone Dust");
        sender.sendMessage("§7- 1 Lapis Lazuli");
        sender.sendMessage("§7- 1 Water Bottle");
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("§5=== MagicSoup Commands ===");
        sender.sendMessage("§7/magicsoup give [player] §f- Give magic soup");
        sender.sendMessage("§7/magicsoup reload §f- Reload configuration");
        sender.sendMessage("§7/magicsoup recipe §f- Show recipe");
    }
}