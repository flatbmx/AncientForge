package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Bloody extends ItemPrefix {

	public Prefix_Bloody() {
		super("Bloody", ChatColor.DARK_RED);
		getEffects().setLifesteal(.1f);
	}

}
