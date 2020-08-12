package keizaiya.second.file.country;

import keizaiya.second.Potato;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

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

    public static ItemStack getreligion(){
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§1宗教変更紙");
        List<String> lore = new ArrayList<>();
        lore.add("§f使用する際はこのアイテムを手に持って");
        lore.add("§f/country religion <Name>");
        lore.add("§fをしてください");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"religion") , PersistentDataType.STRING, "yeah");
        stack.setItemMeta(meta);
        return stack;
    }
}
