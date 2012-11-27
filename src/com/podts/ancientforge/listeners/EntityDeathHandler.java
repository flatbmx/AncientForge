package com.podts.ancientforge.listeners;

import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.podts.ancientforge.MagicItem;
import com.podts.ancientforge.P;
import com.podts.ancientforge.player.AFPlayer;

public class EntityDeathHandler implements Listener {
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		
		if (event.getEntity() instanceof Player)
			return;
		
		if (!(event.getEntity() instanceof Monster))
			return;
		
		if (!(event.getEntity().getKiller() instanceof Player))
			return;
		
		if (event.getEntity().getKiller() == null)
			return;
		
		Monster monster = (Monster) event.getEntity();
		
		Player bukkitkiller = monster.getKiller();
		
		AFPlayer afp = AFPlayer.getPlayer(bukkitkiller.getName());
		
		if (P.getRandom().nextFloat() + afp.getEffects().getFindmagicitems() > .2f ) {
			
			bukkitkiller.sendMessage("The monster dropped a magical item");
			MagicItem item = MagicItem.getRandomeMagicItem();
			Item worlditem = monster.getWorld().dropItemNaturally(monster.getLocation(), item.getItemStack());
			worlditem.setItemStack(item.getItemStack());
			
		}
		
	}
	
}
