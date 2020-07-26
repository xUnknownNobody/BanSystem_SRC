package de.obscuritas.bansystem;

import org.bukkit.plugin.java.JavaPlugin;

import de.obscuritas.bansystem.commands.BanCommand;
import de.obscuritas.bansystem.commands.CheckCommand;
import de.obscuritas.bansystem.commands.TempBanCommand;
import de.obscuritas.bansystem.commands.UnbanCommand;
import de.obscuritas.bansystem.listeners.*;
import de.obscuritas.bansystem.mysql.MySQL;
import de.obscuritas.bansystem.util.FileManager;

public class Main extends JavaPlugin {
	
	
	public String prefix;
	public static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		registerEvents();
		registerCommands();
		
		FileManager.setDefaultConfig();
		FileManager.setDefaultMySQL();
		FileManager.readConfig();
		FileManager.readMySQL();
		
		MySQL.connect();
		MySQL.createTable();
	}

	@Override
	public void onDisable() {
		MySQL.close();
	}
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new LoginListener(), this);
		this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
	}
	
	public void registerCommands() {
		getCommand("ban").setExecutor(new BanCommand(this));
		getCommand("tempban").setExecutor(new TempBanCommand(this));
		getCommand("unban").setExecutor(new UnbanCommand(this));
		getCommand("check").setExecutor(new CheckCommand(this));
	}

	public static Main getInstance() {
		return instance;
	}
}
