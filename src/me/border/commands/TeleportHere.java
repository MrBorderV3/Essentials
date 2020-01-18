package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHere implements CommandExecutor {

    private Main plugin;

    public TeleportHere(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("tphere").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.teleport.others")) {
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleport_error")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else if (args.length == 1) {
                        target.teleport(p.getLocation());
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleport_here_successful")) + " " + target.getDisplayName() + Utils.chat(" &6to you!"));
                        target.sendMessage(Utils.chat(plugin.getConfig().getString("Teleport.teleported_here")));
                    }
                }
            } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }
        return false;
    }
}
