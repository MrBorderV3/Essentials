package me.border;

import me.border.commands.*;
import me.border.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public messageManager mM;

    @Override
    public void onEnable(){
        saveDefaultConfig();
        new Mute(this);
        new Fly(this);
        new Feed(this);
        new Heal(this);
        new Smite(this);
        new Teleport(this);
        new TeleportAll(this);
        new TeleportHere(this);
        new God(this);
        new Broadcast(this);
        new Alert(this);
        new Gamemode(this);
        new GMS(this);
        new GMC(this);
        new GMA(this);
        new GMSP(this);
        new Kill(this);
        new Suicide(this);
        new Message(this);
        new Reply(this);
        new Speed(this);
        new FreezeChat(this);
        new Burn(this);
        new Kick(this);
        getCommand("nick").setExecutor(new Nick(this));
        getCommand("realname").setExecutor(new Nick(this));
        getServer().getPluginManager().registerEvents(new FreezeChat(this), this);
        getServer().getPluginManager().registerEvents(new Mute(this) , this);
        getServer().getPluginManager().registerEvents(new Nick(this), this);
        mM = new messageManager(this);
    }
    public void onDisable() {
        System.out.println("[Essentials] Disabling and Saving Essentials 1.0.0");
    }
}


