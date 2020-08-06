package keizaiya.second.chat.channel;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;

public class channel {

    public static Map<String, channeldata> list = new HashMap<>();
    public static Map<String,String> taglist = new HashMap<>();
    public static Map<String,List<Player>> invite = new HashMap<>();

    public static boolean Createchannel(Player player, String Name ){
        CheckFile();
        YamlConfiguration yml = new YamlConfiguration();
        try {
            InputStream stream = Potato.clname.getResourceAsStream("/sample/channel.yml");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            yml.load(br);
            yml.set("name", Name);
            yml.set("owner", player.getUniqueId().toString());
            yml.set("member", new ArrayList<String>(Arrays.asList(player.getUniqueId().toString())));
            String tag = getNewtag();
            savechannel(tag, yml);
            player.sendMessage("§8[§7System§8] §7チャンネルを作成しました(§f" + Name + "§7)");
            updatechannel();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return false;

    }
    public static void CheckFile(){
        File file = new File("KeizaiyaMain/channel/");
        if(file.exists() == false){
            file.mkdirs();
        }
    }

    public static void updatechannel(){
        if(list != null) {
            list.clear();
        }
        if(taglist != null) {
            taglist.clear();
        }
        File file = new File("KeizaiyaMain/channel/");
        File[] listfile = file.listFiles();
        for(File file1 : listfile){
            String tag = file1.getName().replace(".yml","");
            YamlConfiguration yml = loadchannel(tag);
            if(yml.getBoolean("close") == false) {
                taglist.put(yml.getString("name"), tag);
                channeldata data = new channeldata(yml.getString("name"), yml.getString("owner"),
                        tag, yml.getStringList("member"));
                list.put(tag, data);
            }
        }
    }

    private static YamlConfiguration loadchannel(String tag){
        File file = new File("KeizaiyaMain/channel/" + tag + ".yml");
        return Yamlfile.loadyaml(file);
    }

    private static void savechannel(String tag , YamlConfiguration yml){
        File file = new File("KeizaiyaMain/channel/" + tag + ".yml");
        Yamlfile.Saveyaml(file,yml);
    }

    public static String getNewtag(){
        File file = new File("KeizaiyaMain/channel/");
        File[] listfile = file.listFiles();
        if(listfile != null) {
            return "ch" + String.valueOf(listfile.length);
        }else{return "ch";}
    }

    public static void addmember(String tag,String uuid){
        if(list.keySet().contains(tag)){
            if(list.get(tag).getMember().contains(uuid) == false){
                YamlConfiguration yml = loadchannel(tag);
                List<String> data = yml.getStringList("member");
                data.add(uuid);
                yml.set("member",data);
                savechannel(tag,yml);
            }
        }
        updatechannel();
    }

    public static void removemember(String tag,String uuid){
        if(list.keySet().contains(tag)){
            if(list.get(tag).getMember().contains(uuid)){
                YamlConfiguration yml = loadchannel(tag);
                List<String> data = yml.getStringList("member");
                data.remove(uuid);
                yml.set("member",data);
                savechannel(tag,yml);
            }
        }
        updatechannel();
    }

    public static void closechannel(String tag,String uuid){
        if(list.keySet().contains(tag)){
            if(list.get(tag).getMember().contains(uuid)){
                YamlConfiguration yml = loadchannel(tag);
                yml.set("close",true);
                savechannel(tag,yml);
            }
        }
        updatechannel();
    }

    public static List<Player> getOnlinePlayer(String tag){
        if(list.containsKey(tag)){
            channeldata data = list.get(tag);
            List<Player> players = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()){
                if(data.getMember().contains(player.getUniqueId().toString())){
                    players.add(player);
                }
            }
            return players;
        }
        return null;
    }

    public static String getchanneltag(Integer ints){
        System.out.println(ints);
        for(String key : list.keySet()){
            System.out.println(list.get(key).getModenum());
            if(list.get(key).getModenum().equals(ints)){
                return key;
            }
        }
        return null;
    }

    public static void addinvite(String tag , String name){
        if(list.containsKey(tag)){
            Player player = Bukkit.getPlayer(name);
            if(player != null){
                List<Player> data = new ArrayList<>();
                if(invite.containsKey(tag)){
                    data = invite.get(tag);
                }
                data.add(player);
                invite.put(tag,data);
            }
        }
    }

    public static void removeinvite(String tag , String name){
        if(list.containsKey(tag)){
            Player player = Bukkit.getPlayer(name);
            if(player != null){
                List<Player> data = new ArrayList<>();
                if(invite.containsKey(tag)){
                    data = invite.get(tag);
                    data.remove(player);
                }
                invite.put(tag,data);
            }
        }
    }

    public static channeldata getvalum(String key){
        return list.get(key);
    }
}
