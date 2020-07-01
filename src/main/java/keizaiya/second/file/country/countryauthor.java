package keizaiya.second.file.country;

import keizaiya.second.Potato;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.List;

public class countryauthor {
    public static List<Player> inviteok = new ArrayList<>();
    public static List<Player> invitelist = new ArrayList<>();
    public static void InvitePlayer(Player sender,Player player , String Countrytag){
        if(Countrydata.checkpermission(sender,"invite")) {
            if (invitelist.contains(player) == false) {

                invitelist.add(player);
                new BukkitRunnable() {
                    Player sender2 = sender;
                    Player player2 = player;
                    String Country = Countrytag;
                    Integer now = 0;
                    Integer endtime = Potato.config.getInt("invitetime", 1200);

                    @Override
                    public void run() {
                        if (now == 0) {
                            player2.sendMessage("§8[§7System§8] §7" + Countrydata.getCountryName(Country) + "から招待が届きました。");
                            player2.sendMessage("§8[§7System§8] §7参加する場合は以下のコマンドを実行してください。");
                            player2.sendMessage("§8[§7System§8] §7/Country INconsent");
                            sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "に招待を送りました。");
                        }
                        now++;
                        if (now >= endtime) {
                            removeinvitelist(player2);
                            sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "の招待がキャンセルされました。");
                            player2.sendMessage("§8[§7System§8] §7" + Countrydata.getCountryName(Country) + "への招待がキャンセルされました。");
                            this.cancel();
                        }
                        if (inviteok.contains(player2)) {
                            System.out.println(Playerdata.getNowCountry(player2).contains("null"));
                            if (Playerdata.getNowCountry(player2).contains("null")) {
                                Playerdata.setCountrytag(player2, Playerdata.CountryNomber(player2), Country);
                                Countrydata.addmember(Country, player2);
                                removeinviteok(player2);
                                player2.sendMessage("§8[§7System§8] §7" + Countrydata.getCountryName(Country) + "に参加しました。");
                                sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "が参加しました。");
                                this.cancel();
                            } else {
                                player2.sendMessage("§8[§7System§8] §7国に所属しているアカウントでは入ることができません。");
                                sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "の招待がキャンセルされました。");
                                removeinviteok(player2);
                                this.cancel();

                            }
                        }
                    }
                }.runTaskTimer(Potato.plugin, 0, 1);
            }else{
                sender.sendMessage("§8[§7System§8] §7指定したプレイヤーはすでに招待されています。");
            }
        }else{
            sender.sendMessage("§8[§7System§8] §7あなたには招待する権限がありません。");
        }
    }

    public static boolean inviteok(Player player){
        if(invitelist.contains(player)){
            invitelist.remove(player);
            inviteok.add(player);
            return true;
        }
        return false;
    }

    public static boolean invitecheck(Player player){
        if(invitelist.contains(player)){return true;}
        else{return false;}
    }

    public static void removeinvitelist(Player player){
        if(invitelist.contains(player)){
            invitelist.remove(player);
        }
    }

    public static void removeinviteok(Player player){
        if(inviteok.contains(player)){
            inviteok.remove(player);
        }
    }

    private static List<Player> removelist = new ArrayList<>();
    private static List<Player> removeok = new ArrayList<>();

    public static void removecountry(Player sender,Player player , String Countrytag){
        if(sender == player || Countrydata.checkpermission(sender,"remove")){
            if(removelist.contains(player) == false) {
                if(Countrydata.getOnlinemember(Playerdata.getNowCountry(sender)).contains(player)) {
                    if (Countrydata.checkpermissioncountry(player, "head",Playerdata.getNowCountry(sender)) == false) {
                        removelist.add(player);
                        new BukkitRunnable() {
                            Player sender2 = sender;
                            Player player2 = player;
                            String Country = Playerdata.getNowCountry(sender);
                            Integer now = 0;
                            Integer endtime = Potato.config.getInt("removetime", 1200);
                            Integer countrynom = Playerdata.getNumber(player,Country);

                            @Override
                            public void run() {
                                if (now == 0) {
                                    sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "を" + Countrydata.getCountryName(Country) + "から離脱させますか。");
                                    sender2.sendMessage("§8[§7System§8] §7離脱させるには以下のコマンドを実行してください");
                                    sender2.sendMessage("§8[§7System§8] §7/Country REconsent <Player>");
                                }
                                now++;
                                if (now >= endtime) {
                                    removeremovelist(player2);
                                    sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "の離脱をキャンセルしました。");
                                    this.cancel();
                                }
                                if (removeok.contains(player2)) {
                                    if(countrynom != 0) {
                                        sender2.sendMessage("§8[§7System§8] §7" + player2.getDisplayName() + "を離脱させました。");
                                        Countrydata.deleatmember(Country, player2);
                                        Playerdata.setCountrytag(player2, countrynom, "null");
                                        Playerdata.chengchatmode(player2, 0);
                                        removeremoveok(player2);
                                        this.cancel();
                                    }else{
                                        player.sendMessage("エラー^^");
                                    }
                                }

                            }
                        }.runTaskTimer(Potato.plugin, 0, 1);
                    }else{
                        sender.sendMessage("§8[§7System§8] §7国家元首は離脱させることができません。");
                    }
                }else{
                    sender.sendMessage("§8[§7System§8] §7あなたの国のメンバーではありません。");
                }
            }else{
                sender.sendMessage("§8[§7System§8] §7そのプレイヤーはすでに離脱処理中です。");
            }
        }else{
            sender.sendMessage("^^");
        }
    }

    public static void removeremovelist(Player player){
        if(removelist.contains(player)){
            removelist.remove(player);
        }
    }

    public static void removeremoveok(Player player){
        if(removeok.contains(player)){
            removeok.contains(player);
        }

    }
    public static void removechengeoklist(Player player){
        if(removelist.contains(player)){
            removelist.remove(player);
            removeok.add(player);
        }
    }

    public static boolean removecheck(Player player){
        if(removelist.contains(player)){return true;}
        else{return false;}
    }
}
