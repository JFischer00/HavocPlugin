package io.github.jfischer00.havocplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class HavocPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("havoc").setExecutor(new HavocPluginCommandExecutor(this));
		getCommand("enabled").setExecutor(new HavocPluginCommandExecutor(this));
		
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable() {
	}
}
