package de.obscuritas.bansystem.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.obscuritas.bansystem.Main;
import de.obscuritas.bansystem.util.BanManager;

public class CheckCommand implements CommandExecutor {

	
    private Main main;
	
	public CheckCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("list")) {
				List<String> list = BanManager.getBannedPlayers();
				
				if(list.size() == 0) {
					sender.sendMessage(main.prefix + "§cKeine Spieler gebannt!");
					return true;
				}
				
				sender.sendMessage(main.prefix + "§7--------= §dBan-Liste §7=--------");
				for(String allBanned : BanManager.getBannedPlayers()) {
					sender.sendMessage(main.prefix + "§a" + allBanned + " §7(Grund: §e" + BanManager.getReason(getUUID(allBanned)) + "§7)");
				}
				sender.sendMessage(main.prefix + "§7--------= §dBan-Liste §7=--------");
				return true;
			}
			String playername = args[0];
			sender.sendMessage(main.prefix + "§7-----------= §fBan-Info §7=-----------");
			sender.sendMessage(main.prefix + "§fName: §e " + playername);
			sender.sendMessage(main.prefix + "§fStatus: " + (BanManager.isBanned(getUUID(playername)) ? "§cgebannt" : "§aaktiv"));
			
			if(BanManager.isBanned(getUUID(playername))) {
				sender.sendMessage(main.prefix + "§fGrund: §e " + BanManager.getReason(getUUID(playername)));
				sender.sendMessage(main.prefix + "§fVerbleibende Zeit: §e " + BanManager.getRemainingTime(getUUID(playername)));
			}
			sender.sendMessage(main.prefix + "§7-----------= §fBan-Info §7=-----------");
			return true;
		}
		sender.sendMessage(main.prefix + "§c/check (list) <Spieler>");
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private String getUUID(String playername) {
		return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
	}
}
