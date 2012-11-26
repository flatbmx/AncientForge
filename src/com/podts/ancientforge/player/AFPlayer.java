package com.podts.ancientforge.player;

import java.util.HashMap;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.effect.Effects;

public class AFPlayer {
	
	private static HashMap<String,AFPlayer> players = new HashMap<String,AFPlayer>();
	
	public static HashMap<String,AFPlayer> getPlayers() {
		return players;
	}
	
	public static AFPlayer getPlayer(String name) {
		return players.get(name);
	}
	
	private Player bukkitplayer;
	private Effects effects;
	private HashMap<Integer,NamedItem> items;
	private HashMap<Integer,NamedItem> equipment;
	
	public HashMap<Integer,NamedItem> getEquipment() {
		return equipment;
	}
	
	public Player getBukkitPlayer() {
		return bukkitplayer;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public HashMap<Integer,NamedItem> getItems() {
		return items;
	}
	
	public AFPlayer(Player p) {
		
		this.bukkitplayer = p;
		this.items = new HashMap<Integer,NamedItem>();
		this.effects = new Effects();
		this.equipment = new HashMap<Integer,NamedItem>();
		players.put(this.getBukkitPlayer().getName(), this);
		
		for (int i=0; i < getBukkitPlayer().getInventory().getContents().length; i++) {
			
			ItemStack item = getBukkitPlayer().getInventory().getContents()[i];
			
			if (item == null)
				continue;
			
			if (item instanceof CraftItemStack) {
				
				if (NamedItem.isPluginItem((CraftItemStack) item)) {
					
					NamedItem nameditem = new NamedItem(item);
					
					if (nameditem.hasPrefix() || nameditem.hasSuffix()) {
						
						MagicItem magicitem = new MagicItem(item);
						magicitem.setSlot(i);
						items.put(i, magicitem);
						
					}
					else {
						nameditem.setSlot(i);
						items.put(i, nameditem);
					}
					
				}
				
			}
			
		}
		
		for (int i=0; i < getBukkitPlayer().getInventory().getArmorContents().length; i++) {
			
			ItemStack item = getBukkitPlayer().getInventory().getArmorContents()[i];
			
			if (item == null)
				return;
			
			if (item instanceof CraftItemStack) {
				
				if (NamedItem.isPluginItem((CraftItemStack) item)) {
					
					NamedItem nameditem = new NamedItem(item);
					
					if (nameditem.hasPrefix() || nameditem.hasSuffix()) {
						
						MagicItem magicitem = new MagicItem(item);
						magicitem.setSlot(i);
						equipment.put(i, magicitem);
						
					}
					else {
						nameditem.setSlot(i);
						equipment.put(i,nameditem);
					}
					
				}
				
			}
			
		}
		
	}
	
}
