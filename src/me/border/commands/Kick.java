package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

    private Main plugin;

    public Kick(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("kick").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)){
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null){
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                return true;
            }
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("Kick.kick_successful") + target.getName() + "&6!"));
            String message = "";
            for (int i = 1; i < args.length; i++ ) {
                message += "" + args[i];
            }
            target.kickPlayer(ChatColor.AQUA + "Kicked by: " + ChatColor.RED + "CONSOLE" + ChatColor.AQUA + " For: " + ChatColor.RED + message);
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.kick")){
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null){
                p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                return true;
            }
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Kick.kick_successful")));
            String message = "";
            for (int i = 1; i < args.length; i++ ) {
                message += "" + args[i];
            }
            target.kickPlayer( ChatColor.AQUA + "Kicked by: " + ChatColor.RED + p.getName() + ChatColor.AQUA + " For: " + ChatColor.RED + message);
            System.out.println("User " + target.getName() + " has been kicked for " +  message + " by " + p.getName());
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
