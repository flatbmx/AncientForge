package com.podts.ancientforge.listeners;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.player.AFPlayer;

public class HandHandler implements Listener {
	
	@EventHandler
	public void onHeldEvent(PlayerItemHeldEvent event) {
		
		try {
		
		Player bukkitplayer = event.getPlayer();
		AFPlayer afp = AFPlayer.getPlayer(bukkitplayer.getName());
		int newslot = event.getNewSlot();
		int oldslot = event.getPreviousSlot();
		
		if ( NamedItem.isPluginItem((CraftItemStack) bukkitplayer.getInventory().getContents()[oldslot]) ) {
			
			// Take Away item effects from the player.
			NamedItem olditem = afp.getItem(oldslot);
			if (olditem instanceof MagicItem) {
				
				MagicItem magicitem = (MagicItem) olditem;
				
				P.getPluginLogger().info("deducted.");
				afp.getEffects().deduct(magicitem.getEffects());
				
			}
			
		}
		
		if (NamedItem.isPluginItem((CraftItemStack) bukkitplayer.getInventory().getContents()[newslot])) {
			
			// Add new items effects to player.
			NamedItem newitem = afp.getItem(newslot);
			if (newitem instanceof MagicItem) {
				
				MagicItem magicitem = (MagicItem) newitem;
				
				afp.getEffects().merge(magicitem.getEffects());
				
			}
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
