package keizaiya.second.file.Admin;

import keizaiya.second.Potato;
import keizaiya.second.armmy.armmy;
import keizaiya.second.chat.chengewoad;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.country.ideology;
import keizaiya.second.file.country.item;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

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
                            "/Admin item <Displayname> <Lore1> <Lore2>... アイテムを編集します\n" +
                            "/Admin word\n" +
                            "/Admin remove <Player>...指定したプレイヤーをAdminから消去します" +
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
                            }if(args[1].equalsIgnoreCase("addpoint")){
                                if(args.length == 4) {
                                    if(Potato.countrylist.containsKey(args[3])) {
                                        Integer amont = 0;
                                        try {
                                            amont = Integer.parseInt(args[2]);
                                            Countrydata.setCountrypoint(args[3], Countrydata.getCountrypoint(args[3]) + amont);
                                            player.sendMessage("設定しました。");
                                        } catch (Exception e) {
                                            player.sendMessage("§8[§7System§8] §7数字をいれてください");
                                        }
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("addmember")){
                                if(args.length == 4) {
                                    if(Potato.countrylist.containsKey(args[3])) {
                                        Player P = Bukkit.getPlayer(args[2]);
                                        if(P != null){
                                            if(Countrydata.checkmember(args[3],P) == false) {
                                                if(Playerdata.getakiCountry(P) != 666) {
                                                    Countrydata.addmember(args[3], P);
                                                    Playerdata.setCountrytag(P,Playerdata.getakiCountry(P),args[3]);
                                                    player.sendMessage("§8[§7System§8] §7設定しました");
                                                }else{
                                                    player.sendMessage("§8[§7System§8] §7変更できる国がありません");
                                                }
                                            }else{
                                                player.sendMessage("§8[§7System§8] §7すでに参加しています");
                                            }
                                        }else{
                                            player.sendMessage("§8[§7System§8] §7指定したプレイヤーはいません");
                                        }
                                    }else{
                                        player.sendMessage("§8[§7System§8] §7指定した国家はありません");
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("removemember")){
                                if(args.length == 4) {
                                    if(Potato.countrylist.containsKey(args[3])) {
                                        Player P = Bukkit.getPlayer(args[2]);
                                        if(P != null){
                                            if(Countrydata.checkmember(args[3],P)) {
                                                if(Countrydata.checkpermissioncountry(P,"head",args[3]) == false) {
                                                    Countrydata.deleatmember(args[3], P);
                                                    Playerdata.deleatcountry(P,args[3]);
                                                    player.sendMessage("§8[§7System§8] §7設定しました");
                                                }else{
                                                    player.sendMessage("§8[§7System§8] §7国家元首なので離脱させれません");
                                                }
                                            }else{
                                                player.sendMessage("§8[§7System§8] §7メンバーではありません");
                                            }
                                        }else{
                                            player.sendMessage("§8[§7System§8] §7指定したプレイヤーはいません");
                                        }
                                    }else{
                                        player.sendMessage("§8[§7System§8] §7指定した国家はありません");
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("sethead")){
                                if(args.length == 4) {
                                    if(Potato.countrylist.containsKey(args[3])) {
                                        Player P = Bukkit.getPlayer(args[2]);
                                        if(P != null){
                                            if(Playerdata.getakiCountry(P) != 666) {
                                                if(Countrydata.checkmember(args[3],P) == false) {
                                                    Countrydata.addmember(args[3], P);
                                                    Playerdata.setCountrytag(P,Playerdata.getakiCountry(P),args[3]);
                                                }
                                                Countrydata.chengehead(P,args[3]);
                                                player.sendMessage("§8[§7System§8] §7設定しました");
                                            }else{
                                                if(Countrydata.checkmember(args[3],P) == false) {
                                                    Countrydata.addmember(args[3], P);
                                                    Playerdata.setCountrytag(P,3,args[3]);
                                                }
                                                Countrydata.chengehead(P,args[3]);
                                                player.sendMessage("§8[§7System§8] §7設定しました(3)");
                                            }
                                        }else{
                                            player.sendMessage("§8[§7System§8] §7指定したプレイヤーはいません");
                                        }
                                    }else{
                                        player.sendMessage("§8[§7System§8] §7指定した国家はありません");
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("getcard")){
                                if(args.length == 3){
                                    player.getInventory().addItem(armmy.getarmmycard(args[2]));
                                }
                            }
                        }if(args.length == 1){
                            player.sendMessage("/Admin Country info <Countrytag>...指定された国のInfoを開きます\n" +
                                    "/Admin Country list...現在ある国のCountrytaglistを表示します\n" +
                                    "/Admin Country list all...削除された国を含むCountrytaglistを表示します\n" +
                                    "/Admin Country SetPoint <Point> <Countrytag>...国の国家ポイントを設定します\n" +
                                    "/Admin Country AddPoint <Point> <Countrytag>...国の国家ポイントを追加します\n" +
                                    "/Admin Country getPoint <Countrytag>...国の国家ポイントを表示します\n" +
                                    "/Admin Country addmember <Player> <Countrytag>...国家にプレイヤーを追加します\n" +
                                    "/Admin Country removemember <Player> <Countrytag>...国家のプレイヤーを離脱させます\n" +
                                    "/Admin Country sethead <Player> <Countrytag>...プレイヤーを指定した国家の国家元首に設定します");
                        }
                    }if(args[0].equalsIgnoreCase("item")) {
                        if(args.length >= 2){
                            if(player.getInventory().getItemInMainHand().getType() != Material.AIR){
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                meta.setDisplayName(args[1].replace("&","§"));
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                            }else{
                                player.sendMessage("§8[§7System§8] §7アイテムがないです");
                            }
                        }if(args.length >= 3){
                            List<String> lore = new ArrayList<>();
                            if(player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                                for (int i = 2; i <= args.length - 1; i++) {
                                    lore.add(args[i].replace("&", "§"));
                                }
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                meta.setLore(lore);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                            }

                        }
                    }if(args[0].equalsIgnoreCase("word")) {
                        if(args.length >= 2){
                            if(args[1].equalsIgnoreCase("add")){
                                if(args.length == 4){
                                    chengewoad.setword(args[2],args[3]);
                                    player.sendMessage("追加しました");
                                }
                            }else if(args[1].equalsIgnoreCase("list")){
                                player.sendMessage(chengewoad.getlist());
                            }else if(args[1].equalsIgnoreCase("remove")){
                                if(args.length == 3) {
                                    if(chengewoad.checkword(args[2])) {
                                        chengewoad.removeword(args[2]);
                                        player.sendMessage("消去しました");
                                    }else{
                                        player.sendMessage("その言葉は設定されてません");
                                    }
                                }
                            }
                        } else if(args.length == 1){
                            player.sendMessage("/admin word add <Before> <After>...変換する文字を設定します\n" +
                                    "/admin word remove <Before>...設定されている文字を消去します\n" +
                                    "/admin word list...一覧をだします");
                        }
                    }if(args[0].equalsIgnoreCase("remove")) {
                        if(args.length == 2){
                            Player player2 = Bukkit.getPlayer(args[1]);
                            if(player2 != null){
                                if(adminfile.checkmember(player2)) {
                                    adminfile.removemember(player2);
                                    player.sendMessage("プレイヤーをリストから削除しました");
                                }else{
                                    player.sendMessage("そのプレイヤーはAdminではありません");
                                }
                            }else{
                                player.sendMessage("そのプレイヤー名のプレイヤーはいません");
                            }
                        }
                    }
                }
            }
        }
    }
}
