package com.podts.ancientforge.suffix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemSuffix;

public class Suffix_OfTheReckless extends ItemSuffix {

	public Suffix_OfTheReckless() {
		super("Of The Reckless", ChatColor.RED);
		getEffects().setDamagemodifier(.1f);
	}

}
