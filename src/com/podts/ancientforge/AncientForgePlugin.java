package com.podts.ancientforge;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class AncientForgePlugin extends JavaPlugin {
	
	@Override
    public void onEnable(){
        
    }
 
    @Override
    public void onDisable() {
        
    }
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lavel, String[] args) {
    	
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
