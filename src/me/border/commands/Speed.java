package me.border.commands;

import me.border.Main;
import me.border.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

    private Main plugin;

    public Speed(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("speed").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_error_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.speed")) {
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("notEnoughArguments")));
                return true;
            } else {
                if (args[0].equals("0")){
                    if (p.isFlying() == true && p.getAllowFlight() == true){
                        p.setFlySpeed(0);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "0"));
                        return true;
                    } else {
                        p.setWalkSpeed(0);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "0"));
                        return true;
                    }
                } else if (args[0].equals("1")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.1);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "1"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.2);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "1"));
                        return true;
                    }
                } else if (args[0].equals("2")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.2);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "2"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.3);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "2"));
                        return true;
                    }
                } else if (args[0].equals("3")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.3);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "3"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.4);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "3"));
                        return true;
                    }
                } else if (args[0].equals("4")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.4);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "4"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.5);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "4"));
                        return true;
                    }
                } else if (args[0].equals("5")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.5);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "5"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.6);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "5"));
                        return true;
                    }
                } else if (args[0].equals("6")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.6);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "6"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.7);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "6"));
                        return true;
                    }
                } else if (args[0].equals("7")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.7);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "7"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.8);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "7"));
                        return true;
                    }
                } else if (args[0].equals("8")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.8);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "8"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.9);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "8"));
                        return true;
                    }
                } else if (args[0].equals("9")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed((float) 0.9);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "9"));
                        return true;
                    } else {
                        p.setWalkSpeed((float) 0.95);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "9"));
                        return true;
                    }
                } else if (args[0].equals("10")) {
                    if (p.isFlying() == true && p.getAllowFlight() == true) {
                        p.setFlySpeed(1);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "10"));
                        return true;
                    } else {
                        p.setWalkSpeed(1);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "10"));
                        return true;
                    }
                } else {
                    if (args.length == 1 || args.length == 0) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("notEnoughArguments")));
                        return true;
                    }
                    if (!p.hasPermission("essentials.speed.others")) {
                        p.sendMessage(plugin.getConfig().getString("no_perm_message"));
                        return true;
                    } else {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target == null) {
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("player_offline_message")));
                            return true;
                        } else if (args[1].equals("0")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true){
                                target.setFlySpeed(0);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "0"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed(0);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "0"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6speed!"));
                            }
                        } else if (args[1].equals("1")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.1);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "1"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.2);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "1"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("2")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.2);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "2"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.3);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "2"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("3")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.3);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "3"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.4);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "3"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("4")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.4);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "4"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.5);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "4"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("5")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.5);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "5"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.6);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "5"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("6")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.6);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "6"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.7);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "6"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("7")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.7);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "7"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.8);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "7"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6speed!"));
                            }
                        } else if (args[1].equals("8")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.8);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "8"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s&6 speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.9);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "8"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("9")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed((float) 0.9);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "9"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6speed!"));
                            } else {
                                target.setWalkSpeed((float) 0.95);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "9"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else if (args[1].equals("10")) {
                            if (target.isFlying() == true && target.getAllowFlight() == true) {
                                target.setFlySpeed(1);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.fly_speed_changed") + "10"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            } else {
                                target.setWalkSpeed(1);
                                target.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.walk_speed_changed") + "10"));
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("Speed.speed_others_successfully") + target.getDisplayName() + "'s &6 speed!"));
                            }
                        } else {
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
                        }
                    }
                }
            }
        }
        return false;
    }
}
