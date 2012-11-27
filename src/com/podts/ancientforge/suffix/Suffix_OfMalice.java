package com.podts.ancientforge.suffix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemSuffix;

public class Suffix_OfMalice extends ItemSuffix {

	public Suffix_OfMalice() {
		super("Of Malice", ChatColor.LIGHT_PURPLE);
		getEffects().setArmorincrease(-2);
		getEffects().setDamagemodifier(.2f);
	}

}
