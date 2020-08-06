package keizaiya.second.chat.channel;

import keizaiya.second.Potato;
import keizaiya.second.file.player.Playerdata;
import keizaiya.second.inventory.menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class channelmenu {
    public static List<String> menus = new ArrayList<>(Arrays.asList(
            "ch","ch","ch","ch","ch","ch","ch","ch","ch"
            ,"ch","ch","ch","ch","ch","ch","ch","ch","ch"
            ,"bp","bp","bp","bp","menu","bp","bp","bp","bp"
    ));
    public static Inventory chatmenu(Player player){
        //アイテムデータ関連
        ItemStack BD = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = BD.getItemMeta();
        meta.setDisplayName(" ");
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"chmenu") , PersistentDataType.STRING, "back");
        BD.setItemMeta(meta);

        ItemStack menu = new ItemStack(Material.CHEST);
        meta = menu.getItemMeta();
        meta.setDisplayName("menu");
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"chmenu") , PersistentDataType.STRING, "menu");
        menu.setItemMeta(meta);

        Inventory inv = Bukkit.createInventory(null,27,"Channel");
        inv.clear();
        List<String> list = new ArrayList<>();
        channel.updatechannel();
        for(int c = 0; c <= menus.size()-1 ; c++){
            if(menus.get(c) == "bp"){
                inv.setItem(c,BD);
            }if(menus.get(c) == "menu"){
                inv.setItem(c,menu);
            }if(menus.get(c) == "ch"){
                for(String key : channel.list.keySet()){
                    channeldata data = channel.getvalum(key);
                    if(list.contains(key) == false){
                        if(data.getMember().contains(player.getUniqueId().toString())){
                            list.add(key);
                            ItemStack ch = new ItemStack(Material.WHITE_WOOL);
                            meta = ch.getItemMeta();
                            meta.setDisplayName(data.getname());
                            List<String> lore = new ArrayList<>();
                            lore.add(key);
                            meta.setLore(lore);
                            meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"tag") , PersistentDataType.STRING, data.gettag());
                            meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"chmenu") , PersistentDataType.STRING, "channel");
                            ch.setItemMeta(meta);
                            inv.setItem(c,ch);
                            break;
                        }
                    }
                }
            }
        }
        return inv;
    }

    public static void clickmenu(InventoryClickEvent e) {
        if (e.getCurrentItem() != null) {
            String name = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "chmenu")
                    , PersistentDataType.STRING);
            if(name == "menu"){
                e.getWhoClicked().openInventory(menu.menu((Player) e.getWhoClicked()));
            }if(name == "channel"){
                String tag = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "tag")
                        , PersistentDataType.STRING);
                if(channel.list.keySet().contains(tag)){
                    channeldata data = channel.list.get(tag);
                    Playerdata.chengchatmode((Player) e.getWhoClicked(),data.getModenum());
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage("chatmodeを " + data.getname() + " に変更しました。");
                }
            }
        }
        e.setCancelled(true);
    }
}
