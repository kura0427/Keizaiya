package keizaiya.second.file.shop;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class shop {

    public static Map<String,shopdata> shoplist = new HashMap<>();

    public static void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("shop")) {
                if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("Create")){
                        if(args.length == 2){
                            shopdata shopdata = new shopdata(player.getUniqueId().toString(),args[1],getnewtag());
                            System.out.println(shopdata.gettag());
                            setshop(shopdata);
                            player.sendMessage(shopdata.gettag() + "で作成しました。");
                            updatefile();
                        }
                    }if(args[0].equalsIgnoreCase("geteditstick")){
                        if(args.length == 2){
                            shopdata data = getdata(args[1]);
                            if(data != null){
                                if(data.getOwner().equals(player.getUniqueId().toString())){
                                    if(player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)){
                                        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"shoptag") , PersistentDataType.STRING,data.gettag());
                                        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"shoppass") , PersistentDataType.STRING,data.getPass());
                                        meta.setDisplayName(data.gettag() + "のショップエディットスティック");
                                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                                    }else {
                                        player.sendMessage("手にポテト棒を持ってください。");
                                    }
                                }else {
                                    player.sendMessage("オーナーしか編集できません。");
                                }
                            }else {
                                player.sendMessage("tagが存在しないものです。");
                            }
                        }
                    }if(args[0].equalsIgnoreCase("setaom")){
                        if (args.length == 4){
                            if(player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)){
                                String tag = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoptag")
                                        , PersistentDataType.STRING);
                                String pass = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin, "shoppass")
                                        , PersistentDataType.STRING);
                                shopdata data = shop.getdata(args[1]);
                                if(tag != null && pass != null && data != null){
                                    if(data.gettag().equals(tag)){
                                        if(data.getPass().equals(pass)){
                                            try{
                                                Integer aom = Integer.valueOf(args[3]);
                                                if(args[2].equals("pro")){
                                                    data.setProductaom(aom);
                                                    player.sendMessage("設定しました。");
                                                    shop.setshop(data);
                                                }else if(args[2].equals("give")){
                                                    data.setGiveaom(aom);
                                                    shop.setshop(data);
                                                    player.sendMessage("設定しました。");
                                                }else {
                                                    player.sendMessage("コマンドが間違ってます");
                                                }
                                            }catch (Exception e){
                                                player.sendMessage("数字を入力してください");
                                                return;
                                            }
                                        }else {
                                            player.sendMessage("パスワードが違います。");
                                        }
                                    }else {
                                        player.sendMessage("tagが違ってます。");
                                    }
                                }
                            }
                        }
                    }if(args[0].equalsIgnoreCase("chengeitem")){
                        if(args.length == 2){
                            shopdata data = shop.getdata(args[1]);
                            if(data != null){
                                if(data.getLocation().getBlock().getType().equals(Material.BARREL)) {
                                    Barrel chest = (Barrel) data.getLocation().getBlock().getState();
                                    Inventory chestinv = chest.getInventory();
                                    Integer proaom = 0;
                                    Integer giveaom = 0;
                                    ItemStack pro = data.getProduct().clone();
                                    pro.setAmount(1);
                                    ItemStack give = data.getGive().clone();
                                    give.setAmount(1);
                                    for (ItemStack e : chestinv.getContents()) {
                                        if(e != null) {
                                            ItemStack taisyou = e.clone();
                                            taisyou.setAmount(1);
                                            if (pro.equals(taisyou)) {
                                                proaom = proaom + e.getAmount();
                                            }
                                        }
                                    }
                                    for (ItemStack e : player.getInventory().getContents()) {
                                        if(e != null) {
                                            ItemStack taisyou = e.clone();
                                            taisyou.setAmount(1);
                                            if (give.equals(taisyou)) {
                                                giveaom = giveaom + e.getAmount();
                                            }
                                        }
                                    }
                                    if (giveaom >= data.getGiveaom() && proaom >= data.getProductaom()) {
                                        chestinv = removeitem(pro, data.getProductaom(), chestinv);
                                        chestinv = additem(give, data.getGiveaom(), chestinv, data.getLocation());
                                        chest.getInventory().setStorageContents(chestinv.getContents());

                                        Inventory inventory = player.getInventory();
                                        inventory = additem(pro, data.getProductaom(), inventory, player.getLocation());
                                        inventory = removeitem(give, data.getGiveaom(), inventory);
                                        player.getInventory().setContents(inventory.getContents());
                                    }else {
                                        if(giveaom < data.getGiveaom()){
                                            player.sendMessage("渡すアイテムが不足しています。");
                                        }else {
                                            player.sendMessage("商品が不足しています。");
                                        }
                                    }
                                }else {
                                    player.sendMessage("データがバグっています。発売元に確認してください。");
                                }
                            }
                        }
                    }if(args[0].equalsIgnoreCase("chengepass")){
                        if(args.length == 3){
                            shopdata data = getdata(args[1]);
                            if(data != null){
                                if(data.getOwner().equals(player.getUniqueId().toString())){
                                    data.setPass(args[2]);
                                    shop.setshop(data);
                                }else {
                                    player.sendMessage("オーナーしか編集できません。");
                                }
                            }else {
                                player.sendMessage("tagが存在しないものです。");
                            }
                        }
                    }
                }
            }
        }
    }

    public static String getnewtag(){
        List<String> data = gettaglist();
        if(data != null) {
            String tag = "shop" + data.size();
            return tag;
        }else {
            return "shop" + 0;
        }
    }
    public static void updatefile(){
        File file = new File("KeizaiyaMain/shop.yml");
        if(file.exists()){
            YamlConfiguration yml = Yamlfile.loadyaml(file);
            shoplist.clear();
            for(String key : yml.getKeys(false)){
                    String tag = yml.getString(key + ".tag");
                    String uuid = yml.getString(key + ".owner");
                    String pass = yml.getString(key + ".pass");
                    ItemStack stack = (ItemStack) yml.get(key + ".product");
                    Integer aom = yml.getInt(key + ".productaom");
                    ItemStack stack1 = (ItemStack) yml.get(key + ".give");
                    Integer aom2 = yml.getInt(key + ".giveaom");
                    Location location = (Location) yml.get(key + ".location");
                    Location kanban = (Location) yml.get(key + ".kanban");
                    shopdata shopdatas = new shopdata(uuid,pass,tag);
                    shopdatas.setGive(stack1);
                    shopdatas.setGiveaom(aom2);
                    shopdatas.setProduct(stack);
                    shopdatas.setProductaom(aom);
                    shopdatas.setLocation(location);
                    shopdatas.setKanban(kanban);
                    shoplist.put(shopdatas.gettag(),shopdatas);

            }
        }else {
            YamlConfiguration yml = new YamlConfiguration();
            Yamlfile.Saveyaml(file,yml);
        }
    }
    public static List<String> gettaglist(){
        File file = new File("KeizaiyaMain/shop.yml");
        YamlConfiguration yml = Yamlfile.loadyaml(file);
        List<String> data = new ArrayList<>();
        if(yml.getKeys(false) != null) {
            if(yml.getKeys(false).size() != 0) {
                for(String key : yml.getKeys(false)) {
                    data.add(key);
                }
            }
        }
        return data;

    }

    public static void setshop(shopdata data){
        File file = new File("KeizaiyaMain/shop.yml");
        YamlConfiguration yml = Yamlfile.loadyaml(file);
        yml.set(data.gettag() + ".owner",data.getOwner());
        yml.set(data.gettag() + ".pass",data.getPass());
        yml.set(data.gettag() + ".product",data.getProduct());
        yml.set(data.gettag() + ".productaom",data.getProductaom());
        yml.set(data.gettag() + ".give",data.getGive());
        yml.set(data.gettag() + ".giveaom",data.getGiveaom());
        yml.set(data.gettag() + ".location",data.getLocation());
        yml.set(data.gettag() + ".tag",data.gettag());
        yml.set(data.gettag() + ".kanban",data.getKanban());
        Yamlfile.Saveyaml(file,yml);
    }

    public static shopdata getdata(String tag){
        updatefile();
        if(shoplist.keySet().contains(tag)){
            return shoplist.get(tag);
        }else {
            return null;
        }
    }

    public static Inventory removeitem(ItemStack stack , Integer yeah , Inventory inv){
        Integer nokori = yeah;
        for(int i = 0 ; i< inv.getSize() ; i++){
            ItemStack e = inv.getItem(i);
            if(e != null) {
                ItemStack taisyou = e.clone();
                taisyou.setAmount(1);
                if (stack.equals(taisyou)) {
                    Integer now = e.getAmount();
                    if (now - nokori < 0) {
                        nokori = nokori - e.getAmount();
                        e.setAmount(0);
                        inv.setItem(i, e);
                    } else {
                        e.setAmount(e.getAmount() - nokori);
                        inv.setItem(i, e);
                        break;
                    }
                }
            }
        }

        return inv;
    }

    public static Inventory additem(ItemStack stack , Integer yeah , Inventory inv , Location location){
        Integer nokori = yeah;
        for(int i = 0 ; i< inv.getSize() ; i++){
            ItemStack e = inv.getItem(i);
            ItemStack yeahs = stack.clone();
            if(e == null){
                if(nokori - 64 >= 0){
                    nokori = nokori - 64;
                    yeahs.setAmount(64);
                    inv.setItem(i,yeahs);
                }else{
                    yeahs.setAmount(nokori);
                    nokori = 0;
                    inv.setItem(i,yeahs);
                    break;
                }
            }else {
                ItemStack taisyou = e.clone();
                taisyou.setAmount(1);
                if(taisyou.equals(yeahs)) {
                    if (e.getAmount() + nokori >= 64) {
                        Integer sa = 64 - e.getAmount();
                        e.setAmount(64);
                        nokori = nokori - sa;
                        inv.setItem(i, e);
                    } else {
                        e.setAmount(e.getAmount() + nokori);
                        nokori = 0;
                        inv.setItem(i, e);
                    }
                }
            }
        }
        if(nokori > 0){
            ItemStack yeahs = stack.clone();
            location.setY(location.getBlockY() + 2);
            yeahs.setAmount(nokori);
            location.getWorld().dropItemNaturally(location,yeahs);
        }
        return inv;
    }
}