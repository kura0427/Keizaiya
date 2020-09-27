package keizaiya.second.chat;

import keizaiya.second.Potato;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class chat {
    public static void chat(AsyncPlayerChatEvent e) {
        Integer chatmode = Playerdata.chatmode(e.getPlayer());
        String messagemot = e.getMessage();
        Boolean noneIME = true;
        Player sender = e.getPlayer();
        if (e.getMessage().substring(0).contains("!")) {
            chatmode = 0;
            messagemot = messagemot.substring(1, messagemot.length());
        }
        if (messagemot.substring(0).contains("#")) {
            noneIME = false;
            messagemot = messagemot.substring(1, messagemot.length());
            if (messagemot.substring(0).contains("!")) {
                chatmode = 0;
                messagemot = messagemot.substring(1, messagemot.length());
            }
        }
        String messagemot2 = chengewoad.chengeword(messagemot);
        String result = googleIME.convByGoogleIME(japanese.conv(messagemot2));
        String Countryname = "";
        String tag = null;
        if (chatmode != 100 && chatmode != 0) {
            if (Playerdata.getCountrytag(e.getPlayer(), chatmode).contains("null")) {
                Playerdata.chengchatmode(e.getPlayer(),0);
                chatmode = Playerdata.chatmode(e.getPlayer());
            }
        }
        if(chatmode == 0){
            tag = Playerdata.getNowCountry(e.getPlayer());
        }else if( chatmode != 100 && chatmode > 0){
            tag = Playerdata.getCountrytag(sender,chatmode);
        }
        if(tag != null) {
            if (tag.contains("null")) {
                Countryname = "§e放浪者§8";
            } else if (tag != null) {
                Countryname = Countrydata.getnickname(tag);
            } else {
                Countryname = "エラー";
            }
        }else{
            if( chatmode == 100){
                Countryname = "§cAdmin§8";
            }else{
                Countryname = "null";
            }
        }


        String message2 = "";
        Boolean nihongoin = false;
        for(String tango : nihongo){
            if(e.getMessage().contains(tango)){
                nihongoin = true;
                break;
            }
        }
        if(nihongoin){ message2 = messagemot2;}
        if(noneIME) {
            if (nihongoin == false) {
                message2 = result + " §8(" + messagemot + ")";
            }
        }else{ message2 = messagemot; }

        if(chatmode == 0){
            String role = "";
            if(Playerdata.getNowCountry(sender).contains("null") == false){
                role = Countrydata.getrole(sender,tag);
            }
            String message = "§8[§bAll§8][§7" + Countryname + "§8]§f" + role + "§f" + sender.getDisplayName() + "§b: §f" + message2;
            for(Player player : Potato.plugin.getServer().getOnlinePlayers()){
                player.sendMessage(message.replace("&","§"));
            }

            Log( "[" + "All" + "][" + e.getPlayer().getDisplayName() + "] " + message2);

        }else if(chatmode == 100){
            String message = "§8[§cAdmin§8] §f" + sender.getDisplayName() + "§b: §f" + message2;
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(message.replace("&","§"));
            }

            Log( "[" + "Admin" + "][" + e.getPlayer().getDisplayName() + "] " + message2);

        } else if(chatmode > 0){
            String role = Countrydata.getrole(sender,tag);
            for(Player player : Countrydata.getOnlinemember(tag)){
                Integer Coutrynamber = Playerdata.getNumber(player,tag);
                String countrynom = "null";
                if(Coutrynamber == 1){ countrynom = "C1";}
                if(Coutrynamber == 2){ countrynom = "C2";}
                if(Coutrynamber == 3){ countrynom = "C3";}
                if(Coutrynamber == 0){ countrynom = "^^";}
                String message = "§8[§e" + countrynom +"§8][§7" + Countryname + "§8]§f" + role + "§f" + sender.getDisplayName() + "§b: §f" + message2;

                player.sendMessage(message.replace("&","§"));
            }

            Log( "[" + "Country" + "][" + Countryname + "§8][§f" + e.getPlayer().getDisplayName() + "] " + message2);
        }
        e.setCancelled(true);
    }

    public static List<String> nihongo = new ArrayList<>(Arrays.asList(
            "あ","い","う","え","お","か","き","く","け","こ",
            "さ","し","す","せ","そ","た","ち","つ","て","と",
            "な","に","ぬ","ね","の","は","ひ","ふ","へ","ほ",
            "ま","み","む","め","も","ら","り","る","れ","ろ",
            "や","ゆ","よ","わ","を", "ん",
            "ア","イ","ウ","エ","オ","カ","キ","ク","ケ","コ",
            "サ","シ","ス","セ","ソ","タ","チ","ツ","テ","ト",
            "ナ","ニ","ヌ","ネ","ノ","ハ","ヒ","フ","ヘ","ホ",
            "マ","ミ","ム","メ","モ","ラ","リ","ル","レ","ロ",
            "ヤ","ユ","ヨ","ワ","ヲ", "ン"
    ));

    public static void Log(String message){
        System.out.println(message);
        Calendar cal = Calendar.getInstance();
        String date = cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + "";
        File file = new File("KeizaiyaMain/Log/"+ date);
        if (file.exists() == false){
            if(file.mkdirs() == true){
                System.out.println("mkdirssucsess");
            }else{ System.out.println("mkdirsmiss"); }
        }
        file = new File("KeizaiyaMain/Log/"+ date + "/"+ cal.get(Calendar.DATE) + ".txt");
        try{
            FileWriter filewriter = new FileWriter(file,true);
            filewriter.write(  cal.get(Calendar.HOUR_OF_DAY) +":"+ cal.get(Calendar.MINUTE) +":"+ cal.get(Calendar.SECOND) + ";"
                    + message + "\n");
            filewriter.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static String chengemessage(String massage , Player player){
        Integer chatmode = Playerdata.chatmode(player);
        String messagemot = massage;
        Boolean noneIME = true;
        Player sender = player;
        if(massage.substring(0).contains("!")){
            chatmode = 0;
            messagemot = messagemot.substring(1,messagemot.length());
        }if(messagemot.substring(0).contains("#")){
            noneIME = false;
            messagemot = messagemot.substring(1,messagemot.length());
            if(messagemot.substring(0).contains("!")){
                chatmode = 0;
                messagemot = messagemot.substring(1,messagemot.length());
            }
        }
        String messagemot2 = chengewoad.chengeword(messagemot);
        String result = googleIME.convByGoogleIME(japanese.conv(messagemot2));
        String tag = null;
        if(chatmode == 0){
            tag = Playerdata.getNowCountry(player);
        }else if( chatmode != 100 && chatmode > 0){
            tag = Playerdata.getCountrytag(sender,chatmode);
        }


        String message2 = "";
        Boolean nihongoin = false;
        for(String tango : nihongo){
            if(massage.contains(tango)){
                nihongoin = true;
                break;
            }
        }
        if(nihongoin){ message2 = messagemot2;}
        if(noneIME) {
            if (nihongoin == false) {
                message2 = result + " §8(" + messagemot + ")";
            }
        }else{ message2 = messagemot; }
        return message2;
    }

    public static Map<Player,Player> dm = new HashMap<>();
    public static Map<Integer,Boolean> task = new HashMap<>();
    public static Map<Integer,List<String>> taskdata = new HashMap<>();
    public static Map<Integer,String> taskmessage = new HashMap<>();

    public static void announcement(String massage , Integer time){

        new BukkitRunnable(){
            Integer key = getnewtag();
            String messages = massage;
            Integer times = time;
            Integer nowtime = 0;
            Boolean end = false;
            @Override
            public void run(){
                if(task.containsKey(key)){
                    if(task.get(key)){
                        task.remove(key);
                        taskdata.remove(key);
                        taskmessage.remove(key);
                        end =true;
                        this.cancel();
                    }
                }else{
                    task.put(key,false);
                    taskdata.put(key,new ArrayList<String>());
                    taskmessage.put(key,messages);
                }
                if(end == false) {
                    if (nowtime <= 0) {
                        String message = ("&8[" + key + "&8][&e&lお知らせ&8]&f" + messages).replace("&", "§");
                        List<String> notmessage = taskdata.get(key);
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (notmessage.contains(player.getUniqueId().toString()) == false) {
                                chatsiliarize.sendtuuti(player,message,key);
                            }
                        }
                        nowtime = times;
                    }
                    nowtime--;
                }
            }
        }.runTaskTimer(Potato.plugin,0,20);
    }

    public static Integer getnewtag(){
        int i = 0;
        while (true){
            if(task.containsKey(i) == false){
                return i;
            }
            i++;
        }
    }

    public static String getlist(){
        String message = "";
        for(Integer i : task.keySet()){
            message = message + i + " §3:§f " + taskmessage.get(i) + "\n";
        }
        return message;
    }

    public static Boolean chengePlayer(Player player, Integer number){
        if (taskdata.containsKey(number)) {
            List<String> data = taskdata.get(number);
            Boolean yeah = null;
            if(data.contains(player.getUniqueId().toString())) {
                data.remove(player.getUniqueId().toString());
                yeah = true;
            }else{
                data.add(player.getUniqueId().toString());
                yeah = false;
            }
            taskdata.put(number,data);
            return yeah;
        }
        return null;
    }

    public static Boolean setPlayer(Player player, Integer number,Boolean mode){
        if (taskdata.containsKey(number)) {
            List<String> data = taskdata.get(number);
            System.out.println(data);
            Boolean yeah = null;
            if(data.contains(player.getUniqueId().toString())) {
                if(mode == true) {
                    data.remove(player.getUniqueId().toString());
                    yeah = true;
                }else{yeah = false;}
            }else{
                if(mode == false) {
                    data.add(player.getUniqueId().toString());
                    yeah = false;
                }else{
                    yeah = true;
                }
            }
            taskdata.put(number,data);
            return yeah;
        }
        return null;
    }
}
