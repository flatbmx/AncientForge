package com.podts.ancientforge;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.podts.ancientforge.listeners.DamageHandler;
import com.podts.ancientforge.listeners.HandHandler;
import com.podts.ancientforge.listeners.LoginHandler;
import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;
import com.podts.ancientforge.namemodifier.NameModifier;
import com.podts.ancientforge.player.AFPlayer;
import com.podts.ancientforge.prefix.Prefix_Blessed;
import com.podts.ancientforge.suffix.Suffix_OfTheReckless;

public class P extends JavaPlugin {
	
	private static Logger logger;
	
	public static Logger getPluginLogger() {
		return logger;
	}
	
	@Override
    public void onEnable(){
        
		logger = getLogger();
		getServer().getPluginManager().registerEvents(new LoginHandler(), this);
		getServer().getPluginManager().registerEvents(new DamageHandler(), this);
		getServer().getPluginManager().registerEvents(new HandHandler(), this);
		
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
    	
    	if (cmd.getName().equalsIgnoreCase("test")) {
    		
    		if (!(sender instanceof Player))
    			return true;
    		
    		try {
    		
    		Player p = (Player) sender;
    		
    		CraftItemStack ditem = new CraftItemStack(Material.DIAMOND_SWORD);
    		
    		MagicItem item = new MagicItem(ditem, new Prefix_Blessed(), new Suffix_OfTheReckless());
    		
    		p.getInventory().addItem(item.getItemStack());
    		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	if (cmd.getName().equalsIgnoreCase("name")) {
    		
    		if (!(sender instanceof Player)) {
    			sender.sendMessage("Requires player.");
    			return true;
    		}
    		
    		Player p = (Player) sender;
    		
    		if (args.length != 1)
    			return true;
    		
    		ItemStack handitem = p.getItemInHand();
    		
    		if (handitem == null)
    			return true;
    		
    		NamedItem newitem = new NamedItem(handitem);
    		newitem.setName(args[0]);
    		
    		p.setItemInHand(newitem.getItemStack());
    		
    		return true;
    	}
    	
    	if (cmd.getName().equalsIgnoreCase("whatname")) {
    		
    		if (!(sender instanceof Player)) {
    			sender.sendMessage("Requires player.");
    			return true;
    		}
    		
    		Player p = (Player) sender;
    		
    		ItemStack handitem = p.getItemInHand();
    		
    		if (handitem == null)
    			return true;
    		
    		NamedItem item = new NamedItem(handitem);
    		
    		p.sendMessage(item.getStrippedName());
    		
    		return true;
    	}
    	
    	if (cmd.getName().equalsIgnoreCase("getlore")) {
    		
    		if (!(sender instanceof Player)) {
    			sender.sendMessage("Requires player.");
    			return true;
    		}
    		
    		Player p = (Player) sender;
    		
    		ItemStack handitem = p.getItemInHand();
    		
    		if (handitem == null)
    			return true;
    		
    		NamedItem item = new NamedItem(handitem);
    		
    		for (String s : item.getLore()) {
    			p.sendMessage(s);
    		}
    		
    		return true;
    	}
    	
    	if (cmd.getName().equalsIgnoreCase("lore")) {
    		
    		if (!(sender instanceof Player)) {
    			sender.sendMessage("Requires player.");
    			return true;
    		}
    		
    		Player p = (Player) sender;
    		
    		if (args.length != 1)
    			return true;
    		
    		ItemStack handitem = p.getItemInHand();
    		
    		if (handitem == null)
    			return true;
    		
    		NamedItem newitem = new NamedItem(handitem);
    		newitem.setLore(args[0]);
    		
    		p.setItemInHand(newitem.getItemStack());
    		
    		return true;
    		
    	}
    	
    	if (cmd.getName().equalsIgnoreCase("addlore")) {
    		
    		if (!(sender instanceof Player)) {
    			sender.sendMessage("Requires player.");
    			return true;
    		}
    		
    		Player p = (Player) sender;
    		
    		if (args.length != 1)
    			return true;
    		
    		ItemStack handitem = p.getItemInHand();
    		
    		if (handitem == null)
    			return true;
    		
    		NamedItem newitem = new NamedItem(handitem);
    		newitem.addLore(args[0]);
    		
    		p.setItemInHand(newitem.getItemStack());
    		
    		return true;
    		
    	}
    	
    	return false;
    	
    }
    
}
