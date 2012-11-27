package com.podts.ancientforge.namemodifier;

import java.util.HashMap;

import org.bukkit.ChatColor;

import com.podts.ancientforge.P;

public class ItemSuffix extends NameModifier{
	
private static HashMap<String, ItemSuffix> suffixs = new HashMap<String, ItemSuffix>();
	
	public static HashMap<String, ItemSuffix> getSuffixs() {
		return suffixs;
	}
	
	public static ItemSuffix getRandomPrefix() {
		int result = P.getRandom().nextInt(suffixs.size());
		if (result < 0)
			result = 0;
		return (ItemSuffix) suffixs.values().toArray()[result];
	}
	
	public static ItemSuffix getSuffixByItemName(String name) {
		
		for (ItemSuffix suffix : suffixs.values()) {
			
			if (name.endsWith(suffix.getName()))
				return suffix;
			
		}
		
		return null;
		
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
