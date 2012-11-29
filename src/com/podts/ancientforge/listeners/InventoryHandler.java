package com.podts.ancientforge.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.effect.CreativeArmorCheck;
import com.podts.ancientforge.player.AFPlayer;

public class InventoryHandler implements Listener {
	
	@EventHandler
	public void OnEnchantInsertion(PrepareItemEnchantEvent event) {
		
		if (NamedItem.isPluginItem((CraftItemStack) event.getItem())) {
			
			event.setCancelled(true);
			
		}
		
	}
	
	// TODO handle equipment changes.
	
	@EventHandler
	public void OnInventoryClickEvent(InventoryClickEvent event) {
		
		try {
		
		Player p = (Player) event.getWhoClicked();
		AFPlayer afp = AFPlayer.getPlayer(p.getName());
		
		switch (event.getInventory().getType()) {
		
		case ANVIL:
			if (event.getSlot() != 9)
				return;
			
			if (event.getCurrentItem().getType().equals(Material.AIR))
				return;
			
			if (NamedItem.isPluginItem((CraftItemStack) event.getCurrentItem())) {
				event.setCancelled(true);
				p.sendMessage(ChatColor.DARK_RED + "You cannot repair this item.");
			}
			break;
			
		case CRAFTING:
			if (event.getSlot() >= 36 && event.getSlot() <= 39) {
				if (event.getCursor().getType().equals(Material.AIR) && event.getCurrentItem().getType().equals(Material.AIR))
					break;
				CraftItemStack stack;
				
				if ( event.getCurrentItem().getType().equals(Material.AIR) && !event.getCursor().getType().equals(Material.AIR) ) {
					// Deposit
					stack = (CraftItemStack) event.getCursor();
					if (NamedItem.isPluginItem(stack)) {
						
						NamedItem ni = new NamedItem(stack);
						
						if (ni.isMagical()) {
							
							MagicItem mi = new MagicItem(ni);
							afp.getEffects().merge(mi.getEffects());
							
						}
						
					}
				}
				else {
					// Withdraw
					stack = (CraftItemStack) event.getCurrentItem();
					if (NamedItem.isPluginItem(stack)) {
						
						NamedItem ni = new NamedItem(stack);
						
						if (ni.isMagical()) {
							
							MagicItem mi = new MagicItem(ni);
							afp.getEffects().deduct(mi.getEffects());
						}
						
					}
				}
				afp.updateEffects();
			}
			else if (event.getSlot() == afp.getHandSlot()) {
				// Quick Bar
				if (afp.getWeapon() != null) {
					// Player is picking up weapon, deduct effects.
					afp.getEffects().deduct(afp.getWeapon().getEffects());
					afp.setWeapon(null);
					afp.updateWeaponEffects();
				}
				else {
					
					if (event.getCursor().getType().equals(Material.AIR)) {
						// Picking up item, its not a weapon so dont worry.
						break;
					}
					else {
						// Replacing handslot with something else.
						
						if (NamedItem.isPluginItem((CraftItemStack) event.getCursor())) {
							
							NamedItem ni = new NamedItem(event.getCursor());
							
							if (ni.isMagical()) {
								
								MagicItem mi = new MagicItem(ni);
								if (mi.isWeapon()) {
									afp.setWeapon(mi);
									afp.updateWeaponEffects();
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			break;
			
		case PLAYER:
			if (!(event.getSlot() >= 5 && event.getSlot() <= 8))
				break;
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(P.getPluginInstance(), new CreativeArmorCheck(p, event.getSlot(),event.getCurrentItem()),1L);
			break;
			
		default:
			break;
		
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
