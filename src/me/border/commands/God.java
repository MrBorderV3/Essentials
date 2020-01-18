package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class God implements CommandExecutor {

    private ArrayList<Player> list_of_god_players = new ArrayList<>();
    private Main plugin;

    public God(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("god").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.god")){
            if (args.length == 0) {
                if (list_of_god_players.contains(p)) {
                    list_of_god_players.remove(p);
                    p.setInvulnerable(false);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("God.god_disabled_message")));
                } else if(!list_of_god_players.contains(p)){
                    list_of_god_players.add(p);
                    p.setInvulnerable(true);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("God.god_enabled_message")));
                }
            } else {
                if (!p.hasPermission("essentials.god.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        if(list_of_god_players.contains(target)){
                            list_of_god_players.remove(target);
                            target.setInvulnerable(false);
                            target.sendMessage(Utils.chat(plugin.getConfig().getString("God.god_disabled_message")));
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("God.god_disabled_others_message")) + " " + target.getDisplayName() + Utils.chat("&6!"));
                        } else if (!list_of_god_players.contains(target)) {
                            list_of_god_players.add(target);
                            target.setInvulnerable(true);
                            target.sendMessage(Utils.chat(plugin.getConfig().getString("God.god_enabled_message")));
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("God.god_enabled_others_message")) + " " + target.getDisplayName() + Utils.chat("&6!"));
                        }
                    }
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}