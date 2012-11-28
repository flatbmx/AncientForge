package com.podts.ancientforge.suffix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemSuffix;

public class Suffix_OfTheLeach extends ItemSuffix {

	public Suffix_OfTheLeach() {
		super("Of The Leach", ChatColor.DARK_RED);
		getEffects().setLifesteal(.1f);
	}

}
