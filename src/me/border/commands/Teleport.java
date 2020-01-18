package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    private Main plugin;

    public Teleport(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("tp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.tp")) {
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleport_error")));
            } else {
                if (!p.hasPermission("essentials.teleport.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else if (args.length == 1) {
                        p.teleport(target.getLocation());
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleport_successful")) + target.getDisplayName() + Utils.chat("&6!"));
                    } else if (args.length == 2) {
                        Player playerToSend = Bukkit.getPlayerExact(args[0]);
                        Player target1 = Bukkit.getPlayerExact(args[1]);
                        playerToSend.teleport(target1.getLocation());
                        playerToSend.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleported_here")) + target1.getDisplayName() + Utils.chat("&6!"));

                    }

                }

            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }
        return false;
    }
}
