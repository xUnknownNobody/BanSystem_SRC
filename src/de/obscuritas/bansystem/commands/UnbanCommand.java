package de.obscuritas.bansystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.obscuritas.bansystem.Main;
import de.obscuritas.bansystem.util.BanManager;

public class UnbanCommand implements CommandExecutor {
	
	
	private Main main;
	
	public UnbanCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
			String playername = args[0];
			
			if(!BanManager.isBanned(getUUID(playername))) {
				sender.sendMessage(main.prefix + "§cDieser Spieler ist nicht gebannt!");
				return true;
			}
			BanManager.unban(getUUID(playername));
			sender.sendMessage(main.prefix + "§7Du hast §e" + playername + " §aentbannt§7!");
			return true;
		}
		sender.sendMessage(main.prefix + "§c/unban <Spieler>");
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private String getUUID(String playername) {
		return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
	}
}
