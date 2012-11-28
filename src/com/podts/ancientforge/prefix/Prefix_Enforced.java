package com.podts.ancientforge.prefix;

import org.bukkit.ChatColor;

import com.podts.ancientforge.namemodifier.ItemPrefix;

public class Prefix_Enforced extends ItemPrefix {

	public Prefix_Enforced() {
		super("Enforced", ChatColor.BLUE);
		getEffects().setArmormodifier(.05f);
	}

}
