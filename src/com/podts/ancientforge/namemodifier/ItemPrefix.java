package com.podts.ancientforge.namemodifier;

import java.util.HashMap;

import org.bukkit.ChatColor;

import com.podts.ancientforge.P;

public class ItemPrefix extends NameModifier {
	
	private static HashMap<String, ItemPrefix> prefixs = new HashMap<String, ItemPrefix>();
	
	public static HashMap<String, ItemPrefix> getPrefixs() {
		return prefixs;
	}
	
	public static ItemPrefix getRandomPrefix() {
		int result = P.getRandom().nextInt(prefixs.size());
		return (ItemPrefix) prefixs.values().toArray()[result];
	}
	
	public static ItemPrefix getPrefixByItemName(String name) {
		
		for (ItemPrefix prefix : prefixs.values()) {
			
			if (name.startsWith(prefix.getName()))
				return prefix;
			
		}
		
		return null;
		
	}
	
	private static void addPrefix(ItemPrefix prefix) {
		
		if ( prefixs.containsKey(prefix.getName()) )
			return;
		
		prefixs.put(prefix.getName(), prefix);
		
	}
	
	public ItemPrefix(String name) {
		super(name);
		addPrefix(this);
	}
	
	public ItemPrefix(String name, ChatColor color) {
		super(name, color);
		addPrefix(this);
	}
	
}
