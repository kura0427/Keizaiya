package keizaiya.second.chat;

import keizaiya.second.file.Admin.adminfile;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class chatcommand {
    public static void onauthorcommnand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("chatmode")) {
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("admin")){
                        if(adminfile.checkmember(player)){
                            Playerdata.chengchatmode(player,100);
                            player.sendMessage("§8[§7System§8] §7ChatMode §8(§cAdmin§8)");
                        }else{
                            player.sendMessage("§8[§7System§8] §7あなたには権限がありません");
                        }
                    }else if(args[0].equalsIgnoreCase("1")){
                        if(Playerdata.getCountrytag(player,1).contains("null") == false){
                            Playerdata.chengchatmode(player,1);
                            player.sendMessage("§8[§7System§8] §7ChatMode §8(§eCountry1§8)");
                        }else{
                            player.sendMessage("§8[§7System§8] §7そのアカウントでは国に入っていません");
                        }
                    }else if(args[0].equalsIgnoreCase("2")){
                        if(Playerdata.getCountrytag(player,2).contains("null") == false){
                            Playerdata.chengchatmode(player,2);
                            player.sendMessage("§8[§7System§8] §7ChatMode §8(§eCountry2§8)");
                        }else{
                            player.sendMessage("§8[§7System§8] §7そのアカウントでは国に入っていません");
                        }
                    }else if(args[0].equalsIgnoreCase("3")){
                        if(Playerdata.getCountrytag(player,3).contains("null") == false){
                            Playerdata.chengchatmode(player,3);
                            player.sendMessage("§8[§7System§8] §7ChatMode §8(§eCountry3§8)");
                        }else{
                            player.sendMessage("§8[§7System§8] §7そのアカウントでは国に入っていません");
                        }
                    }else{
                        player.sendMessage("§8[§7System§8] §7/chatmode <number>");
                    }
                }if(args.length == 0) {
                    Playerdata.aotochangechatmode(player);
                    Integer now = Playerdata.chatmode(player);
                    String mode = "";
                    if (now == 0) {
                        mode = "§bAll";
                    }
                    if (now == 1) {
                        mode = "§eCountry1";
                    }
                    if (now == 2) {
                        mode = "§eCountry2";
                    }
                    if (now == 3) {
                        mode = "§eCountry3";
                    }
                    player.sendMessage("§8[§7System§8] §7ChatMode §8(" + mode + "§8)");
                }

            }
        }
    }
}

