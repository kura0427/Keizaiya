package keizaiya.second.author;

import keizaiya.second.chat.chat;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import keizaiya.second.inventory.help;
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
                if(player.getInventory().getItemInMainHand().getType() != Material.AIR){
                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                    if(meta != null) {
                        meta.setDisplayName(meta.getDisplayName().replace("&", "§"));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                    }
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
            if(cmd.getName().equalsIgnoreCase("w") || cmd.getName().equalsIgnoreCase("dm")){
                if(args.length >= 2){
                    Player player1 = Bukkit.getPlayer(args[0]);
                    if(player1 != null){
                        String message = "";
                        for(int i = 1; i < args.length;i++){
                            message = message + args[i] +  " ";
                        }
                        message = chat.chengemessage(message,player);
                        player1.sendMessage("§8[§6DM§8][§7By: " + player.getDisplayName() + "§8]§f"+ message);
                        player.sendMessage("§8[§6DM§8][§7To: " + player1.getDisplayName() + "§8]§f"+ message);
                        chat.dm.put(player,player1);
                        chat.dm.put(player1,player);
                        chat.Log(player.getDisplayName() +" To " + player1.getDisplayName() + message);
                    }else{
                        player.sendMessage("指定したプレイヤーはいません");
                    }
                }
            }if(cmd.getName().equalsIgnoreCase("r")){
                if(args.length >= 1){
                    Player player1 = chat.dm.get(player);
                    if(player1 != null){
                        if(Bukkit.getOnlinePlayers().contains(player1)) {
                            String message = "";
                            for (int i = 0; i < args.length; i++) {
                                message = message + args[i] + " ";
                            }
                            message = chat.chengemessage(message, player);
                            player1.sendMessage("§8[§6DM§8][§7By: " + player.getDisplayName() + "§8]§f" + message);
                            player.sendMessage("§8[§6DM§8][§7To: " + player1.getDisplayName() + "§8]§f" + message);
                            chat.dm.put(player, player1);
                            chat.Log(player.getDisplayName() +" To " + player1.getDisplayName() + message);
                        }else{
                            player.sendMessage("指定したプレイヤーは現在いません");
                        }
                    }else{
                        player.sendMessage("前回DMを送ったプレイヤーはいません");
                    }
                }
            }if(cmd.getName().equalsIgnoreCase("notice")){
                if(args.length == 0){
                    player.sendMessage("/notice chenge <nomber>... 通知の表示設定をします\n" +
                            "/notice list ...表示されているお知らせ一覧を表示します\n" +
                            "§7============= §3以下非表示リスト §7============");
                    for(Integer key : chat.taskdata.keySet()){
                        List<String> data = chat.taskdata.get(key);
                        if(data.contains(player.getUniqueId().toString())){
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                                    "tellraw " + player.getDisplayName() + " [{\"text\":\"§7[" + key + "][§eお知らせ§7]§f " + chat.taskmessage.get(key).replace("&","§") + "  \"}" +
                                    ",{\"text\":\"§a§l[True]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/notice set " + player.getDisplayName() + " 1 " + key + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§b表示\"}}" +
                                    ",{\"text\":\"  \"}" +
                                    ",{\"text\":\"§c§l[False]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/notice set " + player.getDisplayName() + " 0 " + key + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§6非表示\"}}]");
                        }
                    }
                    player.sendMessage("§7======================================");
                }else if(args.length == 2){
                    Integer nomber = null;
                    if(args[0].equalsIgnoreCase("chenge")) {
                        try {
                            nomber = Integer.valueOf(args[1]);
                        } catch (Exception er) {
                            System.out.println(er);
                            player.sendMessage("数字を入れてください");
                            return;
                        }
                        if (chat.taskdata.containsKey(nomber)) {
                            if (chat.chengePlayer(player, nomber)) {
                                player.sendMessage("§8[§7System§8] §7表示設定にしました");
                            } else {
                                player.sendMessage("§8[§7System§8] §7非表示設定にしました");
                            }
                        } else {
                            player.sendMessage("そのメッセージはありません");
                        }
                    }
                }else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("list")){
                        player.sendMessage("§7============= §eお知らせ一覧(表示設定) §7============");
                        for(Integer key : chat.taskdata.keySet()){
                            List<String> data = chat.taskdata.get(key);
                            if(data.contains(player.getUniqueId().toString()) == false){
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                                        "tellraw " + player.getDisplayName() + " [{\"text\":\"§7[" + key + "][§eお知らせ§7]§f " + chat.taskmessage.get(key).replace("&","§") + "  \"}" +
                                                ",{\"text\":\"§a§l[True]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/notice set " + player.getDisplayName() + " 1 " + key + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§b表示\"}}" +
                                                ",{\"text\":\"  \"}" +
                                                ",{\"text\":\"§c§l[False]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/notice set " + player.getDisplayName() + " 0 " + key + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§6非表示\"}}]");
                            }
                        }
                        player.sendMessage("§7===========================================");
                    }
                }
            }if(cmd.getName().equalsIgnoreCase("keizaiya")){
                if(args.length == 2){
                    if (args[0].equalsIgnoreCase("help")){
                        Integer num = null;
                        try{
                            num = Integer.valueOf(args[1]);
                        }catch (Exception e){
                            player.sendMessage("数字をいれてください");
                            return;
                        }
                        help.sendhelp(num,player);
                    }
                }if(args.length == 1){
                    help.sendhelp(0,player);
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("notice")){
            if(args.length == 4) {
                if (args[0].equalsIgnoreCase("set")) {
                    Integer nomber = null;
                    Integer number = null;
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player != null) {
                        try {
                            nomber = Integer.valueOf(args[2]);
                        } catch (Exception er) {
                            System.out.println(er);
                            sender.sendMessage("数字を入れてください");
                            return;
                        }
                        try {
                            number = Integer.valueOf(args[3]);
                        } catch (Exception er) {
                            System.out.println(er);
                            player.sendMessage("数字を入れてください");
                            return;
                        }
                        if (chat.taskdata.containsKey(number)) {
                            if (nomber == 1) {
                                chat.setPlayer(player, number, true);
                                player.sendMessage("§8[§7System§8] §7表示設定にしました");
                            } else if (nomber == 0) {
                                chat.setPlayer(player, number, false);
                                player.sendMessage("§8[§7System§8] §7非表示設定にしました");
                            } else {
                                player.sendMessage("範囲外エラー");
                            }
                        } else {
                            player.sendMessage("指定したお知らせはありません");
                        }
                    } else {
                        player.sendMessage("入力方法が間違っています");
                    }
                }
            }
        }
    }
}
