package com.podts.moreitems.player;

import org.bukkit.entity.Player;

import com.podts.ancientforge.effect.Effects;

public class MIPlayer {
	
	private final Player player;
	private Effects effects;
	
	public Player getPlayer() {
		return player;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public void handleLogout() {
		
	}
	
	public void addEffects(Effects other) {
		
		effects.bumpDamageIncrease(other.getDamgeincrease());
		effects.bumpDamagmodifier(other.getDamagemodifier());
		effects.bumpArmorincrease(other.getArmorincrease());
		effects.bumpArmormodifier(other.getArmormodifier());
		effects.bumpFindmagicitems(other.getFindmagicitems());
		
	}
	
	public MIPlayer(Player p) {
		this.player = p;
	}
	
}
