package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Suicide implements CommandExecutor {

    private Main plugin;

    public Suicide(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("suicide").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.suicide")) {
            p.setHealth(0);
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Suicide.suicide")));
            Bukkit.broadcastMessage(Utils.chat("&b") + p.getDisplayName() + Utils.chat(" &cHas killed themselves!"));
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
