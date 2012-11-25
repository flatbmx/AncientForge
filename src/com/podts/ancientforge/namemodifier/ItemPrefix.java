package com.podts.ancientforge.namemodifier;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;

public class ItemPrefix extends NameModifier {
	
	private static HashMap<String, ItemPrefix> prefixs = new HashMap<String, ItemPrefix>();
	
	public static HashMap<String, ItemPrefix> getPrefixs() {
		return prefixs;
	}
	
	public static ItemPrefix getRandomPrefix() {
		Random r = new Random();
		return (ItemPrefix) prefixs.values().toArray()[ r.nextInt( r.nextInt( prefixs.values().size() ) ) ];
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
