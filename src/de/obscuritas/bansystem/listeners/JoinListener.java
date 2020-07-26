package de.obscuritas.bansystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.obscuritas.bansystem.util.BanManager;

public class JoinListener implements Listener {
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if(BanManager.isBanned(player.getUniqueId().toString())) {
			BanManager.unban(player.getUniqueId().toString());
		}
	}
}
