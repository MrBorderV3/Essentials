package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMA implements CommandExecutor {

    private Main plugin;

    public GMA(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("gma").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_messagge")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.gamemode.adventure")) {
            if (args.length == 0) {
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure_message")));
            } else {
                if (!p.hasPermission("essentials.gamemode.adventure.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                    return true;
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        target.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure_others_message")));
                        target.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure_message")) + Utils.chat(target.getDisplayName() + " &6!"));
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}
