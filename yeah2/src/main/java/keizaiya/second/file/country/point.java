package keizaiya.second.file.country;

import keizaiya.second.Potato;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class point {
    public static List<Material> PointBlocklist = new ArrayList<>();
    public static void updatelist(){
        List<String> blockname = Potato.config.getStringList("breaklist");
        for (String name : blockname) {
            if (Material.getMaterial(name) != null) {
                PointBlocklist.add(Material.getMaterial(name));
            }
        }
    }
    public static boolean checkBlock(Material material){
        if(PointBlocklist.contains(material)){
            return true;
        }
        return false;
    }
}
