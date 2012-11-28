package com.podts.ancientforge.effect;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AFPotionEffect extends PotionEffect {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public AFPotionEffect(PotionEffectType type, int amplifier) {
		super(type, 9999999, amplifier);
		
		if (type.equals(PotionEffectType.SPEED))
			name = "walkspeed";
		
	}

}
