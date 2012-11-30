package com.podts.ancientforge.listeners;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.player.AFPlayer;

public class HandHandler implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onHeldEvent(PlayerItemHeldEvent event) {
		
		Player bukkitplayer = event.getPlayer();
		AFPlayer afp = AFPlayer.getPlayer(bukkitplayer.getName());
		int newslot = event.getNewSlot();
		afp.setHandSlot(newslot);
		
		if (afp.getWeapon() != null) {
			afp.getEffects().deduct(afp.getWeapon().getEffects());
			afp.setWeapon(null);
		}
		
		if (NamedItem.isPluginItem((CraftItemStack) bukkitplayer.getInventory().getContents()[newslot])) {
			
			// Add new items effects to player.
			NamedItem newitem = new NamedItem((CraftItemStack) bukkitplayer.getInventory().getContents()[newslot]);
			
			if (newitem.isMagical()) {
				
				MagicItem magicitem = new MagicItem(newitem);
				
				if (magicitem.isWeapon())
					afp.setWeapon(magicitem);
				else
					afp.setWeapon(null);
				
			}
			
		}
		
		afp.updateWeaponEffects();
		
	}
	
}
