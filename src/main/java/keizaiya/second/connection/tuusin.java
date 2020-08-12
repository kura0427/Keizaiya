package keizaiya.second.connection;

import com.google.gson.Gson;
import com.sun.corba.se.impl.oa.toa.TOA;
import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

public class tuusin {

    public static boolean stop = false;
    private static OutputStream out;
    private static InputStream in;
    public static Socket socket;
    public static boolean kidou = false;

    public static Thread startcom(){
        System.out.println("立ち上げました");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (stop == false) {
                    long Time =  System.currentTimeMillis();
                    try {
                        Socket socket = new Socket("192.168.10.145",42732);
                        in = socket.getInputStream();
                        out = socket.getOutputStream();
                        sendserver("server&close@");
                        Gson gson = new Gson();
                        while (true) {
                            String data = "";
                            while (true) {
                                int c = in.read();
                                String string = String.valueOf((char) c);
                                if (string.equalsIgnoreCase("@")) {
                                    break;
                                }
                                if (System.currentTimeMillis() - Time >= 10000) {
                                    data = "";
                                }
                                data = data + string;
                                Time = System.currentTimeMillis();
                            }
                            if(data != null) {
                                System.out.println(data);
                                String[] yeah = data.split("&");
                                if(yeah.length >= 1){
                                    if(yeah[0].equalsIgnoreCase("server")){
                                        if(yeah.length == 2){
                                            if(yeah[1].equalsIgnoreCase("account.request")){
                                                System.out.println("yeah");
                                                sendserver("{name: 'Keizaiyaplugin' , pass: 'i-01i-4jd8a9sy831-4kda-sklpkpads9'}@");
                                            }else if(yeah[1].equalsIgnoreCase("pass clear")){
                                                System.out.println("サーバーとの接続しました。");
                                                kidou = true;
                                            }else if(yeah[1].equalsIgnoreCase("close")){
                                                System.out.println("サーバーへの認証が失敗しました。");
                                                kidou = false;
                                            }else if(yeah[1].equalsIgnoreCase("used account")){
                                                System.out.println("アカウントが使われてます。");
                                                kidou = false;
                                            }
                                        }
                                    }else if(yeah[0].equalsIgnoreCase("discord_BOT_for_POTATO_WARS")){
                                        
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        kidou = false;
                        try {
                            socket.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });
        return thread;
    }

    public static void sendserver(String data){
        if(out != null){
            try {
                out.write(data.getBytes("UTF-8"));
            } catch (IOException e) {
                System.out.println("送信に失敗しました。");
                e.printStackTrace();
            }
        }
    }

    public static void close(){
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void checktps(){
        Gson gson = new Gson();
        new BukkitRunnable(){
            long lasttime = System.currentTimeMillis();
            @Override
            public void run() {
                if(kidou){
                    serverinfo info = getinfo();
                    int tps = checkTPS(info.getTPS());
                    if(tps == 1){
                        if(System.currentTimeMillis() - lasttime >= 100000){
                            lasttime = System.currentTimeMillis();
                            String data = gson.toJson(info);
                            sendserver(getBotsend(data));
                        }
                    }else if(tps == 2){
                        String data = gson.toJson(info);
                        sendserver(getBotsend(data));
                    }
                }
            }
        }.runTaskTimer(Potato.plugin,0,20);
    }

    public static String getBotsend(String data){
        String str = "discord_BOT_for_POTATO_WARS&" + data + "@";
        return str;
    }

    public static serverinfo getinfo(){
        File file = new File("Simpletab/bot/action.yml");
        if(file.exists()){
            YamlConfiguration yml = Yamlfile.loadyaml(file);
            String yeah = yml.getString("tps");
            String TPS = yeah;
            Float data = Float.valueOf(yeah);
            Long time = Long.valueOf(0);
            List<String> member = yml.getStringList("member");
            List<String> info = yml.getStringList("info");
            if(yml.getKeys(false).contains("time")){
                 time = yml.getLong("time");
            }
            return new serverinfo(data,time,member,info,0);
        }else {
            System.out.println("Simpletabのbot機能が有効ではないかエラーが発生しました");
            return null;
        }
    }

    public static int checkTPS(Float data){
        if(data <= 15){
            return 1;
        }else if(data <= 10){
            return 2;
        }
        return 0;
    }

}
