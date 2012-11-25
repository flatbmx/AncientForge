package com.podts.ancientforge.suffix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemSuffix;

public class Suffix_Test extends ItemSuffix {

	public Suffix_Test() {
		super("Test", ChatColor.AQUA);
		getEffects().setDamgeincrease(3);
	}

}
