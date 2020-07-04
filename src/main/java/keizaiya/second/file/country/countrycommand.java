package keizaiya.second.file.country;

import keizaiya.second.Potato;
import keizaiya.second.author.playertelepoat;
import keizaiya.second.file.Admin.adminfile;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.List;


public class countrycommand {
    public static void onCountrycommnand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("country")){
                if(args.length == 0){
                    player.sendMessage("§8=*=*=*= §7Help =*=*=*=\n" +
                            "§f以下のコマンド入力で詳細を開く\n \n" +
                            "/Country Create\n" +
                            "/Country Invite\n" +
                            "/Country Chat\n" +
                            "/Country permission\n" +
                            "/Country TP\n" +
                            "/Country Item\n" +
                            "/Country Remove\n" +
                            "/Country Promote\n" +
                            "/Country Delete\n" +
                            "/Country Nickname\n" +
                            "=*=*=*=*=*=*=*=*=*=*=");
                }if(args.length > 0){
                    if(args[0].equalsIgnoreCase("create")){
                        if(args.length == 3) {
                            if (Countrydata.getTAG(args[1]) == null) {
                                Countrydata.CreateCountry(player, args[1],args[2]);
                            }
                        }if(args.length == 1){
                        player.sendMessage("§8[§7System§8] §7/Country Create <国名> <Nickname>...国を作ります");
                        }else if(args.length == 2){
                            player.sendMessage("§8[§7System§8] §7Nicknameが設定されていません");
                        }
                    }if(args[0].equalsIgnoreCase("invite")){
                        if(args.length == 2) {
                            if (Potato.plugin.getServer().getOnlinePlayers().contains(Potato.plugin.getServer().getPlayer(args[1]))) {
                                if (Countrydata.checkpermission(player, "invite")) {
                                    Player player2 = Potato.plugin.getServer().getPlayer(args[1]);
                                    countryauthor.InvitePlayer(player, player2, Playerdata.getNowCountry(player));
                                }
                            }
                        }else if(args.length == 1){
                            player.sendMessage("§8[§7System§8] §7/Country Invite <Player>...プレイヤーを招待します");
                        }
                    }if(args[0].equalsIgnoreCase("INconsent")){
                        if(args.length == 1){
                            if(countryauthor.invitecheck(player)) {
                                countryauthor.inviteok(player);
                            }else{
                                player.sendMessage("§8[§7System§8] §7あなたは招待されていません。");
                            }
                        }
                    }if(args[0].equalsIgnoreCase("remove")){
                        if(args.length == 1){
                            player.sendMessage("§8[§7System§8] §7/Country Remove <Player>...プレイヤーを追放、又は脱退します");
                        }if(args.length == 2){
                            Player player2 = null;
                            try{
                                player2 = Bukkit.getServer().getPlayer(args[1]);
                            }catch (Exception e){
                                player.sendMessage("§8[§7System§8] §7指定されたプレイヤーはいません");
                            }
                            if(player2 != null) {
                                countryauthor.removecountry(player, player2, Playerdata.getNowCountry(player));
                            }
                        }
                    }if(args[0].equalsIgnoreCase("REconsent")){
                        if(args.length == 2){
                            Player player2 = Potato.plugin.getServer().getPlayer(args[1]);
                            if(countryauthor.removecheck(player2)) {
                                countryauthor.removechengeoklist(player2);
                            }else{
                                player.sendMessage("§8[§7System§8] §7あなたは離脱処理がされておりません");
                            }
                        }
                    }if(args[0].equalsIgnoreCase("tp")){
                        if(args.length == 2){
                            if(args[1].equalsIgnoreCase("set")){
                                if(Countrydata.checkpermission(player,"tp")){
                                    Countrydata.setCountryTP(player.getLocation(),Playerdata.getNowCountry(player));
                                    player.sendMessage("§8[§7System§8] §7TeleportPointを設定しました。");
                                }else{
                                    player.sendMessage("§8[§7System§8] §7あなたには権限がありません。");
                                }
                            }if(args[1].equalsIgnoreCase("1")){
                                playertelepoat.telepoatPlayer(player,0);
                            }if(args[1].equalsIgnoreCase("2")){
                                playertelepoat.telepoatPlayer(player,1);
                            }
                        }else{
                            player.sendMessage("§8[§7System§8] §7/Country Tp Set...自国のTp地点を登録します");
                            player.sendMessage("§8[§7System§8] §7/Country Tp 1...世界首都" + adminfile.getTPname() + "にTpします");
                            player.sendMessage("§8[§7System§8] §7/Country Tp 2...自国にTpします");
                        }
                    }if(args[0].equalsIgnoreCase("item")){
                        if(args.length >= 2){
                            if(args[1].equalsIgnoreCase("card")){
                                if(Countrydata.checkpermission(player,"item")){
                                    Integer amont = 1;
                                    if(args.length == 3){
                                        try { amont = Integer.parseInt(args[2]);
                                        } catch (Exception e) {
                                            player.sendMessage("§8[§7System§8] §7次回からは数字いれてね^^");
                                        }
                                        System.out.println(amont);
                                    }
                                    if(Countrydata.getCountrypoint(Playerdata.getNowCountry(player))-amont >= 0){
                                        ItemStack stack = item.getIdeorogycard(Countrydata.getIdeology(Playerdata.getNowCountry(player)));
                                        stack.setAmount(amont);
                                        player.getInventory().addItem(stack);
                                        Countrydata.removepoint(Playerdata.getNowCountry(player),amont);
                                    }else{player.sendMessage("§8[§7System§8] §7ポイントが足りません");}
                                }
                            }if(args[1].equalsIgnoreCase("country")){
                                if(Countrydata.checkpermission(player,"item")){
                                    Integer amont = 1;
                                    if(args.length == 3){
                                        try { amont = Integer.parseInt(args[2]);
                                        } catch (Exception e) {
                                            player.sendMessage("§8[§7System§8] §7次回からは数字いれてね^^");
                                        }
                                        System.out.println(amont);
                                    }
                                    if(Countrydata.getCountrypoint(Playerdata.getNowCountry(player))-amont >= 0){
                                        ItemStack stack = item.getcard();
                                        stack.setAmount(amont);
                                        player.getInventory().addItem(stack);
                                        Countrydata.removepoint(Playerdata.getNowCountry(player),amont);
                                    }else{player.sendMessage("§8[§7System§8] §7ポイントが足りません");}
                                }
                            }if(args[1].equalsIgnoreCase("banner")){
                                if(player.getInventory().getItemInMainHand().getItemMeta() instanceof BannerMeta){
                                    if(player.getInventory().getItemInMainHand().getAmount() > 64){
                                        player.sendMessage("§8[§7System§8] §7これ以上は増やせません。");
                                    }else {
                                        if(Countrydata.getCountrypoint(Playerdata.getNowCountry(player))-1 >= 0) {
                                            player.getInventory().getItemInMainHand().setAmount(
                                                    player.getInventory().getItemInMainHand().getAmount() + 16);
                                            Countrydata.removepoint(Playerdata.getNowCountry(player),1);
                                        }else{
                                            player.sendMessage("§8[§7System§8] §7ポイントが足りません");
                                        }
                                    }
                                }
                            }
                        }else if(args.length == 1){
                            player.sendMessage("§8[§7System§8] §7/Country Item Card <number>...証(Ideology)を手に入れます(1Point消費)");
                            player.sendMessage("§8[§7System§8] §7/Country Item Country <number>...証(Country)を手に入れます(1Point消費)");
                            player.sendMessage("§8[§7System§8] §7/Country Item Banner...手にある旗を16個増やします(1Point消費)");
                        }
                    }if(args[0].equalsIgnoreCase("delete")){
                        if(Countrydata.checkpermission(player,"head")){
                            if(args.length == 2){
                                if(args[1].equalsIgnoreCase("confirm")) {
                                    Countrydata.deleatcountry(Playerdata.getNowCountry(player), player);
                                    player.sendMessage("§8[§7System§8] §7国を削除しました");
                                }
                            }else{
                                player.sendMessage("§8[§7System§8] §f" + Countrydata.getCountryName(Playerdata.getNowCountry(player)) +
                                        "§7を本当に国を削除しますか？§7(§c§lこの動作は取り消せません!§8)");
                                player.sendMessage("§8[§7System§8] §7削除する場合は以下のコマンド実行してください");
                                player.sendMessage("§8[§7System§8] §7/Country Delete Confirm");
                            }
                        }
                    }if(args[0].equalsIgnoreCase("permission")){
                        if(args.length == 3){
                            if(Countrydata.checkpermission(player,"permission")) {
                                Player player2 = Bukkit.getPlayer(args[2]);
                                if (player2 != null) {
                                    if (Countrydata.getOnlinemember(Playerdata.getNowCountry(player)).contains(player2)) {
                                        if (args[1].equalsIgnoreCase("show")) {
                                            List<String> perm = Countrydata.getpermissionList(player2,Playerdata.getNowCountry(player));
                                            String data = "";
                                            for(String yeah : perm){
                                                if(yeah.contains("member") == false) {
                                                    data = data + "," + yeah;
                                                }
                                            }
                                            if(data == ""){ data = "null";}
                                            player.sendMessage("§8[§7System§8] §7Permission: " + data);
                                        }if (args[1].equalsIgnoreCase("invite")) {
                                            Countrydata.setpermisson(player2,"invite",Playerdata.getNowCountry(player));
                                        }if (args[1].equalsIgnoreCase("chat")) {
                                            Countrydata.setpermisson(player2,"chat",Playerdata.getNowCountry(player));
                                        }if (args[1].equalsIgnoreCase("permission")) {
                                            Countrydata.setpermisson(player2,"permission",Playerdata.getNowCountry(player));
                                        }if (args[1].equalsIgnoreCase("item")) {
                                            Countrydata.setpermisson(player2,"item",Playerdata.getNowCountry(player));
                                        }if (args[1].equalsIgnoreCase("tp")) {
                                            Countrydata.setpermisson(player2,"tp",Playerdata.getNowCountry(player));
                                        }if (args[1].equalsIgnoreCase("remove")) {
                                            Countrydata.setpermisson(player2,"remove",Playerdata.getNowCountry(player));
                                        }
                                    }
                                }
                            }else{
                                player.sendMessage("§8[§7System§8] §8プレイヤーがいません。");
                            }
                        }else if (args.length == 1){
                            player.sendMessage("§8[§7System§8] §7/Country permission <Show/Permission>...権限を設定します");
                            player.sendMessage("§8[§7System§8] §7Permission: Invite,Chat,Permission,Item,Tp,Remove");
                        }
                    }if(args[0].equalsIgnoreCase("promote")){
                        if(args.length >= 2) {
                            if (Countrydata.checkpermission(player,"head")) {
                                Player player2 = null;
                                try {
                                    player2 = Bukkit.getPlayer(args[1]);
                                } catch (Exception e) {
                                }
                                if (player2 != null) {
                                    if (Countrydata.getOnlinemember(Playerdata.getNowCountry(player)).contains(player2)) {
                                        if (args.length == 3) {
                                            if (args[2].equalsIgnoreCase("confirm")) {
                                                Countrydata.chengehead(player2, Playerdata.getNowCountry(player));
                                                player2.sendMessage("§8[§7System§8] §7国家元首になりました");
                                                player.sendMessage("§8[§7System§8] §7国家元首を交代しました");
                                            }
                                        }
                                        if (args.length == 2) {
                                            player.sendMessage("§8[§7System§8] §f" + args[1] + "§7を国家元首にしますか");
                                            player.sendMessage("§8[§7System§8] §7交代する場合は以下のコマンド実行してください");
                                            player.sendMessage("§8[§7System§8] §7/Country promote <Player> Confirm");
                                        }
                                    }
                                }
                            }
                        }
                    }if(args[0].equalsIgnoreCase("info")){
                        if(args.length == 1){
                            player.sendMessage(Countrydata.getCountryInfo(Playerdata.getNowCountry(player)));
                        }
                    }if(args[0].equalsIgnoreCase("nickname")){
                        if(args.length == 1){
                            player.sendMessage("§8[§7System§8] §7/Country nickname <Countryname>...チャットに表示する国名を設定します(最大8文字)");
                        }else if(args.length == 2){
                            if(Countrydata.checkpermission(player,"head")) {
                                System.out.println(args[1]);
                                Countrydata.setnickname(Playerdata.getNowCountry(player), args[1]);
                                player.sendMessage("§8[§7System§8] §7チャットに表示する国名を§f" + Countrydata.getnickname(Playerdata.getNowCountry(player)) + "§7にしました");
                            }else{
                                player.sendMessage("§8[§7System§8] §7あなたには権限がありません");
                            }
                        }
                    }if(args[0].equalsIgnoreCase("chat")){
                        if(args.length == 3){
                            if(Countrydata.checkpermission(player,"chat")) {
                                Player player2 = null;
                                try {
                                    player2 = Bukkit.getPlayer(args[2]);
                                } catch (Exception e) {
                                }
                                if (player2 == null) {
                                    player.sendMessage("§8[§7System§8] §7指定したプレイヤーはいません");
                                } else {
                                    Countrydata.setrole(player2, args[1], Playerdata.getNowCountry(player));
                                    player.sendMessage("§8[§7System§8] §7設定しました");
                                }
                            }
                        }else if(args.length == 1){
                            player.sendMessage("§8[§7System§8] §7/Country chat <役職名> <Player>...国内チャットのPrefixを変更します");
                        }
                    }
                }
            }
        }
    }
}

