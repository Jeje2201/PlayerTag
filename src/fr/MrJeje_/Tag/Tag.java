package fr.MrJeje_.Tag;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Tag extends JavaPlugin implements Listener {
	
	public void onEnable(){
		
		System.out.println("[PlayerTag] Enable! (by MrJeje)"); //affiche dans la console le message que le plugin est activé
		
		getConfig().options().copyDefaults(true); //chargement de la config par defaut
		saveConfig();
		
		getCommand("playertag").setExecutor(new LesCommandes(this)); //creer la commande /tag avec une classe TagCommand
		getCommand("ptag").setExecutor(new LesCommandes(this));
		
		PluginManager pm = getServer().getPluginManager(); //Indique qu'il existe d'autres classes a action
		pm.registerEvents(new Join(this), this); //indique la classe particules a prendre en compte
		pm.registerEvents(new Click(this), this);
	}
	
	public void onDisable(){
		System.out.println("[PlayerTag] Disable! (by MrJeje)");
	}

}