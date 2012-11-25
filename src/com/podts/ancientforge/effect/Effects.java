package com.podts.ancientforge.effect;

import org.bukkit.ChatColor;

import com.podts.ancientforge.NamedItem;

public class Effects {
	
	private int damgeincrease;
	private float damagemodifier;
	private int armorincrease;
	private float armormodifier;
	private float findmagicitems;

	public int getDamgeincrease() {
		return damgeincrease;
	}

	public void setDamgeincrease(int damgeincrease) {
		this.damgeincrease = damgeincrease;
	}
	
	public void bumpDamageIncrease(int damageincrease) {
		this.damgeincrease += damageincrease;
	}
	
	public float getDamagemodifier() {
		return damagemodifier;
	}

	public void setDamagemodifier(float damagemodifier) {
		this.damagemodifier = damagemodifier;
	}
	
	public void bumpDamagmodifier(float damagemodifier) {
		this.damagemodifier += damagemodifier;
	}
	
	public int getArmorincrease() {
		return armorincrease;
	}

	public void setArmorincrease(int armorincrease) {
		this.armorincrease = armorincrease;
	}
	
	public void bumpArmorincrease(int armorincrease) {
		this.armorincrease += armorincrease;
	}
	
	public float getArmormodifier() {
		return armormodifier;
	}

	public void setArmormodifier(float armormodifier) {
		this.armormodifier = armormodifier;
	}
	
	public void bumpArmormodifier(float armormodifier) {
		this.armormodifier += armormodifier;
	}
	
	public float getFindmagicitems() {
		return findmagicitems;
	}

	public void setFindmagicitems(float findmagicitems) {
		this.findmagicitems = findmagicitems;
	}
	
	public void bumpFindmagicitems(float findmagicitems) {
		this.findmagicitems += findmagicitems;
	}
	
	public void addLore(NamedItem item) {
		
		if (damgeincrease != 0)
			item.addLore(ChatColor.RED + " Damage Increase: " + damgeincrease);
		if (damagemodifier != 0)
			item.addLore(ChatColor.RED + " Damage Increase: " + damagemodifier*100 + "%");
		if (armorincrease !=0)
			item.addLore(ChatColor.BLUE + " Armor Increase: " + armorincrease);
		if (armormodifier != 0)
			item.addLore(ChatColor.BLUE + " Armor Increase: " + armormodifier*100 + "%");
		
	}
	
}
