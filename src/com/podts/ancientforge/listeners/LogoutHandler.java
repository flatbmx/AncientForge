package com.podts.ancientforge.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.podts.ancientforge.player.AFPlayer;

public class LogoutHandler implements Listener {
	
	@EventHandler
    public void normalLogin(PlayerQuitEvent event) {
        
		AFPlayer afplayer = AFPlayer.getPlayer(event.getPlayer().getName());
		
		if (afplayer == null)
			return;
		
		AFPlayer.getPlayers().remove(afplayer);
		
    }
	
}
