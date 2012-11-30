package com.podts.ancientforge.effect;

import net.minecraft.server.Material;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.player.AFPlayer;

public class ArmorCheck implements Runnable {
	
	private CraftItemStack olditem;
	
	private AFPlayer afp;
	private Player player;
	private int slot;
	
	@Override
	public void run() {
		
		if (olditem.getType().equals(Material.AIR)) {
			
			//Deposit
			
			CraftItemStack stack;
			
			switch (slot) {
			case 5:
				stack = (CraftItemStack) player.getInventory().getHelmet();
				break;
			case 6:
				stack = (CraftItemStack) player.getInventory().getChestplate();
				break;
			case 7:
				stack = (CraftItemStack) player.getInventory().getLeggings();
				break;
			case 8:
				stack = (CraftItemStack) player.getInventory().getBoots();
				break;
			default:
				return;
			}
			
			if (NamedItem.isPluginItem(stack)) {
				
				NamedItem ni = new NamedItem(stack);
				
				if (ni.isMagical()) {
					
					MagicItem mi = new MagicItem(ni);
					afp.getEffects().merge(mi.getEffects());
					afp.updateEffects();
					afp.getEquipment().put(slot, mi);
					
				}
				
			}
			
		}
		else {
			
			// Withdraw or replace.
			
			CraftItemStack currentitem;
			
			switch (slot) {
			case 5:
			case 39:
				currentitem = (CraftItemStack) player.getInventory().getHelmet();
				break;
			case 6:
			case 38:
				currentitem = (CraftItemStack) player.getInventory().getChestplate();
				break;
			case 7:
			case 37:
				currentitem = (CraftItemStack) player.getInventory().getLeggings();
				break;
			case 8:
			case 36:
				currentitem = (CraftItemStack) player.getInventory().getBoots();
				break;
			default:
				return;
			}
			
			if (currentitem == null) {
				
				//Withdraw
				
				if (NamedItem.isPluginItem(olditem)) {
					
					NamedItem ni = new NamedItem(olditem);
					
					if (ni.isMagical()) {
						MagicItem mi = new MagicItem(ni);
						afp.getEffects().deduct(mi.getEffects());
						afp.updateEffects();
					}
					
				}
				
			}
			else if (currentitem.getHandle() == olditem.getHandle()) {
				
				// Fake
				
			}
			else {
				
				if (NamedItem.isPluginItem(olditem)) {
					
					NamedItem ni = new NamedItem(olditem);
					
					if (ni.isMagical()) {
						MagicItem mi = new MagicItem(ni);
						afp.getEffects().deduct(mi.getEffects());
						afp.updateEffects();
					}
					
				}
				
				if (NamedItem.isPluginItem(currentitem)) {
					
					NamedItem ni = new NamedItem(currentitem);
					
					if (ni.isMagical()) {
						MagicItem mi = new MagicItem(ni);
						afp.getEffects().merge(mi.getEffects());
						afp.updateEffects();
					}
					
				}
				
			}
			
		}
		
	}
	
	public ArmorCheck(Player p,int slot, ItemStack olditem) {
		this.player = p;
		this.afp = AFPlayer.getPlayer(p.getName());
		this.slot = slot;
		this.olditem = (CraftItemStack) olditem;
	}
	
}
