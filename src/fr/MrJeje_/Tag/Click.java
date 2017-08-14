package fr.MrJeje_.Tag;

import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Click implements Listener {
	
	private Tag pl;
	private FileConfiguration config;

	public Click(Tag tag) {
		this.pl = tag;
		this.config = pl.getConfig();
	}
		
		@EventHandler
		public void onPInteract(PlayerInteractEntityEvent e){

			if (e.getRightClicked() instanceof Player){
				Player toucheur = e.getPlayer();
				Player cible = (Player)e.getRightClicked();
				if((toucheur.getInventory().getItemInMainHand().getType() == Material.NAME_TAG) &&(e.getHand() != EquipmentSlot.OFF_HAND)){
					
					if (toucheur.hasPermission("playertag.kit")){
						if (cible.getScoreboardTags().size() > 0){
	/* a laisser le customisation possible */	toucheur.sendMessage("§e[§aPlayerTag§e] "+"§a" + cible.getDisplayName() + "§f : §c" + cible.getScoreboardTags());
						}
						else 
						{
						toucheur.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoTagOnClick")).replace("{1}", cible.getDisplayName()).replace("&", "§"));
						}
					}
					
					else {
						toucheur.sendMessage( (config.getString("Message.PluginName") + config.getString("Message.NoPerm") ).replace("&", "§") );
					}
				}
			}
		}

}
