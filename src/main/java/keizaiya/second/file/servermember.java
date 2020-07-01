package keizaiya.second.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class servermember {
    public static void checkmember(Player player){
        File file = new File("KeizaiyaMain/member.yml");
        YamlConfiguration yml = new YamlConfiguration();
        if(file.exists()){
            yml = Yamlfile.loadyaml(file);
        }
        if(yml.getKeys(true).contains(player.getUniqueId().toString()) == false){
            yml.set(player.getUniqueId().toString(),player.getDisplayName());
        }else{
            if(yml.getString(player.getUniqueId().toString()) != player.getDisplayName()){
                yml.set(player.getUniqueId().toString(),player.getDisplayName());
            }
        }
        Yamlfile.Saveyaml(file,yml);
    }
}
