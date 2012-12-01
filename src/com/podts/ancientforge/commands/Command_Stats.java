package com.podts.ancientforge.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.podts.ancientforge.AFCommand;
import com.podts.ancientforge.player.AFPlayer;

public class Command_Stats extends AFCommand {

	public Command_Stats() {
		super("stats",0);
	}

	@Override
	public void handle(CommandSender sender, String[] args) {
		
		Player player = (Player) sender;
		AFPlayer afp = AFPlayer.getPlayer(player.getName());
		
		player.sendMessage(ChatColor.DARK_RED + "==============================");
		
		if (afp.getEffects().getDamgeincrease() != 0)
			player.sendMessage(ChatColor.DARK_RED + "Damage +: " + afp.getEffects().getDamgeincrease());
		if (afp.getEffects().getDamagemodifier() != 0)
			player.sendMessage(ChatColor.DARK_RED + "Damage +: " + afp.getEffects().getDamagemodifier()*100 + "%");
		if (afp.getEffects().getArmorincrease() != 0)
			player.sendMessage(ChatColor.BLUE + "Armor +: " +afp.getEffects().getArmorincrease());
		if (afp.getEffects().getArmormodifier() != 0)
			player.sendMessage(ChatColor.BLUE + "Armor +: " + afp.getEffects().getArmormodifier()*100 + "%");
		if (afp.getEffects().getLifesteal() != 0)
			player.sendMessage(ChatColor.DARK_RED + "Life Steal +: " + afp.getEffects().getLifesteal()*100 + "%");
		
		player.sendMessage(ChatColor.DARK_RED + "==============================");
		
	}

}
