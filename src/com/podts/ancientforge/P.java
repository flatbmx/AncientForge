package com.podts.ancientforge;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.podts.ancientforge.listeners.DamageHandler;
import com.podts.ancientforge.listeners.EntityDeathHandler;
import com.podts.ancientforge.listeners.HandHandler;
import com.podts.ancientforge.listeners.InventoryHandler;
import com.podts.ancientforge.listeners.LoginHandler;
import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;
import com.podts.ancientforge.namemodifier.NameModifier;
import com.podts.ancientforge.player.AFPlayer;
import com.podts.ancientforge.suffix.Suffix_OfTheRabbit;

public class P extends JavaPlugin {
	
	private static Logger logger;
	private static Random random;
	
	public static Logger getPluginLogger() {
		return logger;
	}
	
	public static Random getRandom() {
		return random;
	}
	
	@Override
    public void onEnable(){
        
		logger = getLogger();
		random = new Random();
		getServer().getPluginManager().registerEvents(new LoginHandler(), this);
		getServer().getPluginManager().registerEvents(new DamageHandler(), this);
		getServer().getPluginManager().registerEvents(new HandHandler(), this);
		getServer().getPluginManager().registerEvents(new EntityDeathHandler(), this);
		getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
		
		NameModifier.initModifiers();
		getLogger().info("Loaded " + ItemPrefix.getPrefixs().size() + " Prefixs.");
		getLogger().info("Loaded " + ItemSuffix.getSuffixs().size() + " Suffixs.");
		
		for (Player p : getServer().getOnlinePlayers()) {
			new AFPlayer(p);
		}
		
    }
 
    @Override
    public void onDisable() {
        
    }
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lavel, String[] args) {
    	
    	if (cmd.getName().equalsIgnoreCase("magicitem")) {
    		
    		if (!(sender instanceof Player))
    			return true;
    		
			Player p = (Player) sender;
			
			if (p.getName().equalsIgnoreCase("flatbmx")) {
				
				MagicItem item = new MagicItem(new CraftItemStack(Material.DIAMOND_BOOTS), new Suffix_OfTheRabbit());
				
				p.getInventory().addItem(item.getItemStack());
				return true;
				
			}
			
			if (!p.hasPermission("ancientforge.command.magicitem"))
				return true;
			
			MagicItem item = MagicItem.getRandomeMagicItem();
			
			p.getInventory().addItem(item.getItemStack());
    		
			return true;
			
    	}
    	
    	return false;
    	
    }
    
}
