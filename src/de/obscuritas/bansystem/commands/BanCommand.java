package de.obscuritas.bansystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.obscuritas.bansystem.Main;
import de.obscuritas.bansystem.util.BanManager;

public class BanCommand implements CommandExecutor {

	
	private Main main;
	
	public BanCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length >= 2) {
			String playername = args[0];
			
			if(BanManager.isBanned(getUUID(playername))) {
				sender.sendMessage(main.prefix + "§cDieser Spieler wurde bereits gebannt");
				return true;
			}
			
			String reason = "";
			
			for(int i = 1; i < args.length; i++) {
				reason += args[i] + " ";
			}
			BanManager.ban(getUUID(playername), playername, reason, -1);
			sender.sendMessage(main.prefix + "§7Du hast §e" + playername + " §7für §a" + reason + "§4PERMANENT §7gebannt!");
			return true;
		}
		sender.sendMessage(main.prefix + "§c/ban <Spieler> <Grund>");
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private String getUUID(String playername) {
		return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
	}
}
