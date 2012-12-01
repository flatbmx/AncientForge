package com.podts.ancientforge;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AFCommand {
	
	private static HashMap<String,AFCommand> commands = new HashMap<String,AFCommand>();
	
	public static HashMap<String,AFCommand> getCommandList() {
		return commands;
	}
	
	public static AFCommand getCommand(String name) {
		return commands.get(name);
	}
	
	private String name;
	private String usage;
	private boolean needsplayer = true;
	private int minimumarguments = 0;
	
	public String getName() {
		return name;
	}
	
	public boolean needsPlayer() {
		return needsplayer;
	}
	
	public void setneedsPlayer(boolean needs) {
		needsplayer = needs;
	}
	
	public int getMinimumArguments() {
		return minimumarguments;
	}
	
	public void setMinimumARguements(int min) {
		minimumarguments = min;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	public void run(CommandSender sender, String[] args) {
		
		if (needsplayer) {
			if ( !(sender instanceof Player) ) {
				sender.sendMessage(ChatColor.DARK_RED + "Command requires a player, cannot be executed by console.");
				return;
			}
		}
		
		handle(sender, args);
		
	}
	
	public abstract void handle(CommandSender sender, String[] args);
	
	public AFCommand(String name, int minargs) {
		this.name = name;
		this.minimumarguments = minargs;
		commands.put(name, this);
	}
	
}
