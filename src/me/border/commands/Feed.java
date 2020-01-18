package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {

    private Main plugin;

    public Feed(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("feed").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.feed")) {
            if (args.length == 0) {
                p.setFoodLevel(20);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_message")));
            } else {
                if (!p.hasPermission("essentials.feed.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        target.setFoodLevel(20);
                        target.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_message")));
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_others_message")) + " " + target.getDisplayName() + Utils.chat("&6!"));
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}