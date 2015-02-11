package io.github.jfischer00.havocplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HavocPluginCommandExecutor implements CommandExecutor {
	public HavocPluginCommandExecutor(HavocPlugin plugin) {
	}

	public void tellConsole(CommandSender sender, String message) {
		sender.sendMessage(message);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("havoc")) {
			if (args.length != 2) {
				tellConsole(sender, ChatColor.RED
						+ "Invalid number of arguments.");
				return false;
			}

			boolean playerFound = false;

			for (Player target : Bukkit.getServer().getOnlinePlayers()) {
				if (target.getName().equalsIgnoreCase(args[0])) {
					tellConsole(sender, ChatColor.RED + "Target obtained.");
					playerFound = true;

					if (args[1].equalsIgnoreCase("ghasts")) {
						Bukkit.getServer().dispatchCommand(
								Bukkit.getServer().getConsoleSender(),
								"spawnmob ghast,ghast 5 " + target.getName());
						target.sendMessage(ChatColor.YELLOW
								+ "Beware of fireballs!");
						return true;
					} else if (args[1].equalsIgnoreCase("creepers")) {
						Bukkit.getServer().dispatchCommand(
								Bukkit.getServer().getConsoleSender(),
								"spawnmob creeper,creeper 5 "
										+ target.getName());
						target.sendMessage(ChatColor.YELLOW
								+ "Watch out for explosions!!");
						return true;
					} else if (args[1].equalsIgnoreCase("fire")) {
						target.setFireTicks(1200);
						target.sendMessage(ChatColor.YELLOW
								+ "Ouch, Hot! Better find some water.");
						return true;
					} else if (args[1].equalsIgnoreCase("kill")) {
						target.getWorld().createExplosion(target.getLocation(),
								4F);
						target.setHealth(0);
						target.sendMessage(ChatColor.YELLOW
								+ "BOOM! What happened?");
						return true;
					} else if (args[1].equalsIgnoreCase("lightning")) {
						target.getWorld().strikeLightning(target.getLocation());
						target.sendMessage(ChatColor.YELLOW
								+ "FLASH! That's hot.");
						return true;
					} else if (args[1].equalsIgnoreCase("hide")) {
						for (Player hidden : Bukkit.getOnlinePlayers()) {
							if (!hidden.getName().equalsIgnoreCase(args[0])) {
								target.hidePlayer(hidden);
								target.sendMessage(ChatColor.YELLOW
										+ "Where did everyone go?");
								return true;
							}
						}
					} else if (args[1].equalsIgnoreCase("show")) {
						for (Player hidden : Bukkit.getOnlinePlayers()) {
							if (!hidden.getName().equalsIgnoreCase(args[0])) {
								target.showPlayer(hidden);
								target.sendMessage(ChatColor.YELLOW
										+ "Everyone has now reappeared!");
								return true;
							}
						}
					} else if (args[1].equalsIgnoreCase("ultimate")) {
						Bukkit.getServer()
								.dispatchCommand(
										Bukkit.getServer().getConsoleSender(),
										"summon Creeper {Riding:{id:\"Creeper\",Equipment:[{Count:1,id:diamond_sword,tag:{display:{Name:Sword of Destiny},ench:[{id:20,lvl:2},{id:19,lvl:2},{id:16,lvl:5},{id:34,lvl:3}]}},{Count:1,id:diamond_boots,tag:{display:{Name:Boots of Epicness},ench:[{id:8,lvl:3},{id:2,lvl:4},{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}},{Count:1,id:diamond_leggings,tag:{display:{Name:Leggings of Epicness},ench:[{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}},{Count:1,id:diamond_chestplate,tag:{display:{Name:Chestplate of Epicness},ench:[{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}},{Count:1,id:diamond_helmet,tag:{display:{Name:Helmet of Epicness},ench:[{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}}],CustomName:Creepsies,CustomNameVisible:1,DropChances:[2.0F,2.0F,2.0F,2.0F,2.0F],ExplosionRadius:20,Fuse:30,powered:1},Equipment:[{Count:1,id:diamond_sword,tag:{display:{Name:Sword of Destiny},ench:[{id:20,lvl:2},{id:19,lvl:2},{id:16,lvl:5},{id:34,lvl:3}]}},{Count:1,id:diamond_boots,tag:{display:{Name:Boots of Epicness},ench:[{id:8,lvl:3},{id:2,lvl:4},{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}},{Count:1,id:diamond_leggings,tag:{display:{Name:Leggings of Epicness},ench:[{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}},{Count:1,id:diamond_chestplate,tag:{display:{Name:Chestplate of Epicness},ench:[{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}},{Count:1,id:diamond_helmet,tag:{display:{Name:Helmet of Epicness},ench:[{id:0,lvl:4},{id:7,lvl:3},{id:34,lvl:3}]}}],CustomName:Creepsies,CustomNameVisible:1,DropChances:[2.0F,2.0F,2.0F,2.0F,2.0F],ExplosionRadius:20,Fuse:30,powered:1} "
												+ target.getLocation().getX()
												+ " "
												+ target.getLocation().getY()
												+ " "
												+ target.getLocation().getZ());
						target.sendMessage(ChatColor.YELLOW
								+ "You now must face the ultimate challenge!");
						return true;
					} else {
						tellConsole(sender, ChatColor.RED + args[1]
								+ " is not a type of havoc.");
						return false;
					}
				}
			}

			if (!playerFound) {
				tellConsole(sender, ChatColor.RED + args[0] + " is not online.");
				return true;
			}
		}
		return false;
	}
}
