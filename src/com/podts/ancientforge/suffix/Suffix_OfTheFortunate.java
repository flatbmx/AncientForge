package com.podts.ancientforge.suffix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemSuffix;

public class Suffix_OfTheFortunate extends ItemSuffix {

	public Suffix_OfTheFortunate() {
		super("Of The Fortunate", ChatColor.GOLD);
		getEffects().setFindmagicitems(.03f);
	}

}
