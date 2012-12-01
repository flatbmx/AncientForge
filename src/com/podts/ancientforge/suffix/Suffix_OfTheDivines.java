package com.podts.ancientforge.suffix;

//Rare

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemSuffix;

public class Suffix_OfTheDivines extends ItemSuffix {

	public Suffix_OfTheDivines() {
		super("Of The Divines", ChatColor.GOLD);
		getEffects().setDamgeincrease(3);
		getEffects().setLifesteal(.2f);
	}

}
