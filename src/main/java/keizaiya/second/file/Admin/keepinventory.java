package keizaiya.second.file.Admin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class keepinventory {

    public static List<Player> keeplist = new ArrayList<>();

    public static void deathPlayer(PlayerDeathEvent e){
        if(keeplist.contains(e.getEntity())){
            e.setKeepInventory(true);
            e.setKeepLevel(true);
            e.setDeathMessage("keepInventoryが有効です");
            e.setDroppedExp(0);
            e.getDrops().clear();
        }
    }

    public static void addPlayer(Player player){
        if(checkPlayer(player) == false){
            keeplist.add(player);
        }
    }

    public static boolean checkPlayer(Player player){
        return keeplist.contains(player);
    }

    public static void removePlayer(Player player){
        if(checkPlayer(player)){
            keeplist.remove(player);
        }
    }

    public static String listPlayer(){
        if(keeplist.size() > 0) {
            String name = "";
            for (Player player : keeplist){
                name = name + player.getDisplayName() + "\n";
            }
            return name;
        }
        else {
            return "プレイヤーなし";
        }
    }
}
