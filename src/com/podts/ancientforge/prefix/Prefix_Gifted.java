package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Gifted extends ItemPrefix {

	public Prefix_Gifted() {
		super("Gifted", ChatColor.BLUE);
		getEffects().setArmorincrease(2);
		getEffects().setWalkspeed(1);
	}
	
	

}
