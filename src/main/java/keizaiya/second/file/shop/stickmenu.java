package keizaiya.second.file.shop;

import keizaiya.second.Potato;
import keizaiya.second.inventory.help;
import org.bukkit.*;
import org.bukkit.block.Barrel;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class stickmenu {

    public static List<Player> list = new ArrayList<>();
    public static List<Player> list2 = new ArrayList<>();

    public static void clickEvent(PlayerInteractEvent event){
        shop.updatefile();
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                if (event.getClickedBlock().getState() instanceof Barrel) {
                    Location location = event.getClickedBlock().getLocation();
                    for(String key : shop.shoplist.keySet()){
                        shopdata s = shop.shoplist.get(key);
                        if(s.getLocation().equals(location)){
                            if(s.getOwner().equals(event.getPlayer().getUniqueId().toString())){
                                break;
                            }else {
                                event.getPlayer().sendMessage("ショップに登録されてる樽なので開けません。");
                                event.setCancelled(true);
                                break;
                            }
                        }
                    }
                }if (event.getClickedBlock().getState() instanceof Sign) {
                    Location location = event.getClickedBlock().getLocation();
                    for(String key : shop.shoplist.keySet()){
                        shopdata s = shop.shoplist.get(key);
                        if(s.getKanban().equals(location)){
                            Bukkit.getServer().dispatchCommand(event.getPlayer(),"shop chengeitem " + s.gettag());
                            break;
                        }
                    }
                }
            }
            if(event.getItem() != null){
                if(event.getItem().getType().equals(Material.BLAZE_ROD)){
                    String tag = event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoptag")
                            , PersistentDataType.STRING);
                    String pass = event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoppass")
                            , PersistentDataType.STRING);
                    if(tag != null && pass != null){
                        shopdata data = shop.getdata(tag);
                        if(data != null){
                            if(data.getPass().equals(pass)){
                                if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                                    if(list.contains(event.getPlayer())) {
                                        if (event.getClickedBlock().getType().equals(Material.BARREL)) {
                                            data.setLocation(event.getClickedBlock().getLocation());
                                            shop.setshop(data);
                                            event.getPlayer().sendMessage("設定しました。");
                                            list.remove(event.getPlayer());
                                            event.setCancelled(true);
                                        }else {
                                            event.getPlayer().sendMessage("樽をクリックしてください。");
                                        }
                                    }else if(list2.contains(event.getPlayer())){
                                        if (event.getClickedBlock().getState() instanceof Sign) {
                                            data.setKanban(event.getClickedBlock().getLocation());
                                            shop.setshop(data);
                                            event.getPlayer().sendMessage("設定しました。");
                                            list.remove(event.getPlayer());
                                            event.setCancelled(true);
                                        }else {
                                            event.getPlayer().sendMessage("看板をクリックしてください。");
                                        }
                                    }
                                }else {
                                    event.getPlayer().openInventory(geteditmenu(data));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Inventory geteditmenu(shopdata data){
        if(data != null) {
            Inventory inventory = Bukkit.createInventory(null, 9, "チェストショップメニュー");

            ItemStack delete = new ItemStack(Material.BARRIER);
            ItemMeta metas = delete.getItemMeta();
            metas.setDisplayName("§e§lDelete");
            List<String> lore = new ArrayList<>();
            lore.add("§8§l>> §7§lRightClick");
            lore.add("§8情報を初期化します。");
            metas.setLore(lore);
            metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "delete");
            delete.setItemMeta(metas);
            inventory.setItem(7, delete);

            ItemStack book = new ItemStack(Material.BOOK);
            metas = book.getItemMeta();
            metas.setDisplayName("§e§lDATA");
            lore.clear();
            lore.add("§8Owner: " + data.getOwner());
            lore.add("Pass: " + data.getPass());
            metas.setLore(lore);
            metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "book");
            book.setItemMeta(metas);
            inventory.setItem(0, book);

            ItemStack were2 = new ItemStack(Material.BIRCH_SIGN);
            metas = were2.getItemMeta();
            metas.setDisplayName("§e§lSign Location");
            lore.clear();
            lore.add("§8§l>> §7§lRightClick");
            lore.add("§8使用する看板の位置を設定します。");
            lore.add("§8X : " + data.getKanban().getBlockX());
            lore.add("§8Y : " + data.getKanban().getBlockY());
            lore.add("§8Z : " + data.getKanban().getBlockZ());
            metas.setLore(lore);
            metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "sign");
            were2.setItemMeta(metas);
            inventory.setItem(1, were2);

            ItemStack were = new ItemStack(Material.BARREL);
            metas = were.getItemMeta();
            metas.setDisplayName("§e§lChest Location");
            lore.clear();
            lore.add("§8§l>> §7§lRightClick");
            lore.add("§8使用する樽の位置を設定します。");
            lore.add("§8X : " + data.getLocation().getBlockX());
            lore.add("§8Y : " + data.getLocation().getBlockY());
            lore.add("§8Z : " + data.getLocation().getBlockZ());
            metas.setLore(lore);
            metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "locate");
            were.setItemMeta(metas);
            inventory.setItem(2, were);

            ItemStack pro;
            if(data.getProduct().getType().equals(Material.AIR)){
                pro = new ItemStack(Material.WHITE_WOOL);
                metas = pro.getItemMeta();
                metas.setDisplayName("§e§l商品");
                lore.clear();
                lore.add("§8§l>> §7§lRightClick");
                lore.add("§8商品を設定します。");
                metas.setLore(lore);
                metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "pro");
                pro.setItemMeta(metas);
            }else{
                pro = new ItemStack(data.getProduct());
                metas = pro.getItemMeta();
                metas.setDisplayName("§e§l商品");
                lore.clear();
                lore.add("§8§l>> §7§lRightClick");
                lore.add("§8商品を設定します。");
                metas.setLore(lore);
                metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "pro");
                pro.setItemMeta(metas);
            }
            pro.setAmount(1);
            inventory.setItem(3, pro);

            ItemStack proaom = new ItemStack(Material.STONE_PICKAXE);
            metas = proaom.getItemMeta();
            metas.setDisplayName("§e§l商品 の数");
            lore.clear();
            lore.add("§8§l>> §7§lRightClick");
            lore.add("数: " + data.getProductaom());
            metas.setLore(lore);
            metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "proaom");
            proaom.setItemMeta(metas);
            inventory.setItem(4, proaom);

            ItemStack give;
            if(data.getGive().getType().equals(Material.AIR)){
                give = new ItemStack(Material.WHITE_WOOL);
                metas = give.getItemMeta();
                metas.setDisplayName("§e§lお金");
                lore.clear();
                lore.add("§8§l>> §7§lRightClick");
                lore.add("§8商品を設定します。");
                metas.setLore(lore);
                metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "give");
                give.setItemMeta(metas);
            }else{
                give = new ItemStack(data.getGive());
                metas = give.getItemMeta();
                metas.setDisplayName("§e§lお金");
                lore.clear();
                lore.add("§8§l>> §7§lRightClick");
                lore.add("§8商品を設定します。");
                metas.setLore(lore);
                metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "give");
                give.setItemMeta(metas);
            }
            give.setAmount(1);
            inventory.setItem(5, give);

            ItemStack giveaom = new ItemStack(Material.EMERALD);
            metas = giveaom.getItemMeta();
            metas.setDisplayName("§e§lお金の数");
            lore.clear();
            lore.add("§8§l>> §7§lRightClick");
            lore.add("数: " + data.getGiveaom());
            metas.setLore(lore);
            metas.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin, "chsp"), PersistentDataType.STRING, "giveaom");
            giveaom.setItemMeta(metas);
            inventory.setItem(6, giveaom);

            return inventory;
        }else {
            return null;
        }
    }

    public static void clickmenu(InventoryClickEvent e){
        if(e.getView().getTitle().equals("チェストショップメニュー")) {
            if (e.getCurrentItem() != null) {
                String name = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "chsp")
                        , PersistentDataType.STRING);
                String tag = e.getWhoClicked().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoptag")
                        , PersistentDataType.STRING);
                shopdata data = shop.getdata(tag);
                if (name.equals("delete")) {
                    shopdata newdata = new shopdata(data.getOwner(), data.getPass(), data.gettag());
                    shop.setshop(newdata);
                    e.getWhoClicked().sendMessage("初期化しました。");
                    e.getWhoClicked().closeInventory();
                } else if (name.equals("pro")){
                    e.getWhoClicked().openInventory(getselectmenu(0, (Player) e.getWhoClicked()));
                }else if(name.equals("proaom")){
                    help.sendtellraw((Player) e.getWhoClicked(),help.gettellrawtosuggest("このテキストをクリックしてください。","/shop setaom "  + data.gettag() +  " pro "));
                    e.getWhoClicked().closeInventory();
                }else if(name.equals("give")){
                    e.getWhoClicked().openInventory(getselectmenu(1, (Player) e.getWhoClicked()));
                }else if(name.equals("giveaom")){
                    help.sendtellraw((Player) e.getWhoClicked(),help.gettellrawtosuggest("このテキストをクリックしてください。","/shop setaom "  + data.gettag() +  " give "));
                    e.getWhoClicked().closeInventory();
                }else if(name.equals("locate")){
                    startlocateselect((Player) e.getWhoClicked());
                    e.getWhoClicked().sendMessage("使用する樽を左クリックしてください。");
                    e.getWhoClicked().closeInventory();
                }else if(name.equals("sign")){
                    startkanbanlect((Player) e.getWhoClicked());
                    e.getWhoClicked().sendMessage("使用する看板を左クリックしてください。");
                    e.getWhoClicked().closeInventory();
                }
                e.setCancelled(true);
            }
        }if(e.getView().getTitle().equals("渡すアイテムを選択してください。")) {
            if(e.getCurrentItem() != null){
                String tag = e.getWhoClicked().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoptag")
                        , PersistentDataType.STRING);
                shopdata data = shop.getdata(tag);
                if(data != null){
                    data.setProduct(e.getCurrentItem());
                    shop.setshop(data);
                    e.getWhoClicked().closeInventory();
                }
                e.setCancelled(true);
            }
        }if(e.getView().getTitle().equals("受け取るアイテムを選択してください。")) {
            if(e.getCurrentItem() != null){
                String tag = e.getWhoClicked().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoptag")
                        , PersistentDataType.STRING);
                shopdata data = shop.getdata(tag);
                if(data != null){
                    data.setGive(e.getCurrentItem());
                    shop.setshop(data);
                    e.getWhoClicked().closeInventory();
                }
                e.setCancelled(true);
            }
        }
    }

    public static Inventory getselectmenu(Integer type , Player player){
        Inventory inventory;
        if(type.equals(0)){
            inventory = Bukkit.createInventory(null,36,"渡すアイテムを選択してください。");
        }else if(type.equals(1)){
            inventory = Bukkit.createInventory(null,36,"受け取るアイテムを選択してください。");
        }else {
            return null;
        }
        for(int i = 0 ; i < 36 ; i++){
            inventory.setItem(i,player.getInventory().getItem(i));
        }
        return inventory;
    }

    public static void startlocateselect(Player player){
        list.add(player);
        if(list2.contains(player)){
            list2.remove(player);
        }
        new BukkitRunnable(){
            Long starttime = System.currentTimeMillis();
            Player p = player;
            @Override
            public void run() {
                if(list.contains(p) == false){
                    cancel();
                }
                if(System.currentTimeMillis() - starttime >= 60000){
                    list.remove(p);
                    p.sendMessage("解除しました。");
                    this.cancel();
                }
            }
        }.runTaskTimer(Potato.plugin,0,20);
    }

    public static void startkanbanlect(Player player){
        list2.add(player);
        if(list.contains(player)){
            list.remove(player);
        }
        new BukkitRunnable(){
            Long starttime = System.currentTimeMillis();
            Player p = player;
            @Override
            public void run() {
                if(list2.contains(p) == false){
                    cancel();
                }
                if(System.currentTimeMillis() - starttime >= 60000){
                    list2.remove(p);
                    p.sendMessage("解除しました。");
                    this.cancel();
                }
            }
        }.runTaskTimer(Potato.plugin,0,20);
    }

    public static void bb(BlockBreakEvent e){
        if(e.getBlock().getType().equals(Material.BARREL)){
            Location location = e.getBlock().getLocation();
            for(String key : shop.shoplist.keySet()){
                shopdata s = shop.shoplist.get(key);
                if(s.getLocation().getBlock().getState() instanceof Barrel) {
                    if (s.getLocation().equals(location)) {
                        e.getPlayer().sendMessage("破壊できません。");
                        e.setCancelled(true);
                        break;
                    }
                }
            }
        }
        if (e.getBlock().getState() instanceof Sign) {
            Location location = e.getBlock().getLocation();
            for(String key : shop.shoplist.keySet()){
                shopdata s = shop.shoplist.get(key);
                if(s.getKanban().equals(location)){
                    e.getPlayer().sendMessage("破壊できません。");
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }
}
