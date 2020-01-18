package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.command.CommandExecutor;

public class Nick implements Listener, CommandExecutor {

    private Main plugin;

    public Nick(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("nick")) {
            if (!p.hasPermission("essentials.nick.self")) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                return true;
            }
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Nick.incorrect_usage")));
                return true;
            }
            if (args[0].equalsIgnoreCase("off")) {
                plugin.getConfig().set(p.getName(), null);
                plugin.saveConfig();
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Nick.nick_reset")));
                return true;
            }
            for (String key : plugin.getConfig().getKeys(false)) {
                if (plugin.getConfig().getString(key).equals(args[0])) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Nick.nick_taken")));
                    return true;
                }
            }
            plugin.getConfig().set(p.getName(), args[0]);
            plugin.saveConfig();
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Nick.nick_changed")));
        }
        if (cmd.getName().equalsIgnoreCase("realname")) {
            if (!p.hasPermission("essentials.realname")) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                return true;
            }
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Realname.incorrect_usage")));
                return true;
            }
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (plugin.getConfig().contains(target.getName())) {
                    if (plugin.getConfig().getString(target.getName()).equals(args[0])) {
                        p.sendMessage(Utils.chat("&8[&eEssentials&8] " + Utils.chat("&b") + args[0] + Utils.chat("'s&6 real name is") + Utils.chat("&1 ") + target.getName()));
                        return true;
                    } else {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
