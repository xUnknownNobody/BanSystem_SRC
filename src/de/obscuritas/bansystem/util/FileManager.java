package de.obscuritas.bansystem.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.obscuritas.bansystem.Main;
import de.obscuritas.bansystem.mysql.MySQL;

public class FileManager {

	
	public static File getConfigFile() {
		return new File("plugins/Banmanager", "config.yml");
	}
	
	public static FileConfiguration getConfigFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getConfigFile());
	}
	
	public static File getMySQlFile() {
		return new File("plugins/Banmanager", "mysql.yml");
	}
	
	public static FileConfiguration getMySQLFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getMySQlFile());
	}
	
	public static void setDefaultConfig() {
		FileConfiguration fileConfiguration = getConfigFileConfiguration();
		fileConfiguration.options().copyDefaults(true);
		fileConfiguration.addDefault("prefix", "[BanSystem]");
		
		try {
			fileConfiguration.save(getConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readConfig() {
		FileConfiguration fileConfiguration = getConfigFileConfiguration();
		
		Main.getInstance().prefix = ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("prefix") + " §r");
	}
	
	public static void setDefaultMySQL() {
		FileConfiguration fileConfiguration = getMySQLFileConfiguration();
		fileConfiguration.options().copyDefaults(true);
		
		fileConfiguration.addDefault("username", "root");
		fileConfiguration.addDefault("password", "password");
		fileConfiguration.addDefault("database", "localhost");
		fileConfiguration.addDefault("host", "localhost");
		fileConfiguration.addDefault("port", "3306");
		
		try {
			fileConfiguration.save(getMySQlFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readMySQL() {
		FileConfiguration fileConfiguration = getMySQLFileConfiguration();
		
		MySQL.username = fileConfiguration.getString("username");
		MySQL.password = fileConfiguration.getString("password");
		MySQL.database = fileConfiguration.getString("database");
		MySQL.host = fileConfiguration.getString("host");
		MySQL.port = fileConfiguration.getString("port");
	}
}
