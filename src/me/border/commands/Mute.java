package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class Mute implements CommandExecutor, Listener {

    public ArrayList<Player> list_of_muted_people = new ArrayList<>();

    private Main plugin;

    public Mute(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("mute").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.mute")) {
            if (args.length <= 1) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("notEnoughArguments")));
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    return true;
                } else {
                    if (plugin.getConfig().getConfigurationSection("muted_players") == null) {
                        plugin.getConfig().set("muted_players." + plugin.getName() + ".test", p.getServer());
                    }
                    for (String section : plugin.getConfig().getConfigurationSection("muted_players").getKeys(false)) {
                        if (section.equals(target.getUniqueId().toString())) {
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("Mute.player_already_muted")));
                            return true;
                        } else if (!section.equals(target.getUniqueId().toString())) {
                            StringBuilder x = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                x.append(args[i] + " ");
                            }
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("Mute.modNotification") + "&b " + target.getName()));
                            target.sendMessage(Utils.chat(plugin.getConfig().getString("Mute.userNotification") + "&b " + p.getName()));
                            plugin.getConfig().set("muted_players." + target.getUniqueId() + ".muter", p.getName());
                            plugin.getConfig().set("muted_players." + target.getUniqueId() + ".reason", x.toString().trim());
                            plugin.getConfig().set("muted_players." + target.getUniqueId() + ".name", target.getName());
                            plugin.saveConfig();
                        }
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
