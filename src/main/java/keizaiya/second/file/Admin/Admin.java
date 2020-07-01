package keizaiya.second.file.Admin;

import keizaiya.second.Potato;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.country.ideology;
import keizaiya.second.file.country.item;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    public static void onAdmincommnand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("admin")){
                adminfile.addmember(player);
                if(args.length == 0){
                    player.sendMessage("-----AdminHelp-----\n" +
                            "説明がないものはコマンド入力で詳細を開く\n \n" +
                            "/Admin SetTp...テレポート地点を設定します\n" +
                            "/Admin SetName...テレポート地点の名前を設定します\n" +
                            "/Admin ideology\n" +
                            "/Admin card\n" +
                            "/Admin ^^ アイテムを1sにします\n" +
                            "/Admin Country\n" +
                            "/Chatmode admin チャットモードをAdminにします");
                }if(args.length > 0){
                    if(args[0].equalsIgnoreCase("settp")){
                        if(args.length == 2) {
                        }if(args.length == 1){
                            adminfile.setMainTP(player.getLocation());
                            player.sendMessage("テレポート地点を設定しました。");
                        }
                    }if(args[0].equalsIgnoreCase("setName")){
                        if(args.length == 2) {
                            adminfile.setTPname(args[1]);
                            player.sendMessage("名前を" + args[1] +"に設定しました");
                        }

                    }if(args[0].equalsIgnoreCase("ideology")){
                        if(args.length == 3){
                            if(args[1].equalsIgnoreCase("getname")){
                                player.sendMessage(ideology.getideologyname(args[2]));
                            }if(args[1].equalsIgnoreCase("getcard")){
                                player.getInventory().addItem(ideology.getchengcard(args[2]));
                            }
                        }if(args.length == 2){
                            if(args[1].equalsIgnoreCase("list")){
                                player.sendMessage(ideology.getIdeologyinfo());
                            }
                        } else if (args.length == 1){
                            player.sendMessage("/Admin ideology getname <Ideologytag>...指定されたキーに割り当てられてる名前を表示します\n" +
                                    "/Admin ideology getcard <Ideologytag>...指定されたキーの変更カードをだします\n" +
                                    "/Admin ideology list...キー一覧");
                        }
                    }if(args[0].equalsIgnoreCase("card")){
                        if(args.length >= 2){
                            if(args[1].equalsIgnoreCase("country")){
                                Integer amont = 1;
                                if(args.length == 3){
                                    try { amont = Integer.parseInt(args[2]);
                                    } catch (Exception e) {
                                        player.sendMessage("§8[§7System§8] §7次回からは数字いれてね^^");
                                    }
                                }
                                ItemStack stack = item.getcard();
                                stack.setAmount(amont);
                                player.getInventory().addItem(stack);
                            }if(args[1].equalsIgnoreCase("ideology")){
                                Integer amont = 1;
                                if(args.length >= 3){
                                    if(args.length == 4){
                                        try { amont = Integer.parseInt(args[3]);
                                        } catch (Exception e) {
                                            player.sendMessage("§8[§7System§8] §7次回からは数字いれてね^^");
                                        }
                                    }
                                    ItemStack stack = item.getIdeorogycard(args[2]);
                                    stack.setAmount(amont);
                                    player.getInventory().addItem(stack);
                                }
                            }
                        }else if(args.length == 1){
                            player.sendMessage("/Admin card country <number>...証(Countryをだします)\n" +
                                    "/Admin card ideology <Ideologytag> <number>...証(Ideology)をだします");
                        }
                    }if(args[0].equalsIgnoreCase("^^")){
                        player.getInventory().getItemInMainHand().setAmount(64);
                    }if(args[0].equalsIgnoreCase("villager")) {
                        ItemStack stack = new ItemStack(Material.PAPER);
                        ItemMeta meta = stack.getItemMeta();
                        meta.setDisplayName("§c§lVillager Ticket");
                        List<String> lore = new ArrayList<>();
                        lore.add("右クリックを押すとスポーンします");
                        meta.setLore(lore);
                        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "summon"), PersistentDataType.STRING, "villager");
                        stack.setItemMeta(meta);
                        player.getInventory().addItem(stack);
                    }if(args[0].equalsIgnoreCase("country")){
                        Countrydata.updateCountrylist();
                        if(args.length >= 2){
                            if(args[1].equalsIgnoreCase("list")){
                                if(args.length == 3){
                                    if(args[2].equalsIgnoreCase("all")) {
                                        List<String> list = Countrydata.getAllContrytag();
                                        int i = 0;
                                        String message = "";
                                        for(String name: list){
                                            if(i >= 2){
                                                message = message + "\n";
                                                i = 0;
                                            }
                                            message = message + "," + name;
                                            i++;
                                        }
                                        player.sendMessage(message);
                                    }
                                }else{
                                    List<String> list = Countrydata.getContrytag();
                                    int i = 0;
                                    String message = "";
                                    for(String name: list){
                                        if(i >= 2){
                                            message = message + "\n";
                                            i = 0;
                                        }
                                        message = message + "," + name;
                                        i++;
                                    }
                                    player.sendMessage(message);
                                }
                            }if(args[1].equalsIgnoreCase("info")){
                                if(args.length == 3){
                                    if(Potato.countrylist.containsKey(args[2])) {
                                        player.sendMessage(Countrydata.getCountryInfo(args[2]));
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("setpoint")){
                                if(args.length == 4) {
                                    if(Potato.countrylist.containsKey(args[3])) {
                                        Integer amont = 0;
                                        try {
                                            amont = Integer.parseInt(args[2]);
                                            Countrydata.setCountrypoint(args[3], amont);
                                            player.sendMessage("設定しました。");
                                        } catch (Exception e) {
                                            player.sendMessage("§8[§7System§8] §7数字をいれてください");
                                        }
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("getpoint")){
                                if(args.length == 3){
                                    if(Potato.countrylist.containsKey(args[2])) {
                                        player.sendMessage(String.valueOf(Countrydata.getCountrypoint(args[2])));
                                    }
                                }
                            }
                        }if(args.length == 1){
                            player.sendMessage("/Admin Country info <Countrytag>...指定された国のInfoを開きます\n" +
                                    "/Admin Country list...現在ある国のCountrytaglistを表示します\n" +
                                    "/Admin Country list all...削除された国を含むCountrytaglistを表示します\n" +
                                    "/Admin Country SetPoint <Point> <Countrytag>...国の国家ポイントを設定します\n" +
                                    "/Admin Country getPoint <Countrytag>...国の国家ポイントを表示します");
                        }
                    }
                }
            }
        }
    }
}
