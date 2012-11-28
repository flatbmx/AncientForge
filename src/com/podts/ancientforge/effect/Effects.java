package com.podts.ancientforge.effect;

import org.bukkit.ChatColor;

import com.podts.ancientforge.NamedItem;

public class Effects {
	
	private float lifesteal;
	private float lifestealleft;
	private float damageleft;
	private int damgeincrease;
	private float damagemodifier;
	private int armorincrease;
	private float armorleft;
	private float armormodifier;
	private float findmagicitems;
	
	private float walkspeed;
	
	public float getWalkspeed() {
		return walkspeed;
	}
	
	public void bumpWalkSpeed(float walkspeed) {
		this.walkspeed += walkspeed;
	}
	
	public void setWalkspeed(float walkspeed) {
		this.walkspeed = walkspeed;
	}
	
	public float getLifesteal() {
		return lifesteal;
	}

	public void setLifesteal(float lifesteal) {
		this.lifesteal = lifesteal;
	}
	
	public void bumpLifesteal(float lifesteal) {
		this.lifesteal += lifesteal;
	}
	
	public float getLifestealleft() {
		return lifestealleft;
	}

	public void setLifestealleft(float lifestealleft) {
		this.lifestealleft = lifestealleft;
	}
	
	public void bumpLifestealleft(float lifestealleft) {
		this.lifestealleft += lifestealleft;
	}
	
	public float getDamageleft() {
		return damageleft;
	}

	public void setDamageleft(float damageleft) {
		this.damageleft = damageleft;
	}
	
	public void bumpDamageleft(float damageleft) {
		this.damageleft += damageleft;
	}
	
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
	
	public float getArmorleft() {
		return armorleft;
	}

	public void setArmorleft(float armorleft) {
		this.armorleft = armorleft;
	}
	
	public void bumpArmorleft(float armorleft) {
		this.armorleft += armorleft;
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
		
		item.clearLore();
		
		if (lifesteal != 0)
			item.addLore(ChatColor.DARK_RED + "Life Steal + : " + lifesteal*100 + "%");
		if (damgeincrease != 0)
			item.addLore(ChatColor.RED + "Damage + : " + damgeincrease);
		if (damagemodifier != 0)
			item.addLore(ChatColor.RED + "Damage + : " + damagemodifier*100 + "%");
		if (armorincrease !=0)
			item.addLore(ChatColor.BLUE + "Armor + : " + armorincrease);
		if (armormodifier != 0)
			item.addLore(ChatColor.BLUE + "Armor + : " + armormodifier*100 + "%");
		if (findmagicitems != 0)
			item.addLore(ChatColor.GOLD + "Magic Drop Rate + : " + findmagicitems*100 + "%");
		
	}
	
	public void merge(Effects other) {
		
		bumpLifesteal(other.getLifesteal());
		bumpDamageIncrease(other.getDamgeincrease());
		bumpDamagmodifier(other.getDamagemodifier());
		bumpArmorincrease(other.getArmorincrease());
		bumpArmormodifier(other.getArmormodifier());
		bumpFindmagicitems(other.getFindmagicitems());
		
		bumpWalkSpeed(other.getWalkspeed());
		
	}
	
	public void deduct(Effects other) {
		
		bumpLifesteal(other.getLifesteal()*-1);
		bumpDamageIncrease(other.getDamgeincrease()*-1);
		bumpDamagmodifier(other.getDamagemodifier()*-1);
		bumpArmorincrease(other.getArmorincrease()*-1);
		bumpArmormodifier(other.getArmormodifier()*-1);
		bumpFindmagicitems(other.getFindmagicitems()*-1);
		
		bumpWalkSpeed(other.getWalkspeed()*-1);
		
	}
	
}
