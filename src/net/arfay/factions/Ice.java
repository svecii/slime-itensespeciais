package net.arfay.factions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.arfay.factions.api.RecipeItem;
import net.arfay.factions.commands.EspecialCommand;
import net.arfay.factions.listeners.ChunkClearListener;
import net.arfay.factions.listeners.GeneratorListener;
import net.arfay.factions.listeners.LauncherListener;
import net.arfay.factions.listeners.RegeneradorListener;
import net.arfay.factions.listeners.TntThrowListener;
import net.arfay.factions.manager.EspecialManager;

public class Ice extends JavaPlugin {
	
	public void onEnable() {
		getLogger().info("enabled.");
        new EspecialManager();
        new RecipeItem();
		this.Commands();
		this.Listeners();
		
	}
	
	private void Listeners() {
        Bukkit.getPluginManager().registerEvents((Listener)new LauncherListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new TntThrowListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new GeneratorListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ChunkClearListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new RegeneradorListener(), (Plugin)this);
	}
	
	private void Commands() {
		this.getCommand("item").setExecutor((CommandExecutor)new EspecialCommand());
	}
	
	public static Ice get() {
		return (Ice)JavaPlugin.getPlugin(Ice.class);
	}

}
