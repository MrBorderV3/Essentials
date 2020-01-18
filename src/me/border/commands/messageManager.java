package me.border.commands;

import me.border.Main;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class messageManager {

    Main plugin;

    HashMap<Player, Player> conversations = new HashMap<Player, Player>();

    public messageManager(Main plugin) {
        this.plugin = plugin;
    }

    public void setReplyTarget(Player messager, Player receiver) {
        conversations.put(messager, receiver);
        conversations.put(receiver, messager);
    }

    public Player getReplyTarget(Player messager) {
        return conversations.get(messager);
    }
}
