package keizaiya.second.file.Admin;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class adminfile {
    public static boolean checkAdmindata(){
        File file = new File("KeizaiyaMain/admindata.yml");
        if(file.exists() == false){
            InputStream stream = Potato.clname.getResourceAsStream("/sample/admindata.yml");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            YamlConfiguration yml = new YamlConfiguration();
            try {
                yml.load(br);
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public static void setMainTP(Location location){
        YamlConfiguration yml = loadfile();
        yml.set("TP.world",location.getWorld().getName());
        yml.set("TP.X",location.getX());
        yml.set("TP.Y",location.getY());
        yml.set("TP.Z",location.getZ());
        savefile(yml);
    }
    public static Location getMainTP(){
        YamlConfiguration yml = loadfile();
        Location location = new Location(Bukkit.getWorld(yml.getString("TP.world")),yml.getDouble("TP.X")
                ,yml.getDouble("TP.Y"),yml.getDouble("TP.Z"));
        return location;
    }

    public static void setTPname(String name){
        YamlConfiguration yml = loadfile();
        yml.set("Name",name);
        savefile(yml);
    }
    public static String getTPname(){
        YamlConfiguration yml = loadfile();
        return yml.getString("Name");
    }

    private static YamlConfiguration loadfile(){
        File file = new File("KeizaiyaMain/admindata.yml");
        return Yamlfile.loadyaml(file);
    }

    private static boolean savefile( YamlConfiguration yml){
        File file = new File("KeizaiyaMain/admindata.yml");
        Yamlfile.Saveyaml(file,yml);
        return true;
    }

    public static void addmember(Player player){
        YamlConfiguration yml = loadfile();
        List<String> list = new ArrayList<String>();
        if(yml.getKeys(true).contains("member")){
            if(yml.getStringList("member").contains(player.getUniqueId().toString()) == false) {
                list = yml.getStringList("member");
                list.add(player.getUniqueId().toString());
                yml.set("member",list);
                savefile(yml);
            }
        }else{
            list.add(player.getUniqueId().toString());
            yml.set("member",list);
            savefile(yml);
        }
    }

    public static void removemember(Player player){
        YamlConfiguration yml = loadfile();
        List<String> list = new ArrayList<String>();
        if(yml.getKeys(true).contains("member")){
            list = yml.getStringList("member");
        }
        list.remove(player.getUniqueId().toString());
        yml.set("member",list);
        savefile(yml);
    }

    public static List<Player> getOnlinemember(){
        List<Player> member = new ArrayList<>();
        YamlConfiguration yml = loadfile();
        if(yml.getKeys(true).contains("member")) {
            List<String> memberuuid = yml.getStringList("member");
            for (Player player : Potato.plugin.getServer().getOnlinePlayers()) {
                if (memberuuid.contains(player.getUniqueId().toString())) {
                    member.add(player);
                }
            }
        }
        return member;
    }

    public static boolean checkmember(Player player){
        List<Player> member = new ArrayList<>();
        YamlConfiguration yml = loadfile();
        if(yml.getKeys(true).contains("member")) {
            if(yml.getStringList("member").contains(player.getUniqueId().toString())){
                return true;
            }
        }
        return false;
    }
}
