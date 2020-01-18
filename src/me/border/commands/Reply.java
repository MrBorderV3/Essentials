package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import me.border.commands.messageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply implements CommandExecutor {

    private Main plugin;

    public Reply(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("reply").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player messager = (Player) sender;

        if (sender.hasPermission("essentials.message")) {
            Player receiver = plugin.mM.getReplyTarget(messager);
            if (plugin.mM.getReplyTarget(receiver) == null) {
                    sender.sendMessage(Utils.chat(plugin.getConfig().getString("Message.reply_offline_message")));
                    return true;
                } else {
                    String message = "";
                    for (int i = 0; i < args.length; i++){
                        message += "" + args[i];
                    }
                    messager.sendMessage(Utils.chat("&6[&cMe&6 ->&b ") + Utils.chat(receiver.getName()) + Utils.chat("&6]&f ") + Utils.chat(message));
                    receiver.sendMessage(Utils.chat("&6[&b") + Utils.chat(messager.getName()) + Utils.chat(" &6-> &cMe&6]&f ") + Utils.chat(message));
                }
            } else {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
