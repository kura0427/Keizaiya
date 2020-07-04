package keizaiya.second.author;

import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;
import java.util.List;

public class authorcommand {
    public static void onauthorcommnand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("print")){
                if(args.length == 1){
                    if(Countrydata.checkpermission(player,"item")) {
                        if (player.getInventory().getItemInMainHand().getType() == Material.PAPER) {
                            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                            meta.setDisplayName(args[0]);
                            List<String> lore = new ArrayList<>();
                            lore.add("§7Name: " + args[0]);
                            lore.add("§7Country: " + Countrydata.getCountryName(Playerdata.getNowCountry(player)));
                            meta.setLore(lore);
                            player.getInventory().getItemInMainHand().setItemMeta(meta);
                        }
                    }
                }
            }if(cmd.getName().equalsIgnoreCase("toolcolor")){
                if(player.getInventory().getItemInMainHand().getType() != null){
                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setDisplayName(meta.getDisplayName().replace("&","§"));
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                }
            }if(cmd.getName().equalsIgnoreCase("playerinfo")){
                if(args.length == 1){
                    Player player2 = null;
                    try{player2 = Bukkit.getPlayer(args[0]);
                    }catch (Exception e){}
                    if (player2 != null) {
                        System.out.println("^^");
                        String message = "§8=*=*=*=*= §7Info §8=*=*=*=*=\n";
                        message = message + "§7名前 §8>>§7§l" + args[0] + "\n";
                        if(Playerdata.getCountrytag(player2,1).contains("null") == false){
                            message = message + "§8=*=*=*= §7Account No.1 §8=*=*=*=\n";
                            message = message + Countrydata.getlowInfo(Playerdata.getCountrytag(player2,1));
                        }if(Playerdata.getCountrytag(player2,2).contains("null") == false){
                            message = message + "\n§8=*=*=*= §7Account No.2 §8=*=*=*=\n";
                            message = message + Countrydata.getlowInfo(Playerdata.getCountrytag(player2,2));
                        }if(Playerdata.getCountrytag(player2,3).contains("null") == false){
                            message = message + "\n§8=*=*=*= §7Account No.3 §8=*=*=*=\n";
                            message = message + Countrydata.getlowInfo(Playerdata.getCountrytag(player2,3));
                        }
                        player.sendMessage(message);
                    }
                }

            }if(cmd.getName().equalsIgnoreCase("selfkick")){
                player.kickPlayer("あなたをKickしました ^^");
            }
        }
    }
}
