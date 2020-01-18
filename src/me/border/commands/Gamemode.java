package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    private Main plugin;

    public Gamemode(Main plugin){
        this.plugin = plugin;

        plugin.getCommand("gamemode").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.gamemode")) {
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.current_gamemode")) + Utils.chat("&a ") + p.getGameMode() + Utils.chat("&6!"));
                return true;
            } else if ((args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival"))) {
                if (!p.hasPermission("essentials.gamemode.survival")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                    return true;
                } else {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_survival_message")));
                }
            } else if ((args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative"))) {
                if (!p.hasPermission("essentials.gamemode.creative")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                    return true;
                } else {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative_message")));
                }
            } else if ((args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure"))) {
                if (!p.hasPermission("essentials.gamemode.adventure")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                    return true;
                } else {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure_message")));
                }
            } else if ((args[0].equals("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator"))) {
                if (!p.hasPermission("essentials.gamemode.spectator")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                    return true;
                } else {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_spectator_message")));
                }
            } else {
                if (!p.hasPermission("essentials.gamemode.others.survival") && !p.hasPermission("essentials.gamemode.others") && !p.hasPermission("essentials.gamemode.others.creative") && !p.hasPermission("essentials.gamemode.others.adventure") && !p.hasPermission("essentials.gamemode.others.spectator")) {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                    return true;
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                    } else {
                        if (!p.hasPermission("essentials.gamemode.others") && args.length == 1) {
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                            return true;
                        } else if (p.hasPermission("essentials.gamemode.others") && args.length == 1) {
                            p.sendMessage(Utils.chat("&8[&eEssentials&8] &b") + target.getDisplayName() + Utils.chat("'s &6current gamemode is &a") + target.getGameMode() + Utils.chat("&6!"));
                        } else if ((args.length == 2 && args[1].equals("0") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("survival"))) {
                            if (!p.hasPermission("essentials.gamemode.others.survival")) {
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                                return true;
                            } else {
                                target.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_survival_others_message")) + target.getDisplayName() + Utils.chat(" &6!"));
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_survival_message")));
                            }
                        } else if ((args.length == 2 && args[1].equals("1") || args[1].equalsIgnoreCase("c") || args[1].equalsIgnoreCase("creative"))) {
                            if (!p.hasPermission("essentials.gamemode.others.creative")) {
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                                return true;
                            } else {
                                target.setGameMode(GameMode.CREATIVE);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative_message")));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative_others_message")) + target.getDisplayName() + Utils.chat(" &6!"));
                            }
                        } else if ((args.length == 2 && args[1].equals("2") || args[1].equalsIgnoreCase("a") || args[1].equalsIgnoreCase("adventure"))) {
                            if (!p.hasPermission("essentials.gamemode.others.adventure")) {
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                                return true;
                            } else {
                                target.setGameMode(GameMode.ADVENTURE);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure_message")));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure_others_message")) + target.getDisplayName() + Utils.chat(" &6!"));
                            }
                        } else if ((args.length == 2 && args[1].equals("3") || args[1].equalsIgnoreCase("sp") || args[1].equalsIgnoreCase("spectator"))) {
                            if (!p.hasPermission("essentials.gamemode.others.spectator")) {
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                                return true;
                            } else {
                                target.setGameMode(GameMode.SPECTATOR);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_spectator_message")));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_spectator_others_message")) + target.getDisplayName() + Utils.chat(" &6!"));
                            }
                        } else {
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.incorrect_usage")));
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
