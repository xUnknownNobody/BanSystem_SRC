package de.obscuritas.bansystem.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.obscuritas.bansystem.Main;

public class MySQL {

	
	public static String username,
						 password,
						 database,
						 host,
						 port;
	
	public static Connection connection;
	
	public static void connect() {
		if(!isConnected()) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
				Bukkit.getConsoleSender().sendMessage(Main.getInstance().prefix + "Verbindung zu MySQL-Datenbank aufgebaut!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close() {
		if(isConnected()) {
			try {
				connection.close();
				Bukkit.getConsoleSender().sendMessage(Main.getInstance().prefix + "Verbindung zu MySQL-Datenbank geschlossen!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		return connection != null;
	}
	
	public static void createTable() {
		if(isConnected()) {
			try {
				connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS banned_players (name VARCHAR(100), uuid VARCHAR(100), ende VARCHAR(100), reason VARCHAR(100))");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void update(String query) {
		if(isConnected()) {
			try {
				connection.createStatement().executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ResultSet getResult(String query) {
		if(isConnected()) {
			try {
				return connection.createStatement().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
