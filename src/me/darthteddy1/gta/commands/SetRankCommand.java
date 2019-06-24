package me.darthteddy1.gta.commands;

import me.darthteddy1.gta.permission.Rank;
import me.darthteddy1.gta.permission.RankManager;
import me.darthteddy1.gta.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(RankManager.getRank(p) == Rank.ADMIN || RankManager.getRank(p) == Rank.OWNER) {
                if(args.length == 2) {
                    if(Bukkit.getPlayer(args[0]) != null) {
                        if(args[1].equalsIgnoreCase("owner")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.OWNER);
                        } else if(args[1].equalsIgnoreCase("admin")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.ADMIN);
                        } else if(args[1].equalsIgnoreCase("mod")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.MOD);
                        } else if(args[1].equalsIgnoreCase("helper")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.HELPER);
                        } else if(args[1].equalsIgnoreCase("mafiaboss")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.MAFIABOSS);
                        } else if(args[1].equalsIgnoreCase("hitman")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.HITMAN);
                        } else if(args[1].equalsIgnoreCase("hoodlum")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.HOODLUM);
                        } else if(args[1].equalsIgnoreCase("crook")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.CROOK);
                        } else if(args[1].equalsIgnoreCase("default")) {
                            if(Bukkit.getPlayer(args[0]).isOnline()) {
                                Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                            }
                            Rank.setRank(Bukkit.getPlayer(args[0]), Rank.DEFAULT);
                        } else {
                            sender.sendMessage(MessageUtils.PREFIX_BAD + "Invalid rank supplied");
                        }
                    }
                }
            } else {
                p.sendMessage(MessageUtils.NO_PERMISSION);
            }
        } else {
            if(args.length == 2) {
                if(Bukkit.getPlayer(args[0]) != null) {
                    if(args[1].equalsIgnoreCase("owner")) {
                    if(Bukkit.getPlayer(args[0]).isOnline()) {
                        Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                    }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.OWNER);
                    } else if(args[1].equalsIgnoreCase("admin")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.ADMIN);
                    } else if(args[1].equalsIgnoreCase("mod")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.MOD);
                    } else if(args[1].equalsIgnoreCase("helper")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.HELPER);
                    } else if(args[1].equalsIgnoreCase("mafiaboss")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.MAFIABOSS);
                    } else if(args[1].equalsIgnoreCase("hitman")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.HITMAN);
                    } else if(args[1].equalsIgnoreCase("hoodlum")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.HOODLUM);
                    } else if(args[1].equalsIgnoreCase("crook")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.CROOK);
                    } else if(args[1].equalsIgnoreCase("default")) {
                        if(Bukkit.getPlayer(args[0]).isOnline()) {
                            Bukkit.getPlayer(args[0]).sendMessage(MessageUtils.PREFIX_GOOD + "Your rank has been updated!\n§7Please relog to load your new permissions!");
                        }
                        Rank.setRank(Bukkit.getPlayer(args[0]), Rank.DEFAULT);
                    } else {
                        sender.sendMessage("Invalid rank");
                    }
                }
            }
        }
        return true;
    }

}
