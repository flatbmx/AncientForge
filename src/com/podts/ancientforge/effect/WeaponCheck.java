package com.podts.ancientforge.effect;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.NamedItem;
import com.podts.ancientforge.player.AFPlayer;

public class WeaponCheck implements Runnable {
	
	private AFPlayer afp;
	private Player player;
	
	@Override
	public void run() {
		
		if (afp.getWeapon() != null)
			return;
		
		if (NamedItem.isPluginItem((CraftItemStack) player.getItemInHand())) {
			
			NamedItem ni = new NamedItem(player.getItemInHand());
			
			if (ni.isMagical()) {
				
				MagicItem mi = new MagicItem(ni);
				if (mi.isWeapon()) {
					afp.setWeapon(mi);
					afp.updateWeaponEffects();
				}
				
			}
			
		}
		
	}
	
	public WeaponCheck(Player player) {
		this.player = player;
		afp = AFPlayer.getPlayer(player.getName());
	}
	
}
