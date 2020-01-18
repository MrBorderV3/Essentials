package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class FreezeChat implements CommandExecutor, Listener {

    private ArrayList<Object> list_for_toggle = new ArrayList<>();
    private Main plugin;
    private Object toggle;

    public FreezeChat(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("freezechat").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.freezechat")) {
            if (list_for_toggle.contains(toggle)) {
                list_for_toggle.remove(toggle);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("FreezeChat.chat_unfrozen_message")));
                Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("FreezeChat.chat_unfrozen_global")));
            } else if (!list_for_toggle.contains(toggle)) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("FreezeChat.chat_frozen_message")));
                list_for_toggle.add(toggle);
                Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("FreezeChat.chat_frozen_global")));
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }
        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (list_for_toggle.contains(toggle)) {
            if (!e.getPlayer().hasPermission("essentials.staff")) {
                e.getPlayer().sendMessage(Utils.chat(plugin.getConfig().getString("FreezeChat.player_frozen_message")));
                e.setCancelled(true);
                return;
            }
        }
        if (plugin.getConfig().getConfigurationSection("muted_players") != null) {
            for (String section : plugin.getConfig().getConfigurationSection("muted_players").getKeys(false)) {
                if (section.equals(e.getPlayer().getUniqueId().toString())) {
                    e.getPlayer().sendMessage(Utils.chat(plugin.getConfig().getString("Mute.muted_chat_message")));
                    e.setCancelled(true);
                    return;
                }
            }
        }
        if (plugin.getConfig().contains(e.getPlayer().getName())) {
            e.setCancelled(true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Utils.chat("&f<&b~") + Utils.chat(plugin.getConfig().getString(e.getPlayer().getName())) + Utils.chat("&f> ") + Utils.chat(e.getMessage()));
            }
        }
    }
}

