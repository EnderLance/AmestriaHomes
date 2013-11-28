package me.Amestria.AmestriaHomes;

import java.util.EventListener;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Homes extends JavaPlugin implements EventListener 
{
	
	public boolean newplayer;
	private boolean isBlacklisted;

	public void onEnable() 
	{
		getLogger().info("AmestriaHomes has been enabled");
		this.saveDefaultConfig();
		this.getConfig();
		this.saveConfig();
		List<World> worlds = getServer().getWorlds();
		for(int i = 0; i < worlds.size(); i++)
		{
			if(!(this.getConfig().contains("worlds."+worlds.get(i).getName())))
			{
				this.getConfig().set("worlds."+worlds.get(i).getName(), "none");
				this.saveConfig();
				getLogger().info(worlds.get(i).getName()+" has been added.");
			}
			else if(this.getConfig().contains(worlds.get(i).getName()))
			{
				getLogger().info(worlds.get(i).getName()+" has been found.");
			}
		}
	}

	public void onDisable() 
	{
		getLogger().info("AmestriaHomes is now Disabled");
		saveConfig();
	}
	
	public boolean detectNewPlayer(Player player)
	{
		if(!(this.getConfig().contains("homes."+player.getName())))
		{
			newplayer = true;
		}
		else if(this.getConfig().contains("homes."+player.getName()))
		{
			newplayer = false;
		}
		return newplayer;
	}
	
	public boolean isOnBlacklist(String world)
	{
		if(this.getConfig().getString("worlds."+world).equalsIgnoreCase("none"))
		{
			isBlacklisted = false;
		}
		else if(!(this.getConfig().getString("worlds."+world).equalsIgnoreCase("none")))
		{
			isBlacklisted = true;
		}
		
		return isBlacklisted;
	}
	
	@EventHandler
	public void onPLayerJoinEvent(PlayerJoinEvent event) 
	{
		if(detectNewPlayer(event.getPlayer())) 
		{
			for(int i = 1; i<4; i++)
			{
				Location spawnloc = (Location) getServer().getWorld(event.getPlayer().getWorld().getName()).getSpawnLocation();
				double locX = spawnloc.getX();
				double locY = spawnloc.getY();
				double locZ = spawnloc.getZ();
				this.getConfig().set("homes."+event.getPlayer().getName()+"."+i+".x", locX);
				this.getConfig().set("homes."+event.getPlayer().getName()+"."+i+".y", locY);
				this.getConfig().set("homes."+event.getPlayer().getName()+"."+i+".z", locZ);
				this.saveConfig();
			}
		}
	}

	public void goHome(Player player, String number)
	{
		double x = this.getConfig().getDouble("homes."+player.getName()+"."+number+".x");
		double y = this.getConfig().getDouble("homes."+player.getName()+"."+number+".y");
		double z = this.getConfig().getDouble("homes."+player.getName()+"."+number+".z");
		String w = this.getConfig().getString("homes."+player.getName()+"."+number+".w");
		if((isOnBlacklist(w) && player.hasPermission(this.getConfig().getString("worlds."+w))) || !isOnBlacklist(w))
		{
			World world = getServer().getWorld(w);
			player.teleport(new Location(world, x, y, z));
			player.sendMessage(ChatColor.GOLD + "Welcome Home!");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You cannot teleport to that world.");
		}
	}
	
	public void setHome(Player player, double x, double y, double z, String number)
	{
		if((isOnBlacklist(player.getWorld().getName()) && player.hasPermission(this.getConfig().getString("worlds."+player.getWorld().getName()))) || !isOnBlacklist(player.getWorld().getName()))
		{
			this.getConfig().set("homes."+player.getName()+"."+number+".x", x);
			this.getConfig().set("homes."+player.getName()+"."+number+".y", y);
			this.getConfig().set("homes."+player.getName()+"."+number+".z", z);
			this.getConfig().set("homes."+player.getName()+"."+number+".w", player.getWorld().getName());
			this.saveConfig();
			player.sendMessage(ChatColor.GOLD +"You have set a home here.");
		}
		else
		{
			player.sendMessage(ChatColor.RED + "You cannot set a home in this world.");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender instanceof Player) 
		{
			Player player = (Player) sender;
			if(label.equalsIgnoreCase("sethome"))
			{
				if(args.length == 1) 
				{
					Location loc = player.getLocation();
					double locX = loc.getX();
					double locY = loc.getY();
					double locZ = loc.getZ();
					if(player.hasPermission("amestriahomes.home."+args[0]))
					{
						setHome(player, locX, locY, locZ, args[0]);	
					}
					else if(!player.hasPermission("amestriahomes.home."+args[0]))
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to set that home.");
					}
				}
				else if(args.length != 1)
				{
					player.sendMessage(ChatColor.RED +"Usage: /sethome <number>");
				}
			}
			else if(label.equalsIgnoreCase("home"))
			{	
				if(args.length == 1) 
				{
					if(player.hasPermission("amestriahomes.home."+args[0]))
					{
						goHome(player, args[0]);
					}
					else if(!player.hasPermission("amestriahomes.home."+args[0]))
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to use that home.");
					}
				}
				else if(args.length == 2) 
				{
					if(player.hasPermission("amestriahomes.others"))
					{
						Player target = Bukkit.getServer().getPlayer(args[1]);
						if(target.hasPermission("amestriahomes.home."+args[0]))
						{
							goHome(target, args[0]);
							player.sendMessage(ChatColor.GOLD + "You have teleported " + target.getName() + " home.");
						}
						else if(!target.hasPermission("amestriahomes.home."+args[0]))
						{
							player.sendMessage(ChatColor.RED + target.getName() + " cannot be teleported to that home.");
						}
					}
					else if(!(player.hasPermission("amestriahomes.others")))
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to teleport other players.");
					}
				}
			}
			else if(label.equalsIgnoreCase("blacklist"))
			{
				if(args.length == 2)
				{
					if(args[0].equalsIgnoreCase("add"))
					{
						if(player.hasPermission("blacklist.add"))
						{
							World world = getServer().getWorld(args[1]);
							if((this.getConfig().getString("worlds."+world.getName()).equals("none")))
							{
								this.getConfig().set("worlds."+world.getName(), "blacklist.enter."+world.getName());
								this.saveConfig();
								player.sendMessage(ChatColor.GREEN + world.getName() + " has been added from the blacklist.");
							}
							else
							{
								player.sendMessage(ChatColor.RED + "That world is already in the blacklist.");
							}
						}
						else if(!(player.hasPermission("blacklist.add")))
						{
							player.sendMessage(ChatColor.RED + "You don't have permission to add worlds from the blacklist.");
						}
					}
					else if(args[0].equalsIgnoreCase("remove"))
					{
						if(player.hasPermission("blacklist.remove"))
						{
							World world = getServer().getWorld(args[1]);
							if(!(this.getConfig().getString("worlds."+world.getName()).equals("none")))
							{
								this.getConfig().set("worlds."+world.getName(), "none");
								this.saveConfig();
								player.sendMessage(ChatColor.GREEN + world.getName() + " has been removed from the blacklist.");
							}
							else
							{
								player.sendMessage(ChatColor.RED + "That world is not on the blacklist.");
							}
						}
						else if(!(player.hasPermission("blacklist.remove")))
						{
							player.sendMessage(ChatColor.RED + "You don't have permission to remove worlds from the blacklist.");
						}
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Invalid Parameters.");
				}
			}
		}
		return false;
	}
}
