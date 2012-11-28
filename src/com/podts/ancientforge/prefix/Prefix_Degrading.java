package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.*;

public class Prefix_Degrading extends ItemPrefix {
	
	public Prefix_Degrading() {
		super("Degrading", ChatColor.DARK_GRAY);
		getEffects().setDamgeincrease(-3);
		getEffects().setArmorincrease(-2);
	}

}
