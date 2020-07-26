package de.obscuritas.bansystem.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.obscuritas.bansystem.mysql.MySQL;

public class BanManager {

	
	public static void ban(String uuid, String playername, String reason, long seconds) {
		long end = 0;
		
		if(seconds == -1) {
			end = -1;		
		} else {
			long current = System.currentTimeMillis();
			long millis = seconds * 1000;
			end = current + millis;
		}
		
		MySQL.update("INSERT INTO banned_players (name, uuid, ende, reason) VALUES ('" + playername + "','" + uuid + "','" + end +"','" + reason +"')");
		
		Player banned_player = Bukkit.getPlayer(playername);
		
		if(banned_player != null) {
			banned_player.kickPlayer("§8---------------------------------------------------------\n" +
									 "§4Du wurdest vom Server gebannt!\n" + 
									 							"\n" + 
									 "§eGrund: §b" + getReason(uuid) + "\n" +
									 							"\n" +
									 "§eVerbleibende Zeit: " + getRemainingTime(uuid) + "\n" +
									 							"\n" +
									 "§aDu kannst keinen Entbannungsantrag stellen! \n" +	
									 "§8---------------------------------------------------------\n");
		}
	}
	
	public static void unban(String uuid) {
		MySQL.update("DELETE FROM banned_players WHERE UUID='" + uuid + "'");
	}
	
	public static boolean isBanned(String uuid) {
		ResultSet resultSet = MySQL.getResult("SELECT Ende FROM banned_players WHERE UUID='" + uuid + "'");
		
		try {
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getReason(String uuid) {
		ResultSet resultSet = MySQL.getResult("SELECT * FROM banned_players WHERE UUID='" + uuid + "'");
		
		try {
			while(resultSet.next()) {
				return resultSet.getString("reason");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return "";
	}
	
	public static Long getEnd(String uuid) {
		ResultSet resultSet = MySQL.getResult("SELECT * FROM banned_players WHERE UUID='" + uuid + "'");
		
		try {
			while(resultSet.next()) {
				return resultSet.getLong("ende");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static List<String> getBannedPlayers() {
		List<String> list = new ArrayList<String>();
		ResultSet resultSet = MySQL.getResult("SELECT * FROM banned_players");
		
		try {
			while(resultSet.next()) {
				list.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getRemainingTime(String uuid) {
		long current = System.currentTimeMillis();
		long end = getEnd(uuid);
		long millis = end - current;
		
		if(end == -1) {
			return "§4PERMANENT";
		}
		
		int seconds = 0,
			minutes = 0,
			hours = 0,
			days = 0,
			weeks = 0,
			months = 0;
		
		while(millis > 1000) {
			millis -= 1000;
			seconds++;
		}
		
		while(seconds > 60) {
			seconds -= 60;
			minutes++;
		}
		
		while(minutes > 60) {
			minutes -= 60;
			hours++;
		}
		
		while(hours > 24) {
			hours -= 24;
			days++;
		}
		
		while(days > 7) {
			days -= 7;
			weeks++;
		}
		
		while(weeks > 4) {
			weeks -= 4;
			months++;
		}
		
		return months + " Monat(e) " + weeks + " Woche(n) " + days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n) ";
	}
}
