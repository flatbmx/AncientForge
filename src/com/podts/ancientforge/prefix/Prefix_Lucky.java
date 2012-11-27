package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Lucky extends ItemPrefix {

	public Prefix_Lucky() {
		super("Lucky", ChatColor.GOLD);
		getEffects().setFindmagicitems(.02f);
	}

}
