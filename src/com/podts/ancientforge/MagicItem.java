package com.podts.ancientforge;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.effect.Effects;
import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;

public class MagicItem extends NamedItem {
	
	private Effects effects;
	
	private ItemPrefix prefix;
	private ItemSuffix suffix;
	
	public Effects getEffects() {
		return effects;
	}
	
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
		
		effects.addLore(this);
		
	}
	
	private void mergeEffects() {
		effects = new Effects();
		if (hasPrefix())
			effects.merge(prefix.getEffects());
		if (hasSuffix())
			effects.merge(suffix.getEffects());
	}
	
	public MagicItem(NamedItem item) {
		super(item.getItemStack());
		
	}
	
	public MagicItem(ItemStack item) {
		super(item);
	}
	
	public MagicItem(ItemStack item, String name) {
		super(item,name);
		constructName();
	}
	
	public MagicItem(ItemStack item, ItemPrefix prefix) {
		super(item);
		this.prefix = prefix;
		constructName();
	}
	
	public MagicItem(ItemStack item, ItemSuffix suffix) {
		super(item);
		this.suffix = suffix;
		mergeEffects();
		constructName();
		constructLore();
	}
	
	public MagicItem(ItemStack item, ItemPrefix prefix, ItemSuffix suffix) {
		super(item);
		this.prefix = prefix;
		this.suffix = suffix;
		mergeEffects();
		constructName();
		constructLore();
	}
	
}
