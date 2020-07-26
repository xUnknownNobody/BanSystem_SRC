package de.obscuritas.bansystem.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.obscuritas.bansystem.Main;
import de.obscuritas.bansystem.util.BanManager;
import de.obscuritas.bansystem.util.BanUnit;

public class TempBanCommand implements CommandExecutor {

	
	private Main main;
	
	public TempBanCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length >= 4) {
			String playername = args[0];
			
			if(BanManager.isBanned(getUUID(playername))) {
				sender.sendMessage(main.prefix + "§cDieser Spieler wurde bereits gebannt");
				return true;
			}
			
			long value;
			
			try {
				value = Integer.valueOf(args[1]);
			} catch (NumberFormatException e) {
				sender.sendMessage(main.prefix + "§c<Wert> muss eine Zahl sein!");
				e.printStackTrace();
				return true;
			}
			
			String unitString = args[2];
			String reason = "";
			
			for(int i = 3; i < args.length; i++) {
				reason += args[i] + " ";
			}
			
			List<String> unitList = BanUnit.getUnitsAsString();
			
			if(unitList.contains(unitString.toLowerCase())) {
				BanUnit unit = BanUnit.getUnit(unitString);
				long seconds = value * unit.getToSecond();
				BanManager.ban(getUUID(playername), playername, reason, seconds);
				sender.sendMessage(main.prefix + "§7Du hast §e" + playername + " §7für §a" + reason + value + " §b" + unit.getName() + " §7gebannt!");
				return true;
			}
			sender.sendMessage("§cBitte benutze als Einheit: sek(Sekunde), min(Minute), tag(Tag), woche(Woche)");
			return true;
		}
		sender.sendMessage(main.prefix + "§c/tempban <Spieler> <Wert> <Einheit> <Grund>");
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private String getUUID(String playername) {
		return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
	}
}
