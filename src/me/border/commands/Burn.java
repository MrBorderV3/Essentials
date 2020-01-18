package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Burn implements CommandExecutor {

    private Main plugin;

    public Burn(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("burn").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!(sender instanceof Player)){
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.burn")){
            if (args.length == 0){
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Burn.burn")));
                p.setFireTicks(20);
            } else {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("notEnoughArguments")));
            }
            if (args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                } else {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Burn.burn_others") + target.getName() + "&c alight!"));
                    target.setFireTicks(20);
                    target.sendMessage(Utils.chat(plugin.getConfig().getString("Burn.burn")));
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }

}
