package fr.MrJeje_.Tag;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class LesCommandes implements CommandExecutor {
	
	private Tag pl;
	private FileConfiguration config;
	
	public LesCommandes(Tag tag) {
		this.pl = tag;
		this.config = pl.getConfig();
		}	
	
	int compteurjoueur = 0;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player)sender;
			 
			if(cmd.getName().equalsIgnoreCase("playertag")){	
				
				//au lieu de "ce joueur" n'est pas en ligne, mettre le nom du joueur
				
//=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-LISTE DES COMMANDES DU PLUGIN -=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=				
				if(args.length == 0){
					if(p.hasPermission("playertag.help")){ //avec aucun argument en plus
						p.sendMessage(
								"§e§l--- §fPlayerTag Info §e§l---"
								+ "\n§7Below is a list of all Tag commands:§f"
								+ "\n§6/ptag check §a<player>§6: Check the tag(s) on <player>"
								+ "\n§6/ptag checkall§6: Check all players online who got tag(s)"
								+ "\n§6/ptag add §a<player> <tag>§6: Add a <tag> on <player>"
								+ "\n§6/ptag remove §a<player> <tag>§6: Remove a <tag> on <player>"
								+ "\n§6/ptag clear §a<player>§6: Remove all tags on <player>"
								+ "\n§6/ptag kit§6: Give you a NameTag"
								+ "\n§6/ptag info§6: Give infos about the plugin"
								+ "\n§e§l----"
								);
				}
					else {
						p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
					}
				}
//=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-CREDIT DEVELOPPEUR=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=							
				if(args.length == 1){
					
					if(args[0].equalsIgnoreCase("info")){
						if (p.hasPermission("playertag.config")){
							p.sendMessage(
									"§e-----PlayerTag Config§e-----"
									+ "\n§7Version: §f"+ Bukkit.getServer().getPluginManager().getPlugin("PlayerTag").getDescription().getVersion()
									+ "\n§7Alert on Join (Player): §f"+ config.getString("JoinAlert.Player")
									+ "\n§7Alert on Join (Console): §f"+ config.getString("JoinAlert.Console")
									+ "\n§7Alert on Join (PlaySound): §f"+ config.getString("JoinAlert.Sound")
									+ "\n§7Alert on Add: §f"+ config.getString("CommandAlert.OnAdd")
									+ "\n§7Alert on Remove: §f"+ config.getString("CommandAlert.OnRemove")
									+ "\n§7Alert on Clear: §f"+ config.getString("CommandAlert.OnClear")
									+ "\n§7Author §fMrJeje_ §7Idea§f Dronox"
									+ "\n§e----------------------"
									
									);
						}
						else{
							p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
						}
					}
					
					if(args[0].equalsIgnoreCase("checkall")){		
						if (p.hasPermission("playertag.checkall")){
							compteurjoueur = 0;
							
							for (Player a: Bukkit.getOnlinePlayers()){
								
								if (a.getScoreboardTags().isEmpty() == false){
/* a laisser le customisation possible */		p.sendMessage(config.getString("Message.PluginName").replace("&", "§") + "§a" + a.getDisplayName() + " §f: §c" + a.getScoreboardTags());
									compteurjoueur += 1;
								}	
							}						
							if (compteurjoueur == 0){
								p.sendMessage((config.getString("Message.PluginName")+config.getString("Message.NoPlayersOnlineGotTags")).replace("&", "§"));
							}
						}
						else {
							p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
						}
					}
					
					if(args[0].equalsIgnoreCase("kit")){
						if (p.hasPermission("playertag.kit")){
						ItemStack etiquette = new ItemStack(Material.NAME_TAG, 1);
						ItemMeta laEtiquette = etiquette.getItemMeta();
						ArrayList<String> lore = new ArrayList<>();
						
						lore.add(ChatColor.GRAY + "Baguette II");
						lore.add("");
						lore.add(ChatColor.GOLD + "Right click a player to check him");
						
						laEtiquette.addEnchant(Enchantment.DURABILITY, 10, true);
						laEtiquette.setDisplayName(ChatColor.RED + "The Checkeur");
						laEtiquette.setLore(lore);
						etiquette.setItemMeta(laEtiquette);
					
						p.getInventory().addItem(etiquette);
						p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.Kit")).replace("&", "§"));
						}
						else {
							p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
						} 
					}
					
					if ((!args[0].equalsIgnoreCase("info")) && (!args[0].equalsIgnoreCase("checkall")) && (!args[0].equalsIgnoreCase("kit")) ){
						p.sendMessage((config.getString("Message.PluginName")+config.getString("Message.ErrorCommand")).replace("&", "§"));
					}
				}
//=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-CHECK LE TAG D'UN OU DE PLUSIEURS JOUEUR=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=				
				if(args.length == 2){//si il y 2 argument en plus de /tag
				
					Player cible = Bukkit.getPlayerExact(args[1]); //prendre le nom du joueur et renvoyer "null" si il n'existe pas 

						if(args[0].equalsIgnoreCase("check")){
							if (p.hasPermission("playertag.check")){
								if (cible != null){
									if (cible.getScoreboardTags().isEmpty() == false){
/* a laisser le customisation possible */	p.sendMessage(config.getString("Message.PluginName").replace("&", "§")+"§a" + cible.getDisplayName() + " §f: §c" + cible.getScoreboardTags());
									}
									else{
										p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.HasNoTag")).replace("{1}", cible.getDisplayName()).replace("&", "§"));
									}
								}
								else{
									p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.PlayerOffline")).replace("{1}", args[1]).replace("&", "§"));
								}
							}
							else {
								p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
							}
						}	
						
						if(args[0].equalsIgnoreCase("clear")){
							if(p.hasPermission("playertag.clear")){
								if (cible != null){
									if (cible.getScoreboardTags().isEmpty() == false){		
										cible.getScoreboardTags().clear();
										p.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.ClearTag") ).replace("{1}", cible.getDisplayName()).replace("&", "§") );
										if(config.getString("CommandAlert.OnClear") == "true")
									 	{
									 		for (Player s : Bukkit.getOnlinePlayers()){
												if ((s.hasPermission("playertag.info")) && (s.getDisplayName() != p.getDisplayName()) ){
													s.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.AlertClearTag") ).replace("{1}", p.getDisplayName()).replace("{2}", cible.getDisplayName()).replace("&", "§") );
												}
									 		}
									 	}
									}
									else {
/* avec 2 changements*/						p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.HasNoTag")).replace("{1}", cible.getDisplayName()).replace("&", "§"));
									}
								}
								else {
									p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.PlayerOffline")).replace("{1}", args[1]).replace("&", "§"));
								}
							}
							else {
								p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
							}
						}
						
						//si commande n'existe pas
						if ((!args[0].equalsIgnoreCase("check")) && (!args[0].equalsIgnoreCase("clear")) ){
							p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.ErrorCommand")).replace("&", "§"));
						}
					}					
//=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=AJOUTE ET ENLEVE UN TAG D'UN JOUEUR=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=				
				if(args.length == 3){

					Player cible = Bukkit.getPlayerExact(args[1]);
					String tag = args[2];
						
						if(args[0].equalsIgnoreCase("add")){
								if (p.hasPermission("playertag.add")){
									if (cible != null){
										if(cible.getScoreboardTags().contains(tag)){
/* avec 3 changements*/						p.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.AlreadyGotTag") ).replace("{1}", cible.getDisplayName()).replace("{2}", tag).replace("&", "§") );
										}
										else {
											if (cible.hasPermission("playertag.immune")){
												p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.Immune")).replace("{1}", cible.getDisplayName()).replace("&", "§"));
											}
											else{
											cible.getScoreboardTags().add(tag);
											p.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.AddTag") ).replace("{1}", tag).replace("{2}", cible.getDisplayName()).replace("&", "§") );
											 	if(config.getString("CommandAlert.OnAdd") == "true")
											 	{
											 		for (Player s : Bukkit.getOnlinePlayers()){
														if ((s.hasPermission("playertag.info")) && (s.getDisplayName() != p.getDisplayName()) ){
/* avec 4 changements*/										s.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.AlertAddTag") ).replace("{1}", p.getDisplayName()).replace("{2}", tag).replace("{3}", cible.getDisplayName()).replace("&", "§") );
														}
											 		}
											 	}
											}
										}
									}
									else{
										p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.PlayerOffline")).replace("{1}", args[1]).replace("&", "§"));
									}
								}
								else {
									p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
								}
						}
					
						//enlever
						if(args[0].equalsIgnoreCase("remove")){
								if(p.hasPermission("playertag.remove")){
									if (cible != null){
										if(cible.getScoreboardTags().contains(tag)){
											cible.getScoreboardTags().remove(tag);
											p.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.RemovedTag") ).replace("{1}", tag).replace("{2}", cible.getDisplayName()).replace("&", "§") );
											if(config.getString("CommandAlert.OnRemove") == "true")
										 	{
										 		for (Player s : Bukkit.getOnlinePlayers()){
													if ((s.hasPermission("playertag.info")) && (s.getDisplayName() != p.getDisplayName()) ){
														 s.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.AlertRemoveTag") ).replace("{1}", p.getDisplayName()).replace("{2}", tag).replace("{3}", cible.getDisplayName()).replace("&", "§") );
													}
										 		}
										 	}
										}
										else {
											p.sendMessage( ( config.getString("Message.PluginName") + config.getString("Message.ErrorOnRemove") ).replace("{1}", cible.getDisplayName()).replace("{2}", tag).replace("&", "§") );
										}
									}
									else{
										p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.PlayerOffline")).replace("{1}", args[1]).replace("&", "§"));
									}
								}
								else {
									p.sendMessage((config.getString("Message.PluginName") + config.getString("Message.NoPerm")).replace("&", "§"));
								}
						}
						
						if ((!args[0].equalsIgnoreCase("add")) && (!args[0].equalsIgnoreCase("remove")) ){
							p.sendMessage((config.getString("Message.PluginName")+config.getString("Message.ErrorCommand")).replace("&", "§"));
						}
				}
//=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=--=-=				

			if(args.length > 3){
				p.sendMessage((config.getString("Message.PluginName")+config.getString("Message.ErrorCommand")).replace("&", "§"));
			}
		}
	}
		return false;
	}
}
