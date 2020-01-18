package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportAll implements CommandExecutor {

    private Main plugin;

    public TeleportAll(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("tpall").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.teleport.all")) {
            if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
                p.sendMessage(Utils.chat("&8[&eEssentials&8]&6 No other players are on right now!"));
            } else if (Bukkit.getServer().getOnlinePlayers().size() > 1 ) {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.teleport(p.getLocation());
                }
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleport_all_successful")));
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }
        return false;
    }
}
