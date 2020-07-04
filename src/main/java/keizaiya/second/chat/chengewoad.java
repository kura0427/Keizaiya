package keizaiya.second.chat;

import keizaiya.second.file.Yamlfile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class chengewoad {

    public static void checkfile(){
        File file = new File("KeizaiyaMain/word.yml");
        if(file.exists() == false){
            YamlConfiguration yml = new YamlConfiguration();
            yml.set(":sent:","^^");
            Yamlfile.Saveyaml(file,yml);
        }
    }
    private static YamlConfiguration loadword(){
        File file = new File("KeizaiyaMain/word.yml");
        return Yamlfile.loadyaml(file);
    }

    private static void saveword( YamlConfiguration yml){
        File file = new File("KeizaiyaMain/word.yml");
        Yamlfile.Saveyaml(file,yml);
    }

    public static void setword(String moto,String ward){
        YamlConfiguration yml = loadword();
        yml.set(moto,ward);
        saveword(yml);
    }

    public static void removeword(String before){
        YamlConfiguration yml = loadword();
        if(yml.getKeys(true).contains(before)) {
            YamlConfiguration yml2 = new YamlConfiguration();
            for(String key : yml.getKeys(true)){
                if(key.contains(before) == false){
                    yml2.set(key,yml.getString(key));
                }
            }
            saveword(yml2);
        }
    }
    public static String chengeword(String moto){
        YamlConfiguration yml = loadword();
        String chengeword = moto;
        for(String key :yml.getKeys(true)){
            if(moto.contains(key)){
                chengeword = chengeword.replace(key,yml.getString(key));
            }
        }
        return chengeword;
    }

    public static String getlist(){
        String lore = "";
        YamlConfiguration yml = loadword();
        Integer i = 0;
        for(String key :yml.getKeys(true)){
            if(i == 3){
                lore = lore + "\n";
                i = 0;
            }
            lore = lore + key + " §b:§f " + yml.getString(key) + "  §5;§f";
            i++;
        }
        return lore;
    }

    public static boolean checkword(String before){
        YamlConfiguration yml = loadword();
        return yml.getKeys(true).contains(before);
    }
}
