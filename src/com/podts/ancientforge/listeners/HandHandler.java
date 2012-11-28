package com.podts.ancientforge.listeners;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.player.AFPlayer;

public class HandHandler implements Listener {
	
	@EventHandler
	public void onHeldEvent(PlayerItemHeldEvent event) {
		
		Player bukkitplayer = event.getPlayer();
		AFPlayer afp = AFPlayer.getPlayer(bukkitplayer.getName());
		int newslot = event.getNewSlot();
		
		boolean updateeffects = false;
		
		if (afp.getWeaopn() != null) {
			afp.getEffects().deduct(afp.getWeaopn().getEffects());
			afp.setWeapon(null);
			updateeffects = true;
		}
		
		if (NamedItem.isPluginItem((CraftItemStack) bukkitplayer.getInventory().getContents()[newslot])) {
			
			ItemStack i = bukkitplayer.getInventory().getContents()[newslot];
			
			// Add new items effects to player.
			NamedItem newitem = new NamedItem((CraftItemStack) bukkitplayer.getInventory().getContents()[newslot]);
			
			if (newitem.isMagical()) {
				
				MagicItem magicitem = new MagicItem(newitem);
				
				magicitem.update();
				
				String name = i.getType().name().toLowerCase();
				
				if (!(name.contains("sword") || name.contains("axe") || name.contains("bow") || name.contains("pickaxe") || name.contains("shovel")))
					return;
				
				afp.setWeapon(magicitem);
				afp.getEffects().merge(afp.getWeaopn().getEffects());
				updateeffects = true;
				
			}
			
		}
		
		if (updateeffects)
			afp.updateEffects();
		
	}
	
}
