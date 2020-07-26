package de.obscuritas.bansystem.util;

import java.util.ArrayList;
import java.util.List;

public enum BanUnit {
	
	
	SECOND("Sekunde(n)", "sek", 1),
	MINUTE("Minute(n)", "min", 60),
	HOUR("Stunde(n)", "std", 60*60),
	DAY("Tag(e)", "tag", 24*60*60),
	WEEK("Woche(n)", "woche", 7*24*60*60);
	
	private String name,
				   shortcut;
	private long toSecond;
	
	private BanUnit(String name, String shortcut, long toSecond) {
		this.name = name;
		this.shortcut = shortcut;
		this.toSecond = toSecond;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShortcut() {
		return shortcut;
	}
	
	public long getToSecond() {
		return toSecond;
	}
	
	public static List<String> getUnitsAsString() {
		List<String> units = new ArrayList<>();
		
		for(BanUnit unit : BanUnit.values()) {
			units.add(unit.getShortcut().toLowerCase());
		}
		return units;
	}
	
	public static BanUnit getUnit(String unit) {
		for(BanUnit units : BanUnit.values()) {
			if(units.getShortcut().toLowerCase().equals(unit.toLowerCase())) {
				return units;
			}
		}
		return null;
	}
}
