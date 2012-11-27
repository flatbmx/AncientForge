package com.podts.ancientforge.namemodifier;

import org.bukkit.ChatColor;

public class Prefix_Enforced extends ItemPrefix {

	public Prefix_Enforced() {
		super("Enforced", ChatColor.BLUE);
		getEffects().setArmormodifier(.05f);
	}

}
