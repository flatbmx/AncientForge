package com.podts.ancientforge.listeners;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.player.AFPlayer;

public class InventoryHandler implements Listener {
	
	@EventHandler
	public void OnInventoryCloseEvent(InventoryCloseEvent event) {
		
		if (event.getPlayer() == null)
			return;
		if (!(event.getPlayer() instanceof Player))
			return;
		
		Player player = (Player) event.getPlayer();
		AFPlayer afp = AFPlayer.getPlayer(player.getName());
		
		for (NamedItem item : afp.getEquipment().values()) {
			
			if (item instanceof MagicItem) {
				
				MagicItem mageitem = (MagicItem) item;
				afp.getEffects().deduct(mageitem.getEffects());
				
			}
			
		}
		
		afp.getEquipment().clear();
		
		for (int i=0; i < player.getInventory().getArmorContents().length; i++) {
			
			ItemStack item = player.getInventory().getArmorContents()[i];
			
			if (item == null)
				return;
			
			if (item instanceof CraftItemStack) {
				
				if (NamedItem.isPluginItem((CraftItemStack) item)) {
					
					NamedItem nameditem = new NamedItem(item);
					
					if (nameditem.containsPrefix() || nameditem.containsSuffix()) {
						
						MagicItem magicitem = new MagicItem(item);
						magicitem.setSlot(i);
						afp.getEquipment().put(i, magicitem);
						afp.getEffects().merge(magicitem.getEffects());
						
					}
					else {
						nameditem.setSlot(i);
						afp.getEquipment().put(i,nameditem);
					}
					
				}
				
			}
			
		}
		
	}
	
}
