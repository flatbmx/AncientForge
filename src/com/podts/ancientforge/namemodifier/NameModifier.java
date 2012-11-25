package com.podts.ancientforge.namemodifier;

import org.bukkit.ChatColor;

import com.podts.ancientforge.effect.Effects;

public class NameModifier {
	
	private String name;
	private ChatColor color;
	private Effects effects;
	
	/**
	 * Gets the name of the modifier without color codes.
	 * @return the name.
	 */
	public String getStrippedName() {
		return name;
	}
	
	/**
	 * Gets the name of the modifier with color codes.
	 * @return the name.
	 */
	public String getName() {
		return color + name;
	}
	
	/**
	 * Gets the color of the modifier.
	 * @return the color.
	 */
	public ChatColor getColor() {
		return color;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public NameModifier(String name) {
		this.name = name;
		this.effects = new Effects();
	}
	
	public NameModifier(String name, ChatColor color) {
		this.name = name;
		this.color = color;
		this.effects = new Effects();
	}
	
}
