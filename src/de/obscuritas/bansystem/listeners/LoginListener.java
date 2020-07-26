package de.obscuritas.bansystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.obscuritas.bansystem.util.BanManager;

public class LoginListener implements Listener {

	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		
		if(BanManager.isBanned(uuid)) {
			long current = System.currentTimeMillis();
			long end = BanManager.getEnd(uuid);
			
			if(current < end || end == -1) {
				event.disallow(Result.KICK_BANNED, "§8---------------------------------------------------------\n" +
												   "§4Du wurdest vom Server gebannt!\n" + 
												   							"\n" + 
												   "§eGrund: §b" + BanManager.getReason(uuid) + "\n" +
												   							"\n" +
												   "§eVerbleibende Zeit: " + BanManager.getRemainingTime(uuid) + "\n" +
																			"\n" +
												   "§aDu kannst keinen Entbannungsantrag stellen! \n" +	
												   "§8---------------------------------------------------------\n");
			}
		}
	}
}
