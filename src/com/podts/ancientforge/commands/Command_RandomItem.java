package com.podts.ancientforge.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.podts.ancientforge.AFCommand;
import com.podts.ancientforge.MagicItem;

public class Command_RandomItem extends AFCommand {

	public Command_RandomItem() {
		super("randomitem", 0);
	}

	@Override
	public void handle(CommandSender sender, String[] args) {
		
		Player p = (Player) sender;
		
		if (!p.hasPermission("ancientforge.randomitem"))
			return;
		
		Player rec;
		
		if (args.length > 0) {
			rec = Bukkit.getServer().getPlayer(args[0]);
			if (rec == null) {
				p.sendMessage(ChatColor.RED + "Could not find player.");
				return;
			}
		}
		else {
			rec = p;
		}
		
		MagicItem mi = MagicItem.getRandomeMagicItem();
		
		rec.getInventory().addItem(mi.getItemStack());
		
		if (!rec.equals(p))
			rec.sendMessage("You got a magic item from " + p.getDisplayName());
		
	}

}
