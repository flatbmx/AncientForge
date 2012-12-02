package com.podts.ancientforge.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.effect.ArmorCheck;
import com.podts.ancientforge.effect.WeaponCheck;
import com.podts.ancientforge.player.AFPlayer;

public class InventoryHandler implements Listener {
	
	@EventHandler
	public void OnEnchantInsertion(PrepareItemEnchantEvent event) {
		
		if (NamedItem.isPluginItem((CraftItemStack) event.getItem())) {
			
			event.setCancelled(true);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void OnInventoryClickEvent(InventoryClickEvent event) {
		
		try {
		
		Player p = (Player) event.getWhoClicked();
		AFPlayer afp = AFPlayer.getPlayer(p.getName());
		
		CraftItemStack currentitem = (CraftItemStack) event.getCurrentItem();
		
		if (afp.getWeapon() != null) {
			
			if (afp.getWeapon().getItemStack().equals(currentitem)) {
				
				afp.getEffects().deduct(afp.getWeapon().getEffects());
				afp.setWeapon(null);
				afp.updateWeaponEffects();
				Bukkit.getScheduler().scheduleSyncDelayedTask(P.getPluginInstance(), new WeaponCheck(p), 1L);
				
			}
			
		}
		else
			Bukkit.getScheduler().scheduleSyncDelayedTask(P.getPluginInstance(), new WeaponCheck(p), 1L);
		
		boolean isarmorslot = false;
		
		switch (event.getInventory().getType()) {
		case CRAFTING:
			if (event.getSlot() >= 36 && event.getSlot() <= 39) {
				isarmorslot = true;
			}
			break;
		case PLAYER:
			if (event.getSlot() >= 5 && event.getSlot() <= 8) {
				isarmorslot = true;
			}
				
		default:
			break;
		}
		
		if (isarmorslot) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(P.getPluginInstance(), new ArmorCheck(p, event.getSlot(), currentitem), 1L);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@EventHandler
	public void onAnvilClick(InventoryClickEvent event) {
		
		if ( event.getInventory().getType().equals(InventoryType.ANVIL) ) {
			
			Player p = (Player) event.getWhoClicked();
			
			if (event.getSlot() != 9)
				return;
			
			if (event.getCurrentItem().getType().equals(Material.AIR))
				return;

			if (NamedItem.isPluginItem((CraftItemStack) event.getCurrentItem())) {
				event.setCancelled(true);
				p.sendMessage(ChatColor.DARK_RED + "You cannot repair this item.");
			}
			
		}
		
	}
	
}
