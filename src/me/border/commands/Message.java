package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import me.border.commands.messageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {

    private Main plugin;

    public Message(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("message").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player messager = (Player) sender;

        if (messager.hasPermission("essentials.message")) {
            if (args.length < 1) {
                messager.sendMessage(Utils.chat(plugin.getConfig().getString( "notEnoughArguments")));
            } else {
                Player receiver = Bukkit.getPlayerExact(args[0]);
                if (receiver == null) {
                    messager.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                } else {
                    plugin.mM.setReplyTarget(messager, receiver);
                    args[0] = "";
                    String message = "";
                    for (int i = 0; i < args.length; i++){
                        message += "" + args[i];
                    }
                    messager.sendMessage(Utils.chat("&6[&cMe&6 ->&b ") + Utils.chat(receiver.getName()) + Utils.chat("&6]&f ") + Utils.chat(message));
                    receiver.sendMessage(Utils.chat("&6[&b") + Utils.chat(messager.getName()) + Utils.chat(" &6-> &cMe&6]&f ") + Utils.chat(message));
                }
            }
        } else {
            messager.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
