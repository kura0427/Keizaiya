package keizaiya.second.chat;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;

public class channel {

    public static Map<String,channeldata> list = new HashMap<>();
    public static Map<String,String> taglist = new HashMap<>();

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
                System.out.println(data.getname());
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

}
