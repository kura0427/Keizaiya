package keizaiya.second.file.country;

import keizaiya.second.Potato;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ideology {
    public static String getideologyname(String TAG){
        if(TAG.contains("initial")){ return "初期イデオロギー";}
        if(Potato.config.getKeys(true).contains("ideology." + TAG)){
            return Potato.config.getString("ideology." + TAG);
        }else{
            return null;
        }
    }

    public static List<String> getlist(){
        List<String> ideologylist = new ArrayList<>();
        for(String Key: Potato.config.getKeys(true)){
            if(Key.contains("ideology.")){
                ideologylist.add(Key.replace("ideology.",""));
            }
        }
        ideologylist.add("initial");
        return ideologylist;
    }

    public static ItemStack getchengcard(String Tag){
        String ideology = getideologyname(Tag);
        ItemStack card = new ItemStack(Material.PAPER);
        if(ideology == null){ideology = "^^";}
        ItemMeta meta = card.getItemMeta();
        meta.setDisplayName("§dイデオロギー変更券");
        List<String> lore = new ArrayList<>();
        lore.add("§7Ideology§8:§3§l" + ideology);
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"ideology") , PersistentDataType.STRING, Tag);
        card.setItemMeta(meta);

        return card;
    }

    public static void clickideologycard(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getItem() != null) {
            String name = e.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin,"ideology")
                    , PersistentDataType.STRING);
            if(name != null){
                if(Countrydata.checkpermission(player,"head")){
                    Countrydata.setIdeology(Playerdata.getNowCountry(player),name);
                    player.sendMessage("§8[§7System§8] §7主義を§3§l" + getideologyname(name) + "§7に変更しました。");
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                }else{
                    player.sendMessage("§8[§7System§8] §7国家元首でなければ使えません。");
                }
            }
        }
    }

    public static String getIdeologyinfo(){
        String info = "";
        Integer i = 0;
        for(String Key: Potato.config.getKeys(true)){
            if(Key.contains("ideology.")){
                if(i == 3){
                    info = info + "\n";
                    i = 0;
                }
                info = info + "§8," + Potato.config.getString(Key) + ":§b" + Key.replace("ideology.","");
                i++;
            }
        }
        return info;
    }
}
