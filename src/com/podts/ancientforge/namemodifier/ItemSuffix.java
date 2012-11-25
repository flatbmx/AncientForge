package com.podts.ancientforge.namemodifier;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;

public class ItemSuffix extends NameModifier{
	
private static HashMap<String, ItemSuffix> suffixs = new HashMap<String, ItemSuffix>();
	
	public static HashMap<String, ItemSuffix> getSuffixs() {
		return suffixs;
	}
	
	public static ItemSuffix getRandomPrefix() {
		Random r = new Random();
		return (ItemSuffix) suffixs.values().toArray()[ r.nextInt( r.nextInt( suffixs.values().size() ) ) ];
	}
	
	private static void addSuffix(ItemSuffix suffix) {
		
		if ( suffixs.containsKey(suffix.getName()) )
			return;
		
		suffixs.put(suffix.getName(), suffix);
		
	}
	
	public ItemSuffix(String name) {
		super(name);
		addSuffix(this);
	}
	
	public ItemSuffix(String name, ChatColor color) {
		super(name, color);
		addSuffix(this);
	}
	
}
