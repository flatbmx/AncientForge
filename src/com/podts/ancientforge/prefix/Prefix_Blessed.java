package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Blessed extends ItemPrefix {

	public Prefix_Blessed() {
		super("Blessed", ChatColor.BLUE);
		getEffects().setArmorincrease(1);
		getEffects().setDamgeincrease(1);
	}

}
