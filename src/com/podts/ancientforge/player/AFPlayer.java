package com.podts.ancientforge.player;

import java.util.HashMap;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.effect.AFPotionEffect;
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
	private MagicItem weapon;
	private HashMap<Integer,NamedItem> equipment;
	private HashMap<String, AFPotionEffect> potioneffects;
	
	public HashMap<Integer,NamedItem> getEquipment() {
		return equipment;
	}
	
	public Player getBukkitPlayer() {
		return bukkitplayer;
	}
	
	public Effects getEffects() {
		return effects;
	}
	
	public MagicItem getWeaopn() {
		return weapon;
	}
	
	public void setWeapon(MagicItem item) {
		this.weapon = item;
	}
	
	public void resetEffects() {
		effects = new Effects();
		removePotionEffects();
	}
	
	public void updateEffects() {
		
		P.getPluginLogger().info("updating effects.");
		
		CraftItemStack hand = (CraftItemStack) getBukkitPlayer().getItemInHand();
		
		if (hand != null) {
			
			if (NamedItem.isPluginItem(hand)) {
				
				NamedItem ni = new NamedItem(hand);
				
				String name = ni.getName().toLowerCase();
				
				if (!(name.contains("sword") || name.contains("axe") || name.contains("bow") || name.contains("pickaxe") || name.contains("shovel")))
					return;
				
				if (ni.containsPrefix() || ni.containsSuffix()) {
					
					MagicItem magicitem = new MagicItem(ni);
					weapon = magicitem;
					effects.merge(magicitem.getEffects());
					
				}
				
			}
			
		}
		
		removePotionEffects();
		
		if (getEffects().getWalkspeed() > 0) {
			AFPotionEffect e = new AFPotionEffect(PotionEffectType.SPEED, (int) getEffects().getWalkspeed());
			getBukkitPlayer().addPotionEffect(e);
			potioneffects.put(e.getName(), e);
		}
		
	}
	
	public void removePotionEffects() {
		
		for (AFPotionEffect e : potioneffects.values()) {
			
			if (e instanceof AFPotionEffect) {
				getBukkitPlayer().removePotionEffect(e.getType());
			}
			
		}
		
	}
	
	public AFPlayer(Player p) {
		
		this.bukkitplayer = p;
		this.effects = new Effects();
		this.equipment = new HashMap<Integer,NamedItem>();
		this.potioneffects = new HashMap<String, AFPotionEffect>();
		players.put(this.getBukkitPlayer().getName(), this);
		
		for (int i=0; i < getBukkitPlayer().getInventory().getArmorContents().length; i++) {
			
			ItemStack item = getBukkitPlayer().getInventory().getArmorContents()[i];
			
			if (item == null)
				return;
			
			if (item instanceof CraftItemStack) {
				
				if (NamedItem.isPluginItem((CraftItemStack) item)) {
					
					NamedItem nameditem = new NamedItem(item);
					
					if (nameditem.containsPrefix() || nameditem.containsSuffix()) {
						
						MagicItem magicitem = new MagicItem(item);
						magicitem.setSlot(i);
						equipment.put(i, magicitem);
						effects.merge(magicitem.getEffects());
						
					}
					else {
						nameditem.setSlot(i);
						equipment.put(i,nameditem);
					}
					
				}
				
			}
			
		}
		
		updateEffects();
		
	}
	
}
