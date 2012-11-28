package com.podts.ancientforge.namemodifier;

import org.bukkit.ChatColor;

import com.podts.ancientforge.effect.Effects;
import com.podts.ancientforge.prefix.*;
import com.podts.ancientforge.suffix.*;

public class NameModifier {
	
	public static void initModifiers() {
		
		new Prefix_Blessed();
		new Prefix_Barbarians();
		new Prefix_Lucky();
		new Prefix_Damaged();
		new Prefix_Enforced();
		new Prefix_Bloody();
		new Prefix_Archangels();
		new Prefix_Degrading();
		
		new Suffix_OfTheDeciples();
		new Suffix_OfTheReckless();
		new Suffix_OfTheFortunate();
		new Suffix_OfMalice();
		new Suffix_OfTheLeach();
		new Suffix_OfTheRabbit();
		new Suffix_OfTheFlames();
		new Suffix_OfKings();
		
	}
	
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
		if (hasColor())
			return color + name;
		else
			return name;
	}
	
	public boolean hasColor() {
		return color != null;
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
