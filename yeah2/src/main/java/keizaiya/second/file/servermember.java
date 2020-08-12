package keizaiya.second.file;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.util.UUID;

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

    public static void getplayer(String playername){
        UUID uuid = Bukkit.getPlayerUniqueId(playername);
        OfflinePlayer player1 = Bukkit.getServer().getOfflinePlayer(uuid);
        System.out.println(uuid);
        System.out.println(player1);
        Player player = player1.getPlayer();
        if(player != null){
            System.out.println(player);
            System.out.println(player.getDisplayName());
        }

    }
}
