package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Barbarians extends ItemPrefix {

	public Prefix_Barbarians() {
		super("Barbarians",ChatColor.DARK_GRAY);
		getEffects().setDamagemodifier(.150f);
	}

}
