package keizaiya.second.file.country;

import keizaiya.second.Potato;
import keizaiya.second.author.playertelepoat;
import keizaiya.second.chat.chatsiliarize;
import keizaiya.second.file.player.Playerdata;
import keizaiya.second.inventory.help;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.persistence.PersistentDataType;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.List;


public class countrycommand {
    public static void onCountrycommnand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("country")){
                if(args.length == 0){
                    help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                }if(args.length > 0){
                    if(args[0].equalsIgnoreCase("create")){
                        if(args.length == 3) {
                            if (Countrydata.getTAG(args[1]) == null) {
                                Countrydata.CreateCountry(player, args[1],args[2]);
                            }
                        }if(args.length == 1){
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
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
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                        }

                    }if(args[0].equalsIgnoreCase("INconsent")){
                        if(args.length == 1){
                            if(countryauthor.invitecheck(player)) {
                                countryauthor.inviteok(player);
                            }else{
                                player.sendMessage("§8[§7System§8] §7あなたは招待されていません。");
                            }
                        }if(args.length == 2){
                            if(args[1].equalsIgnoreCase("cancel")){
                                if(countryauthor.invitecheck(player)) {
                                    countryauthor.invitecansell(player);
                                }else{
                                    player.sendMessage("§8[§7System§8] §7あなたは招待されていません。");
                                }
                            }
                        }

                    }if(args[0].equalsIgnoreCase("remove")){
                        if(args.length == 1){
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
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
                        }if(args.length == 3){
                            if(args[2].equalsIgnoreCase("cancel")) {
                                System.out.println("ok");
                                Player player2 = Potato.plugin.getServer().getPlayer(args[1]);
                                if (countryauthor.removecheck(player2)) {
                                    countryauthor.removechengeokcl(player2);
                                } else {
                                    player.sendMessage("§8[§7System§8] §7あなたは離脱処理がされておりません");
                                }
                            }
                        }

                    }if(args[0].equalsIgnoreCase("tp")){
                        if(args.length >= 2){
                            if(args[1].equalsIgnoreCase("set")){
                                if(args.length == 3) {
                                    if (Countrydata.checkpermission(player, "tp")) {
                                        Integer i = null;
                                        try {
                                            i = Integer.valueOf(args[2]);
                                        } catch (Exception es) {
                                            player.sendMessage("§8[§7System§8] §7数字を入れなければなりません");
                                        }
                                        if (i != null) {
                                            if (i <= Potato.config.getInt("TPmax", 1)) {
                                                Integer TPsize = Countrydata.getTPsize(Playerdata.getNowCountry(player));
                                                if(TPsize == null){ TPsize = 0;}
                                                if(TPsize >= i) {
                                                    Countrydata.setCountryTP(player.getLocation(), Playerdata.getNowCountry(player), i);
                                                    player.sendMessage("§8[§7System§8] §7TeleportPointを設定しました。");
                                                }else{
                                                    player.sendMessage("§8[§7System§8] §7範囲外の数字です");
                                                }
                                            }else{
                                                player.sendMessage("§8[§7System§8] §7範囲外の数字です");
                                            }
                                        }else {
                                            player.sendMessage("§8[§7System§8] §7値がNullです");
                                        }
                                    } else {
                                        player.sendMessage("§8[§7System§8] §7あなたには権限がありません。");
                                    }
                                }
                            }if(args[1].equalsIgnoreCase("1")){
                                playertelepoat.telepoatPlayer(player,0);
                            }if(args[1].equalsIgnoreCase("2")){
                                playertelepoat.telepoatPlayer(player,1);
                            }if(args[1].equalsIgnoreCase("3")){
                                playertelepoat.telepoatPlayer(player,2);
                            }
                        }else{
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
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
                            }if(args[1].equalsIgnoreCase("banner")) {
                                if (args.length == 2) {
                                    if (player.getInventory().getItemInMainHand().getItemMeta() instanceof BannerMeta) {
                                        if (player.getInventory().getItemInMainHand().getAmount() > 64) {
                                            player.sendMessage("§8[§7System§8] §7これ以上は増やせません。");
                                        } else {
                                            if (Countrydata.getCountrypoint(Playerdata.getNowCountry(player)) - 1 >= 0) {
                                                player.getInventory().getItemInMainHand().setAmount(
                                                        player.getInventory().getItemInMainHand().getAmount() + 16);
                                                Countrydata.removepoint(Playerdata.getNowCountry(player), 1);
                                            } else {
                                                player.sendMessage("§8[§7System§8] §7ポイントが足りません");
                                            }
                                        }
                                    } else {
                                        player.sendMessage("§8[§7System§8] §7旗を持ってください");
                                    }
                                } else if (args.length == 3) {
                                    if (args[2].equalsIgnoreCase("set")) {
                                        if (player.getInventory().getItemInMainHand().getItemMeta() instanceof BannerMeta) {
                                            if(Countrydata.checkpermission(player,"item")) {
                                                Countrydata.setBanner(Playerdata.getNowCountry(player), player.getInventory().getItemInMainHand());
                                                player.sendMessage("§8[§7System§8] §7設定しました");
                                            }else {
                                                player.sendMessage("§8[§7System§8] §7あなたには権限がありません");
                                            }
                                        } else {
                                            player.sendMessage("§8[§7System§8] §7旗を持ってください");
                                        }
                                    }else if(args[2].equalsIgnoreCase("get")){
                                        if(Countrydata.checkpermission(player,"item")) {
                                            if (Countrydata.getCountrypoint(Playerdata.getNowCountry(player)) - 1 >= 0) {
                                                ItemStack stack = Countrydata.getBanner(Playerdata.getNowCountry(player));
                                                if(stack != null) {
                                                    stack.setAmount(8);
                                                    player.getInventory().addItem(stack);
                                                    Countrydata.removepoint(Playerdata.getNowCountry(player), 1);
                                                }else{
                                                    player.sendMessage("§8[§7System§8] §7国旗が設定されていません");
                                                }
                                            }else {
                                                player.sendMessage("§8[§7System§8] §7ポイントが足りません");
                                            }
                                        }else {
                                            player.sendMessage("§8[§7System§8] §7あなたには権限がありません");
                                        }
                                    }
                                }
                            }
                        }else if(args.length == 1){
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
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
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                        }

                    }if(args[0].equalsIgnoreCase("promote")){
                        if(args.length >= 2) {
                            if (Countrydata.checkpermission(player,"head")) {
                                Player player2 = null;
                                try {
                                    player2 = Bukkit.getPlayer(args[1]);
                                    System.out.println(player2);
                                } catch (Exception e) {
                                }
                                if (player2 != null) {
                                    if (Countrydata.getOnlinemember(Playerdata.getNowCountry(player)).contains(player2)) {
                                        System.out.println("yeah");
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
                                    }else{player.sendMessage("§8[§7System§8] §7指定されたプレイヤー国に参加していません");}
                                }else{
                                    player.sendMessage("§8[§7System§8] §7指定されたプレイヤーはいません");
                                }
                            }else{
                                player.sendMessage("§8[§7System§8] §7権限が足りません");
                            }
                        }else if(args.length == 1){
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                        }

                    }if(args[0].equalsIgnoreCase("info")){
                        if(args.length == 1){
                            player.sendMessage(Countrydata.getCountryInfo(Playerdata.getNowCountry(player)));
                        }

                    }if(args[0].equalsIgnoreCase("nickname")){
                        if(args.length == 1){
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                        }else if(args.length == 2){
                            if(Countrydata.checkpermission(player,"head")) {
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
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                        }

                    }if(args[0].equalsIgnoreCase("religion")){
                        if(args.length ==1){
                            help.sendtellraw(player,help.gettellrawtocommand("詳細はヘルプで確認できます。(Clickhere)","/keizaiya help 1"));
                        }else if(args.length == 2){
                            if(Countrydata.checkpermission(player,"head")){
                                if(player.getInventory().getItemInMainHand() != null) {
                                    String name = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "religion")
                                            , PersistentDataType.STRING);
                                    if (name != null) {
                                        Countrydata.setreligion(Playerdata.getNowCountry(player), args[1]);
                                        player.sendMessage("§8[§7System§8] §7宗教を設定しました");
                                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                                    }else{
                                        player.sendMessage("変更カードを手に持ってください");
                                    }
                                }
                            }else{
                                player.sendMessage("§8[§7System§8] §7あなたには権限がありません");
                            }
                        }
                    }if(args[0].equalsIgnoreCase("repuest")){
                        if(Countrydata.checkpermission(player,"invite")){
                            String tag = Playerdata.getNowCountry(player);
                            if(Countrydata.repuestlist.containsKey(tag)){
                                List<Player> data = Countrydata.repuestlist.get(tag);
                                player.sendMessage("リクエストリスト");
                                for(Player player1 : data){
                                    chatsiliarize.sendrepuest(player,player1);
                                }
                                player.sendMessage("=======================");
                            }else{
                                player.sendMessage("リクエストされてません。");
                            }
                        }else{
                            if(args.length == 2){
                                String name = Countrydata.getTAG(args[1]);
                                if(name != null){
                                    if(Countrydata.getOnlinemember(name).contains(player) == false){
                                        Countrydata.addrepuest(name,player);
                                        player.sendMessage("リクエストしました。");
                                    }else{
                                        player.sendMessage("すでに国に入っています。");
                                    }
                                }else{
                                    player.sendMessage("指定した国がありません。");
                                }
                            }
                        }
                    }if(args[0].equalsIgnoreCase("repuestok")){
                        if(args.length >= 2){
                            if(Countrydata.checkpermission(player,"invite")) {
                                String tag = Playerdata.getNowCountry(player);
                                if (Countrydata.repuestlist.containsKey(tag)) {
                                    List<Player> data = Countrydata.repuestlist.get(tag);
                                    Player player2 = null;
                                    for (Player player1 : data) {
                                        if(player1.getName().contains(args[1])){
                                            player2 = player1;
                                        }
                                    }
                                    if(player2 != null){
                                        if(args.length == 3){
                                            Countrydata.removerepuest(tag,player2);
                                            player.sendMessage(player2.getDisplayName() + " リクエストを拒否しました。");
                                        }else{
                                            Boolean ok = true;
                                            for(int i = 1; i<=3;i++){
                                                if(Playerdata.getCountrytag(player2,i).contains("null")){
                                                    Countrydata.addmember(tag,player2);
                                                    Countrydata.removerepuest(tag,player2);
                                                    Playerdata.setCountrytag(player2,i,tag);
                                                    player.sendMessage(player2.getDisplayName() + " を参加させました。");
                                                    ok = false;
                                                    break;
                                                }
                                            }
                                            if(ok){
                                                player.sendMessage(player2.getDisplayName() + "の空いているアカウントがないので参加させりません");
                                            }
                                        }
                                    }
                                }
                            }else {
                                player.sendMessage("権限がありません。");
                            }
                        }
                    }
                }
            }
        }
    }
}

