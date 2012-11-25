package com.podts.ancientforge.player;

import java.util.LinkedList;

import org.bukkit.entity.Player;

import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.effect.Effects;

public class AFPlayer {
	
	private Player bukkitplayer;
	private Effects effects;
	private LinkedList<NamedItem> items;
	
	public Player getBukkitPlayer() {
		return bukkitplayer;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public LinkedList<NamedItem> getItems() {
		return items;
	}
	
	public AFPlayer(Player p) {
		this.bukkitplayer = p;
		this.items = new LinkedList<NamedItem>();
		this.effects = new Effects();
	}
	
}
