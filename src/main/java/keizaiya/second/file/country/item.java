package keizaiya.second.file.country;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class item {
    public static ItemStack getcard(){
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c証");
        List<String> lore = new ArrayList<>();
        lore.add("§7Name§8: §7国家の証");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getIdeorogycard(String tag){
        String ideology2 = ideology.getideologyname(tag);
        if(ideology2 == null){ideology2 = "^^";}
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c証");
        List<String> lore = new ArrayList<>();
        lore.add("§7Ideology§8:§3§l" + ideology2);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
