package com.podts.ancientforge.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
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
			if (!(event.getSlot() >= 36 && event.getSlot() <= 39))
				break;
			if (event.getCursor().equals(Material.AIR) && event.getCurrentItem().equals(Material.AIR))
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
			p.sendMessage(event.getCurrentItem().getType().name());
			p.sendMessage(event.getCursor().getType().name());
			afp.updateEffects();
			break;
			
		case PLAYER:
			break;
			
		default:
			break;
		
		}
		
	}
	
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
