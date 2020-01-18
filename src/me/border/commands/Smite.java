package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;

public class Smite implements CommandExecutor {

    private Main plugin;

    public Smite(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("smite").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.smite")) {
            if (args.length == 0) {
                p.getWorld().strikeLightning(p.getTargetBlock(null, 600).getLocation());
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Smite.smite_successful")));
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                } else {
                    target.getWorld().strikeLightning(target.getLocation());
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Smite.smite_player_successful")) + " " + target.getDisplayName() + Utils.chat("&6!"));
                    target.sendMessage(Utils.chat(plugin.getConfig().getString("Smite.smitten")));
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}