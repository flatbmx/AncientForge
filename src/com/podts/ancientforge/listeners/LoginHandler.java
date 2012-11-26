package com.podts.ancientforge.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.podts.ancientforge.player.AFPlayer;

public class LoginHandler implements Listener {
	
	@EventHandler
    public void normalLogin(PlayerJoinEvent event) {
        
		new AFPlayer(event.getPlayer());
		
    }
	
}
