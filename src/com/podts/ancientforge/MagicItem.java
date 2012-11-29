package com.podts.ancientforge;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.effect.Effects;
import com.podts.ancientforge.namemodifier.ItemPrefix;
import com.podts.ancientforge.namemodifier.ItemSuffix;

public class MagicItem extends NamedItem {
	
	public static MagicItem getRandomeMagicItem() {
		
		Material[] itemids = {Material.DIAMOND_SWORD,Material.DIAMOND_AXE,
				Material.DIAMOND_HELMET,Material.DIAMOND_CHESTPLATE,Material.DIAMOND_LEGGINGS,Material.DIAMOND_BOOTS,
				Material.IRON_SWORD,Material.IRON_AXE,Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,
				Material.IRON_BOOTS,Material.BOW};
		
		CraftItemStack stack = new CraftItemStack( itemids[P.getRandom().nextInt(itemids.length)] );
		
		int result = P.getRandom().nextInt(100);
		
		if (result < 40)
			return new MagicItem(stack,ItemPrefix.getRandomPrefix());
		else if (result < 80)
			return new MagicItem(stack,ItemSuffix.getRandomPrefix());
		else
			return new MagicItem(stack,ItemPrefix.getRandomPrefix(),ItemSuffix.getRandomPrefix());
		
	}
	
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
	
	public boolean isWeapon() {
		
		String name = getName().toLowerCase();
		
		if ((name.contains("sword") || name.contains("axe") || name.contains("bow") || name.contains("pickaxe") || name.contains("shovel"))) {
			return true;
		}
		
		return false;
		
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
		update();
		constructModifiers();
		if (hasPrefix()) {
			effects.merge(prefix.getEffects());
		}
		if (hasSuffix()) {
			effects.merge(suffix.getEffects());
		}
	}
	
	private void constructModifiers() {
		
		if (containsPrefix()) {
			
			prefix = ItemPrefix.getPrefixByItemName(getName());
			
		}
		
		if (containsSuffix()) {
			
			suffix = ItemSuffix.getSuffixByItemName(getName());
			
		}
		
	}
	
	public MagicItem(NamedItem item) {
		super(item.getItemStack());
		mergeEffects();
	}
	
	public MagicItem(ItemStack item) {
		super(item);
		mergeEffects();
	}
	
	public MagicItem(ItemStack item, String name) {
		super(item,name);
		mergeEffects();
		constructName();
	}
	
	public MagicItem(ItemStack item, ItemPrefix prefix) {
		super(item);
		this.prefix = prefix;
		mergeEffects();
		constructName();
		constructLore();
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
