package com.podts.ancientforge.listeners;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.effect.WeaponCheck;
import com.podts.ancientforge.player.AFPlayer;

public class ItemHandler implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemDrop(PlayerDropItemEvent event) {
		
		Player p = event.getPlayer();
		AFPlayer afp = AFPlayer.getPlayer(p.getName());
		
		if (afp.getWeapon() != null) {
			afp.getEffects().deduct(afp.getWeapon().getEffects());
			afp.setWeapon(null);
			afp.updateWeaponEffects();
		}
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemPickUp(PlayerPickupItemEvent event) {
		
		Player p = event.getPlayer();
		CraftItemStack item = (CraftItemStack) event.getItem().getItemStack();
		
		if (NamedItem.isPluginItem(item)) {
			
			NamedItem ni = new NamedItem(item);
			
			if (ni.isMagical()) {
				
				MagicItem mi = new MagicItem(ni);
				
				if (mi.isWeapon()) {
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(P.getPluginInstance(), new WeaponCheck(p), 1L);
					
				}
				
			}
			
		}
		
	}
	
}
