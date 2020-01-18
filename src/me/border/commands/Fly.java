package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Fly implements CommandExecutor {

    private ArrayList<Player> list_of_flying_people = new ArrayList<>();
    private Main plugin;

    public Fly(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.fly")) {
            if (args.length == 0) {
                if (list_of_flying_people.contains(p)) {
                    list_of_flying_people.remove(p);
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_disabled_message")));
                    return true;
                }  else if (!list_of_flying_people.contains(p)){
                    list_of_flying_people.add(p);
                    p.setAllowFlight(true);
                    p.setFlying(true);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_enabled_message")));
                }
            } else {
                if (!p.hasPermission("essentials.fly.others")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        if (list_of_flying_people.contains(target)) {
                            list_of_flying_people.remove(target);
                            target.setAllowFlight(false);
                            target.setFlying(false);
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_disabled_others_message")) + " " + target.getDisplayName());
                            target.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_disabled_message")));
                            return true;
                        } else if (!list_of_flying_people.contains(target)){
                            list_of_flying_people.add(target);
                            target.setAllowFlight(true);
                            target.setFlying(true);
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_enabled_others_message")) + " " + target.getDisplayName());
                            target.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_enabled_message")));
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
