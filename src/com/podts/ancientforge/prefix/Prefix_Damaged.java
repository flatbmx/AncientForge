package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Damaged extends ItemPrefix {

	public Prefix_Damaged() {
		super("Damaged", ChatColor.DARK_GRAY);
		getEffects().setArmormodifier(-.01f);
		getEffects().setDamagemodifier(-.01f);
	}

}
