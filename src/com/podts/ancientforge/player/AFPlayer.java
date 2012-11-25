package com.podts.ancientforge.player;

import org.bukkit.entity.Player;

import com.podts.ancientforge.effect.Effects;

public class AFPlayer {
	
	private Player bukkitplayer;
	private Effects effects;
	
	public Player getBukkitPlayer() {
		return bukkitplayer;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public AFPlayer(Player p) {
		this.bukkitplayer = p;
		this.effects = new Effects();
	}
	
}
