package com.podts.ancientforge.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.podts.ancientforge.player.AFPlayer;

public class DamageHandler implements Listener {
	
	@EventHandler
	public void OnEntityDamage(EntityDamageByEntityEvent event) {
		
		if (event.getEntity().isDead())
			return;
		
		int damageamount = event.getDamage();
		
		// If the attacker is a player.
		if (event.getDamager() instanceof Player) {
			
			Player attacker = (Player) event.getDamager();
			
			AFPlayer afp = AFPlayer.getPlayer( attacker.getName() );
			
			if (afp.getEffects().getDamagemodifier() > 0) {
				
				float famount = event.getDamage()*afp.getEffects().getDamagemodifier();
				int amount = (int) famount;
				float remainder = famount - amount;
				
				afp.getEffects().bumpDamageleft(remainder);
				
				if ( (int) afp.getEffects().getDamageleft() > 0) {
					amount += (int) afp.getEffects().getDamageleft();
					afp.getEffects().setDamageleft( afp.getEffects().getDamageleft() - (int) afp.getEffects().getDamageleft() );
				}
					
				damageamount += amount;
				
			}
			
			if (afp.getEffects().getDamgeincrease() > 0) {
				damageamount += afp.getEffects().getDamgeincrease();
			}
			
			if (afp.getEffects().getLifesteal() > 0) {
				
				float fheal = damageamount*afp.getEffects().getLifesteal();
				int iheal = (int) fheal;
				afp.getEffects().bumpDamageleft(fheal-iheal);
				if (afp.getEffects().getLifestealleft() > 1) {
					iheal += (int) afp.getEffects().getLifestealleft();
					afp.getEffects().bumpLifestealleft((int) afp.getEffects().getLifestealleft()*-1);
				}
				
				attacker.setHealth(attacker.getHealth() + iheal);
				
			}
			
		}
		
		// If the entity that is getting hurt is a player.
		if (event.getEntity() instanceof Player) {
			
			Player player = (Player) event.getEntity();
			
			AFPlayer defender = AFPlayer.getPlayer(player.getName());
			
			if (defender.getEffects().getArmormodifier() > 0) {
				float famount = damageamount - (damageamount*defender.getEffects().getArmormodifier());
				int amount = (int) famount;
				float remainder = famount - amount;
				
				defender.getEffects().bumpArmorleft(remainder);
				
				if ( (int) defender.getEffects().getArmorleft() > 0 ) {
					amount -= (int) defender.getEffects().getArmorleft();
					defender.getEffects().setArmorleft(defender.getEffects().getArmorleft() - (int) defender.getEffects().getArmorleft());
				}
				
				damageamount = amount;
				
			}
			
			if (defender.getEffects().getArmorincrease() > 0) {
				damageamount -= defender.getEffects().getArmorincrease();
			}
			
		}
		
		if (damageamount < 1)
			damageamount = 0;
		
		event.setDamage(damageamount);
		
	}
	
}
