package com.podts.ancientforge;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;

public class MagicItem extends NamedItem {

	private ItemPrefix prefix;
	private ItemSuffix suffix;
	
	public boolean hasPrefix() {
		return prefix != null;
	}
	
	public ItemPrefix getPrefix() {
		return prefix;
	}
	
	public boolean hasSuffix() {
		return suffix != null;
	}
	
	public ItemSuffix getSuffix() {
		return suffix;
	}
	
	public void update() {
		constructName();
		constructLore();
	}
	
	private void constructName() {
		String result = "";
		result = WordUtils.capitalizeFully(getItemStack().getType().name().replaceAll("_", " "));
		if (prefix != null)
			result = prefix.getName() + ChatColor.RESET + " " + result;
		if (suffix != null)
			result = result + ChatColor.RESET + " " + suffix.getName();
		setName(result);
	}
	
	private void constructLore() {
		
		if (hasPrefix()) {
			prefix.getEffects().addLore(this);
		}
		if (hasSuffix()) {
			suffix.getEffects().addLore(this);
		}
		
	}
	
	public MagicItem(ItemStack item) {
		super(item);
	}
	
	public MagicItem(ItemStack item, String name) {
		super(item,name);
	}
	
	public MagicItem(ItemStack item, ItemPrefix prefix) {
		super(item);
		this.prefix = prefix;
		constructName();
	}
	
	public MagicItem(ItemStack item, ItemSuffix suffix) {
		super(item);
		this.suffix = suffix;
		constructName();
		constructLore();
	}
	
	public MagicItem(ItemStack item, ItemPrefix prefix, ItemSuffix suffix) {
		super(item);
		this.prefix = prefix;
		this.suffix = suffix;
		constructName();
		constructLore();
		
	}
	
}
