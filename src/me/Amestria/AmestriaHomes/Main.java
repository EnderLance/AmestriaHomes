package me.Amestria.AmestriaHomes;

import java.util.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements EventListener {
	
	public void onEnable() {
		getLogger().info("AmestriaHomes has been enabled");
		getConfig();
		}
	
	@EventHandler
	public void onPLayerJoinEvent(PlayerJoinEvent event) {
		if(!event.getPlayer().hasPlayedBefore()) {
			getConfig().set("amestriaHomes.1.x." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.1.y." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.1.z." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.1.w." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.2.x." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.2.y." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.2.z." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.2.w." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.3.x." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.3.y." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.3.z." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.3.w." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.4.x." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.4.y." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.4.z." + event.getPlayer(), "0");
			getConfig().set("amestriaHomes.4.w." + event.getPlayer(), "0");
		}
	}

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(label.equalsIgnoreCase("sethome")){
				if(args.length == 1) {
					if(args[1].matches(".*[1-4].*") == true){				
						getConfig().set("amestriaHomes." + (args[1]) + ".x." + player.getName(), player.getLocation().getBlockX());
						getConfig().set("amestriaHomes." + (args[1]) + ".y." + player.getName(), player.getLocation().getBlockY());
						getConfig().set("amestriaHomes." + (args[1]) + ".z." + player.getName(), player.getLocation().getBlockZ());
						getConfig().set("amestriaHomes." + (args[1]) + ".w." + player.getName(), player.getWorld());
						saveConfig();
					}
					if(player.hasPermission("amestriaHomes.sethome.*")) {
					if(args[1].matches(".*[1-4].*") == true){				
						getConfig().set("amestriaHomes." + (args[1]) + ".x." + player.getName(), player.getLocation().getBlockX());
						getConfig().set("amestriaHomes." + (args[1]) + ".y." + player.getName(), player.getLocation().getBlockY());
						getConfig().set("amestriaHomes." + (args[1]) + ".z." + player.getName(), player.getLocation().getBlockZ());
						getConfig().set("amestriaHomes." + (args[1]) + ".w." + player.getName(), player.getWorld());
						saveConfig();
					}
					else if(args[1].matches(".*[1-4].*") == true){				
						getConfig().set("amestriaHomes." + (args[1]) + ".x." + player.getName(), player.getLocation().getBlockX());
						getConfig().set("amestriaHomes." + (args[1]) + ".y." + player.getName(), player.getLocation().getBlockY());
						getConfig().set("amestriaHomes." + (args[1]) + ".z." + player.getName(), player.getLocation().getBlockZ());
						getConfig().set("amestriaHomes." + (args[1]) + ".w." + player.getName(), player.getWorld());
						saveConfig();
					}
					else if(args[1].matches(".*[1-4].*") == true){				
						getConfig().set("amestriaHomes." + (args[1]) + ".x." + player.getName(), player.getLocation().getBlockX());
						getConfig().set("amestriaHomes." + (args[1]) + ".y." + player.getName(), player.getLocation().getBlockY());
						getConfig().set("amestriaHomes." + (args[1]) + ".z." + player.getName(), player.getLocation().getBlockZ());
						getConfig().set("amestriaHomes." + (args[1]) + ".w." + player.getName(), player.getWorld());
						saveConfig();
					}
					} else if(!player.hasPermission("amestriaHomes.sethome.*")) {
						player.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command");
					}
			} else if(args.length == 2) {
				
				if(player.hasPermission("amestriaHomes.sethome.*.player")) {
				
				Player target = Bukkit.getServer().getPlayer(args[2]);
				
				if(args[1].matches(".*[1-4].*") == true){				
					getConfig().set("amestriaHomes." + (args[1]) + ".x." + target.getName(), player.getLocation().getBlockX());
					getConfig().set("amestriaHomes." + (args[1]) + ".y." + target.getName(), player.getLocation().getBlockY());
					getConfig().set("amestriaHomes." + (args[1]) + ".z." + target.getName(), player.getLocation().getBlockZ());
					getConfig().set("amestriaHomes." + (args[1]) + ".w." + target.getName(), player.getWorld());
					saveConfig();
				}
				else if(args[1].matches(".*[1-4].*") == true){				
					getConfig().set("amestriaHomes." + (args[1]) + ".x." + target.getName(), player.getLocation().getBlockX());
					getConfig().set("amestriaHomes." + (args[1]) + ".y." + target.getName(), player.getLocation().getBlockY());
					getConfig().set("amestriaHomes." + (args[1]) + ".z." + target.getName(), player.getLocation().getBlockZ());
					getConfig().set("amestriaHomes." + (args[1]) + ".w." + target.getName(), player.getWorld());
					saveConfig();
				}
				else if(args[1].matches(".*[1-4].*") == true){				
					getConfig().set("amestriaHomes." + (args[1]) + ".x." + target.getName(), player.getLocation().getBlockX());
					getConfig().set("amestriaHomes." + (args[1]) + ".y." + target.getName(), player.getLocation().getBlockY());
					getConfig().set("amestriaHomes." + (args[1]) + ".z." + target.getName(), player.getLocation().getBlockZ());
					getConfig().set("amestriaHomes." + (args[1]) + ".w." + target.getName(), player.getWorld());
					saveConfig();
				}
				else if(args[1].matches(".*[1-4].*") == true){				
					getConfig().set("amestriaHomes." + (args[1]) + ".x." + target.getName(), player.getLocation().getBlockX());
					getConfig().set("amestriaHomes." + (args[1]) + ".y." + target.getName(), player.getLocation().getBlockY());
					getConfig().set("amestriaHomes." + (args[1]) + ".z." + target.getName(), player.getLocation().getBlockZ());
					getConfig().set("amestriaHomes." + (args[1]) + ".w." + target.getName(), player.getWorld());
					saveConfig();
				}
				}
				else if(!player.hasPermission("amestriaHomes.setHome.*.player")) {
					player.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command");
				}
				
			}
		}
			else if(label.equalsIgnoreCase("home")){
				
				if(args.length == 1) {
					if(args[1].matches(".*[1-4].*") == true){
						
					
					int x = getConfig().getInt("amestriaHomes." + (args[1]) + ".x." + player.getName());
					int y = getConfig().getInt("amestriaHomes." + (args[1]) + ".y." + player.getName());
					int z = getConfig().getInt("amestriaHomes." + (args[1]) + ".z." + player.getName());

					player.teleport(new Location(player.getWorld(), x, y, z));
					 
					 }
				}else if(args.length == 2) {
					if(args.length == 1) {
						if(args[1].matches(".*[1-4].*") == true){
							Player target = Bukkit.getServer().getPlayer(args[2]);
						
						int x = getConfig().getInt("amestriaHomes." + (args[1]) + ".x." + target.getName());
						int y = getConfig().getInt("amestriaHomes." + (args[1]) + ".y." + target.getName());
						int z = getConfig().getInt("amestriaHomes." + (args[1]) + ".z." + target.getName());
						
						player.teleport(new Location(player.getWorld(), x, y, z));
					
				}
				
				 
			}
		}
}
}
		return false;
}
	public void onDisabel() {
		getLogger().info("AmestriaHomes is now Disabled");
		saveConfig();
	}
}
