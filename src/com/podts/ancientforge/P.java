package com.podts.ancientforge;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.podts.ancientforge.commands.*;
import com.podts.ancientforge.listeners.DamageHandler;
import com.podts.ancientforge.listeners.EntityDeathHandler;
import com.podts.ancientforge.listeners.HandHandler;
import com.podts.ancientforge.listeners.InventoryHandler;
import com.podts.ancientforge.listeners.ItemHandler;
import com.podts.ancientforge.listeners.LoginHandler;
import com.podts.ancientforge.listeners.LogoutHandler;
import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;
import com.podts.ancientforge.namemodifier.NameModifier;
import com.podts.ancientforge.player.AFPlayer;

public class P extends JavaPlugin {
	
	private static P instance;
	private static FileConfiguration config;
	private static Logger logger;
	private static Random random;
	
	public static P getPluginInstance() {
		return instance;
	}
	
	public static FileConfiguration getConfigFile() {
		return config;
	}
	
	public static Logger getPluginLogger() {
		return logger;
	}
	
	public static Random getRandom() {
		return random;
	}
	
	@Override
    public void onEnable(){
        
		instance = this;
		logger = getLogger();
		config = this.getConfig();
		random = new Random();
		
		config.addDefault("ItemDropRate-Mobs", .03f);
		config.addDefault("ItemDropRate-Wither", .3f);
		
		config.options().copyDefaults(true);
		saveConfig();
		
		getServer().getPluginManager().registerEvents(new LoginHandler(), this);
		getServer().getPluginManager().registerEvents(new LogoutHandler(), this);
		getServer().getPluginManager().registerEvents(new DamageHandler(), this);
		getServer().getPluginManager().registerEvents(new HandHandler(), this);
		getServer().getPluginManager().registerEvents(new EntityDeathHandler(), this);
		getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
		getServer().getPluginManager().registerEvents(new ItemHandler(), this);
		
		NameModifier.initModifiers();
		getLogger().info("Loaded " + ItemPrefix.getPrefixs().size() + " Prefixs.");
		getLogger().info("Loaded " + ItemSuffix.getSuffixs().size() + " Suffixs.");
		
		new Command_Stats();
		new Command_RandomItem();
		
		for (Player p : getServer().getOnlinePlayers()) {
			new AFPlayer(p);
		}
		
    }
 
    @Override
    public void onDisable() {
        
    	saveConfig();
    	
    	for (AFPlayer afp : AFPlayer.getPlayers().values()) {
    		
    		afp.removePotionEffects();
    		
    	}
    	
    	instance = null;
    	random = null;
    	
    }
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	if (cmd.getLabel().equalsIgnoreCase("ancientforge")) {
    		
    		if (args.length > 0) {
    			
    			if (AFCommand.getCommand(args[0]) != null) {
    				
    				AFCommand command = AFCommand.getCommand(args[0]);
    				
    				if (command == null) {
    					sender.sendMessage(ChatColor.DARK_RED + "No AncientForge command called that.");
    					return false;
    				}
    				
    				if (command.getMinimumArguments() <= args.length-1) {
    					
    					String[] newargs = new String[args.length-1];
    					
    					for (int i=1; i<args.length; i++)
    						newargs[i-1] = args[i];
    					
    					command.run(sender, newargs);
    					
    				}
    				else {
    					
    				}
    				
    			}
    			else {
    				
    			}
    			
    		}
    		
    	}
    	
    	return false;
    	
    }
    
}
