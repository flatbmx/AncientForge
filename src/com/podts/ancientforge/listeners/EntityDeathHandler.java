package com.podts.ancientforge.listeners;

import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.player.AFPlayer;

public class EntityDeathHandler implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event) {
		
		if (event.getEntity() instanceof Player) {
			
			Player player = (Player) event.getEntity();
			
			AFPlayer afp = AFPlayer.getPlayer(player.getName());
			
			afp.resetEffects();
			
		}
		else {
			
			if (!(event.getEntity() instanceof Monster))
				return;
			
			if (!(event.getEntity().getKiller() instanceof Player))
				return;
			
			if (event.getEntity().getKiller() == null)
				return;
			
			Monster monster = (Monster) event.getEntity();
			
			Player bukkitkiller = monster.getKiller();
			
			AFPlayer afp = AFPlayer.getPlayer(bukkitkiller.getName());
			
			float droprate = (float) P.getConfigFile().getDouble("ItemDropRate-Mobs");
			
			if (monster instanceof Wither)
				droprate = (float) P.getConfigFile().getDouble("ItemDropRate-Wither");
			
			if (P.getRandom().nextFloat() + afp.getEffects().getFindmagicitems() > 1-droprate ) {
				
				MagicItem item = MagicItem.getRandomeMagicItem();
				Item worlditem = monster.getWorld().dropItemNaturally(monster.getLocation(), item.getItemStack());
				worlditem.setItemStack(item.getItemStack());
				
			}
			
		}
		
		
		
	}
	
}
