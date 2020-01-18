package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Alert implements CommandExecutor {

    private Main plugin;

    public Alert(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("alert").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!(sender instanceof Player)){
            String message = "";
            for (int i = 0; i < args.length; i++ ) {
                message += " " + args[i];
            }

            Bukkit.broadcastMessage(Utils.chat("&c" + message));
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.alert")){
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("notEnoughArguments")));
                return true;
            }
            String message = "";
            for (int i = 0; i < args.length; i++ ) {
                message += " " + args[i];
            }

            Bukkit.broadcastMessage(Utils.chat("&c" + message));
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }

}

