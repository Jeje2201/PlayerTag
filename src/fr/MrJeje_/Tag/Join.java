package fr.MrJeje_.Tag;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

	private Tag pl;
	private FileConfiguration config;

	public Join(Tag tag) {
		this.pl = tag;
		this.config = pl.getConfig();
		}	
	
		@EventHandler
		public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
			for (Player s : Bukkit.getOnlinePlayers()){
				if ((s.hasPermission("playertag.join")) && (p.getScoreboardTags().isEmpty() == false) && (config.getString("JoinAlert.Player") == "true")){
	/* a laisser le customisation possible */	s.sendMessage("§e[§aPlayerTag§e] §a"+p.getDisplayName()+ " §fjoined with §c"+ p.getScoreboardTags());
					if (config.getString("JoinAlert.Sound") == "true"){
						String LeSons = config.getString("Sound.Join");
						s.playSound(s.getLocation(),Sound.valueOf(LeSons), 1, 1);
					}
				}
			}
			
			if ((p.getScoreboardTags().isEmpty() == false) && (config.getString("JoinAlert.Console") == "true")){
	/* a laisser le customisation possible */	System.out.println("[PlayerTag] "+p.getDisplayName()+ " joined with "+ p.getScoreboardTags());
			}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//This is for mrjeje, just to get a popup when someone mine a diamond, don't need to flood the console and other staff don't want to get spam of this, so to not bother anyone, popup mrjeje only to find xray players 
		@EventHandler
	    public void onBlockBreak(BlockBreakEvent e) {
	        Player c = e.getPlayer();
	        Block b = e.getBlock();
	        Player m = Bukkit.getServer().getPlayer("MrJeje_");
	       
	        if ((m != null) && (b.getType() == Material.DIAMOND_ORE) ){
	        	m.sendMessage( "[§cAlert§f] \""+c.getDisplayName() + "\" mine du §bdiamand");
	        }
					
    	}

}

