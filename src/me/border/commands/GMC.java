package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMC implements CommandExecutor {

    private Main plugin;

    public GMC(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("gmc").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.gamemode.creative")) {
            if (args.length == 0) {
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative_message")));
            } else {
                if (!p.hasPermission("essentials.gamemode.creative.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        target.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative_others_message")));
                        target.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative_message")) + Utils.chat(target.getDisplayName() + " &6!"));
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
