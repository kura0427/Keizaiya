package keizaiya.second.file.player;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.UUID;

public class Playerdata {

    public static boolean CreatePlayerdata(Player player){
        CheckFile();
        String UUID = player.getUniqueId().toString();
        File file = new File("KeizaiyaMain/Playerdata/" + UUID + ".yml");
        if(file.exists() == false){
            InputStream stream = Potato.clname.getResourceAsStream("/sample/playerdata.yml");
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

    public static boolean CheckPlayerDataFile(Player player){
        String UUID = player.getUniqueId().toString();
        File file = new File("KeizaiyaMain/Playerdata/" + UUID + ".yml");
        System.out.println(file);
        System.out.println(file.exists());
        return file.exists();
    }
    public static void CheckFile(){
        File file = new File("KeizaiyaMain/Playerdata/");
        if(file.exists() == false){
            file.mkdirs();
        }
    }

    private static YamlConfiguration loadfile(Player player){
        String UUID = player.getUniqueId().toString();
        File file = new File("KeizaiyaMain/Playerdata/" + UUID + ".yml");
        return Yamlfile.loadyaml(file);
    }

    private static boolean savefile(Player player , YamlConfiguration yml){
        String UUID = player.getUniqueId().toString();
        File file = new File("KeizaiyaMain/Playerdata/" + UUID + ".yml");
        Yamlfile.Saveyaml(file,yml);
        return true;
    }

    public static Integer CountryNomber(Player player){
        YamlConfiguration yml = loadfile(player);
        return yml.getInt("nowCountry");
    }

    public static void chengcountry(Player player, Integer number){
        YamlConfiguration yml = loadfile(player);
        yml.set("nowCountry",number);
        savefile(player,yml);
    }

    public static Integer chatmode(Player player){
        YamlConfiguration yml = loadfile(player);
        return yml.getInt("chat");
    }

    public static void chengchatmode(Player player, Integer mode){
        YamlConfiguration yml = loadfile(player);
        yml.set("chat",mode);
        savefile(player,yml);
    }

    public static void aotochangechatmode(Player player){
        YamlConfiguration yml = loadfile(player);
        Integer now = yml.getInt("chat");
        Integer chenge = 0;
        if(now == 0){ chenge = CountryNomber(player);}
        yml.set("chat",chenge);
        savefile(player,yml);
    }

    public static Integer getCountrychat(Player player){
        if(Playerdata.getNowCountry(player).contains("null") == false){
            return Playerdata.CountryNomber(player);
        }
        return 20;
    }

    public static String getCountrytag(Player player ,Integer number){
        YamlConfiguration yml = loadfile(player);
        if(number == 1){
            return yml.getString("F");
        }if(number == 2){
            return yml.getString("S");
        }if(number == 3){
            return yml.getString("T");
        }
        return null;
    }

    public static void setCountrytag(Player player,Integer number,String tag){
        YamlConfiguration yml = loadfile(player);
        if(number == 1){
            yml.set("F",tag);
        }if(number == 2){
            yml.set("S",tag);
        }if(number == 3){
            yml.set("T",tag);
        }
        savefile(player,yml);
    }
    public static String getNowCountry(Player player){
        return getCountrytag(player,CountryNomber(player));
    }

    public static String getPlayername(String Uuid){
        UUID uuid = UUID.fromString(Uuid);
        if(Bukkit.getServer().getPlayer(uuid) == null){
            return Bukkit.getServer().getOfflinePlayer(uuid).getName();
        }else{
            return Bukkit.getServer().getPlayer(uuid).getName();
        }
    }

    public static void deleatcountry(Player player ,String tag){
        File file = new File("KeizaiyaMain/Playerdata/");
        File[] files = file.listFiles();
        for(File file2 : files){
            YamlConfiguration yml = Yamlfile.loadyaml(file2);
            if(yml.getString("F").contains(tag)){
                yml.set("F","null");
            }if(yml.getString("S").contains(tag)){
                yml.set("S","null");
            }if(yml.getString("T").contains(tag)){
                yml.set("T","null");
            }
            Yamlfile.Saveyaml(file2,yml);
        }
    }

    public static Integer getNumber(Player player ,String TAG){
        YamlConfiguration yml = loadfile(player);
        if(yml.getString("F").contains(TAG)){
            return 1;
        }else if(yml.getString("S").contains(TAG)){
            return 2;
        }else if(yml.getString("T").contains(TAG)){
            return 3;
        }
        return 0;
    }
    //yeah^^


}
