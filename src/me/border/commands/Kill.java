package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {

    private Main plugin;

    public Kill(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("kill").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.kill")) {
            if (args.length == 0) {
                p.performCommand("suicide");
            } else {
                if (!p.hasPermission("essentials.kill.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        target.setHealth(0);
                        target.sendMessage(Utils.chat(plugin.getConfig().getString("Kill.killed")));
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Kill.killed_others")) + Utils.chat(target.getDisplayName()) + Utils.chat("! &cYou monster!"));
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
