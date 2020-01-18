package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Heal implements CommandExecutor {

    private Main plugin;

    public Heal(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("heal").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.heal")) {
            if (args.length == 0) {
                p.setHealth(20);
                p.setFoodLevel(20);
                for (PotionEffect effect : p.getActivePotionEffects())
                    p.removePotionEffect(effect.getType());
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_message")));
            } else {
                if (!p.hasPermission("essentials.heal.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        target.setHealth(20);
                        target.setFoodLevel(20);
                        for (PotionEffect effect : p.getActivePotionEffects())
                            target.removePotionEffect(effect.getType());
                        target.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_message")));
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_others_message")) + " " + target.getDisplayName() + Utils.chat("&6!"));
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
