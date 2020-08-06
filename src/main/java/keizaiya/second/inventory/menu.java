package keizaiya.second.inventory;

import keizaiya.second.Potato;
import keizaiya.second.author.music;
import keizaiya.second.author.playertelepoat;
import keizaiya.second.chat.channel.channelmenu;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.*;
import org.bukkit.block.EnderChest;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class menu {

    public static Map<String,Integer> kakusi = new HashMap<>();

    public static void opengui(Player player){
        player.openInventory(menu(player));
    }

    public static Inventory menu(Player player){
        List<String> defolt = new ArrayList<>(Arrays.asList("§8§l>> §7§lRightClick"));
        List<String> data2 = new ArrayList<>(Arrays.asList(
                "BD" , "BD" , "BD" , "BD" , "BD" , "BD" , "BD" , "BD" , "BD"
                , "BD" , "cty1" , "cty2" , "cty3" , "BD" , "TP1" , "TP2" , "TPA" , "BD"
                , "BD" , "ender" , "plinfo" , "help" , "CH" , "Info" , "chall" , "chcty" , "BD"
                , "BD" , "BD" , "BD" , "BD" , "BD" , "BD" , "BD" , "BD" , "BD2"
        ));

        //アイテムデータ関連
        ItemStack BD = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = BD.getItemMeta();
        meta.setDisplayName(" ");
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "back");
        BD.setItemMeta(meta);

        ItemStack BD2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        meta = BD.getItemMeta();
        meta.setDisplayName(" ");
        List<String> thinking = new ArrayList<>(Arrays.asList("§7^^"));
        meta.setLore(thinking);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "back2");
        BD2.setItemMeta(meta);

        ItemStack info = new ItemStack(Material.BOOK);
        meta = info.getItemMeta();
        meta.setDisplayName("§7§lCountry Info");
        List<String> lore = new ArrayList<>();
        lore.add("§8§l>> §7§lRightClick");
        if(Playerdata.getNowCountry(player).contains("null") == false){
            lore.add("§7§l国家: " + Countrydata.getCountryName( Playerdata.getNowCountry(player)));
        }else{
            lore.add("§e放浪者 §f:この機能は使えません");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "info");
        info.setItemMeta(meta);

        ItemStack tp1 = new ItemStack(Material.GLOWSTONE_DUST);
        meta = tp1.getItemMeta();
        meta.setDisplayName("§7§lTeleport §8§l[§7§lNo.1§8§l]");
        meta.setLore(defolt);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "tp1");
        tp1.setItemMeta(meta);

        ItemStack tp2 = new ItemStack(Material.SUGAR);
        meta = tp2.getItemMeta();
        meta.setDisplayName("§7§lTeleport §8§l[§7§lNo.2§8§l]");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        if(Playerdata.getNowCountry(player).contains("null") == false){
            lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getNowCountry(player)));
            if(Countrydata.getCountryTP(Playerdata.getNowCountry(player),0) == null){
                lore.add("§fTPが設定されていません");
            }
        }else{
            lore.add("§e放浪者 §f:この機能は使えません");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "tp2");
        tp2.setItemMeta(meta);

        ItemStack chatmode1 = new ItemStack(Material.DIAMOND_BLOCK);
        meta = chatmode1.getItemMeta();
        meta.setDisplayName("§7§lChatMode §8§l[§7§lAll§8§l]");
        meta.setLore(defolt);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "cty1");
        chatmode1.setItemMeta(meta);

        ItemStack chatmode2 = new ItemStack(Material.GOLD_BLOCK);
        meta = chatmode2.getItemMeta();
        meta.setDisplayName("§7§lChatMode §8§l[§7§lCountry§8§l]");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        if(Playerdata.getNowCountry(player).contains("null") == false){
            lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getNowCountry(player)));
        }else{
            lore.add("§e放浪者 §f:この機能は使えません");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "cty2");
        chatmode2.setItemMeta(meta);

        ItemStack Ender = new ItemStack(Material.ENDER_CHEST);
        meta = Ender.getItemMeta();
        meta.setDisplayName("§7§lEnder Chest");
        meta.setLore(defolt);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "ender");
        Ender.setItemMeta(meta);

        ItemStack Account1 = new ItemStack(Material.ENDER_PEARL);
        meta = Account1.getItemMeta();
        meta.setDisplayName("§7§lAccount §8§l[§7§lNo.1§8§l]");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        if(Playerdata.getCountrytag(player,1).contains("null") == false){
            lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getCountrytag(player,1)));
        }else{
            lore.add("§e放浪者");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "act1");
        Account1.setItemMeta(meta);

        ItemStack Account2 = new ItemStack(Material.ENDER_PEARL);
        meta = Account2.getItemMeta();
        meta.setDisplayName("§7§lAccount §8§l[§7§lNo.2§8§l]");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        if(Playerdata.getCountrytag(player,2).contains("null") == false){
            lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getCountrytag(player,2)));
        }else{
            lore.add("§e放浪者");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "act2");
        Account2.setItemMeta(meta);

        ItemStack Account3 = new ItemStack(Material.ENDER_PEARL);
        meta = Account3.getItemMeta();
        meta.setDisplayName("§7§lAccount §8§l[§7§lNo.3§8§l]");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        if(Playerdata.getCountrytag(player,3).contains("null") == false){
            lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getCountrytag(player,3)));
        }else{
            lore.add("§e放浪者");
        }
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "act3");
        Account3.setItemMeta(meta);

        ItemStack Help = new ItemStack(Material.BIRCH_SIGN);
        meta = Help.getItemMeta();
        meta.setDisplayName("§e§lHelp");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        lore.add("§8このプラグインの詳細がかかれています");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "help");
        Help.setItemMeta(meta);

        ItemStack playerinfo = new ItemStack(Material.PLAYER_HEAD);
        meta = playerinfo.getItemMeta();
        meta.setDisplayName("§3§lMy Info");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "playerinfo");
        playerinfo.setItemMeta(meta);
        SkullMeta metas = (SkullMeta) playerinfo.getItemMeta();
        metas.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        playerinfo.setItemMeta(metas);

        ItemStack ch = new ItemStack(Material.WHITE_WOOL);
        meta = ch.getItemMeta();
        meta.setDisplayName("§e§lChannels");
        lore.clear();
        lore.add("§8§l>> §7§lRightClick");
        lore.add("§8チャンネル一覧");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "ch");
        ch.setItemMeta(meta);

        Inventory inv = Bukkit.createInventory(null,36,"Menu");
        inv.clear();
        for(int c = 0; c <= data2.size()-1 ; c++){
            if(data2.get(c) == "BD"){
                inv.setItem(c,BD);
            }if(data2.get(c) == "BD2"){
                inv.setItem(c,BD2);
            }if(data2.get(c) == "Info"){
                inv.setItem(c,info);
            }if(data2.get(c) == "TP1"){
                inv.setItem(c,tp1);
            }if(data2.get(c) == "TP2"){
                inv.setItem(c,tp2);
            }if(data2.get(c) == "chall"){
                inv.setItem(c,chatmode1);
            }if(data2.get(c) == "chcty"){
                inv.setItem(c,chatmode2);
            }if(data2.get(c) == "ender"){
                inv.setItem(c,Ender);
            }if(data2.get(c) == "cty1"){
                inv.setItem(c,Account1);
            }if(data2.get(c) == "cty2") {
                inv.setItem(c, Account2);
            }if(data2.get(c) == "cty3") {
                inv.setItem(c, Account3);
            }if(data2.get(c) == "plinfo"){
                inv.setItem(c,playerinfo);
            }if(data2.get(c) == "help"){
                inv.setItem(c,Help);
            }if(data2.get(c) == "TPA"){
                inv.setItem(c,gettpitem(2,player));
            }if(data2.get(c) == "CH"){
                inv.setItem(c,ch);
            }
        }
        return inv;
    }
    public static void clickmenu(InventoryClickEvent e){
        if (e.getCurrentItem() != null){
            String name = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin,"menu")
                    , PersistentDataType.STRING);
            if(name == "ender"){
                if(e.getWhoClicked().getGameMode() == GameMode.CREATIVE){
                    e.getWhoClicked().openInventory(e.getWhoClicked().getEnderChest());
                }else{
                    if(e.getWhoClicked().getHealth() >= 20.0){
                        e.getWhoClicked().openInventory(e.getWhoClicked().getEnderChest());
                    }else{
                        e.getWhoClicked().sendMessage("§8[§7System§8] §7体力を最大まで回復させてから使用してください。");
                    }
                }
            }if(name == "cty1"){
                Playerdata.chengchatmode((Player) e.getWhoClicked(),0);
                e.getWhoClicked().sendMessage("§8[§7System§8] §7ChatMode §8(§fAll§8)");
                e.getWhoClicked().closeInventory();
            }if(name == "cty2"){
                Integer chatmode = Playerdata.getCountrychat((Player) e.getWhoClicked());
                if(chatmode == 20){
                    e.getWhoClicked().sendMessage("§8[§7System§8] §7あなたは現在国に所属していません");
                    e.getWhoClicked().closeInventory();
                }else {
                    Playerdata.chengchatmode((Player) e.getWhoClicked(), Playerdata.CountryNomber((Player) e.getWhoClicked()));
                    e.getWhoClicked().sendMessage("§8[§7System§8] §7ChatMode §8(§fCountry§8)");
                    e.getWhoClicked().closeInventory();
                }
            }if(name == "act1"){
                Playerdata.chengcountry((Player) e.getWhoClicked(),1);
                Playerdata.chengchatmode((Player) e.getWhoClicked(),0);
                e.getWhoClicked().sendMessage("§8[§7System§8] §7アカウントを変更しました。 §8(§fNo.1§8)");
                e.getWhoClicked().closeInventory();
            }if(name == "act2"){
                Playerdata.chengcountry((Player) e.getWhoClicked(),2);
                Playerdata.chengchatmode((Player) e.getWhoClicked(),0);
                e.getWhoClicked().sendMessage("§8[§7System§8] §7アカウントを変更しました。 §8(§fNo.2§8)");
                e.getWhoClicked().closeInventory();
            }if(name == "act3"){
                Playerdata.chengcountry((Player) e.getWhoClicked(),3);
                e.getWhoClicked().sendMessage("§8[§7System§8] §7アカウントを変更しました。 §8(§fNo.3§8)");
                Playerdata.chengchatmode((Player) e.getWhoClicked(),0);
                e.getWhoClicked().closeInventory();
            }if(name == "info"){
                e.getWhoClicked().sendMessage(Countrydata.getCountryInfo(
                        Playerdata.getNowCountry((Player) e.getWhoClicked())));
                e.getWhoClicked().closeInventory();
            }if(name == "tp1"){
                playertelepoat.telepoatPlayer((Player) e.getWhoClicked(),0);
                e.getWhoClicked().closeInventory();
            }if(name == "tp2"){
                playertelepoat.telepoatPlayer((Player) e.getWhoClicked(),1);
                e.getWhoClicked().closeInventory();
            }if(name == "back2"){
                if(kakusi.keySet().contains(e.getWhoClicked().getUniqueId().toString())){
                    Integer i = kakusi.get(e.getWhoClicked().getUniqueId().toString());
                    i++;
                    if(i >= 2000){
                        i = 0;
                        ItemStack playerskull = new ItemStack(Material.PLAYER_HEAD,1); // (short) SkullType.PLAYER.ordinal()
                        SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
                        meta.setOwningPlayer((OfflinePlayer) e.getWhoClicked());
                        playerskull.setItemMeta(meta);
                        e.getWhoClicked().getInventory().addItem(playerskull);
                        InputStream stream = Potato.clname.getResourceAsStream("/sample/Meitetu.yml");
                        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                        YamlConfiguration yml = new YamlConfiguration();
                        List<Player> data = new ArrayList<Player>();
                        data.add((Player) e.getWhoClicked());
                        try {
                            yml.load(br);
                            music.music(yml, data);
                        } catch (IOException es) {
                            es.printStackTrace();
                        } catch (InvalidConfigurationException es) {
                            es.printStackTrace();
                        }
                    }
                    kakusi.put(e.getWhoClicked().getUniqueId().toString(),i);
                }else{
                    kakusi.put(e.getWhoClicked().getUniqueId().toString(),1);
                }
            }if(name == "help"){
                help.sendhelp(0,(Player) e.getWhoClicked());
                e.getWhoClicked().closeInventory();
            }if(name == "playerinfo"){
                if(e.getWhoClicked() instanceof Player) {
                    Player player = (Player) e.getWhoClicked();
                    String message = "§8=*=*=*=*= §7Info §8=*=*=*=*=\n";
                    message = message + "§7名前 §8>>§7§l" + player.getDisplayName() + "\n";
                    if (Playerdata.getCountrytag(player, 1).contains("null") == false) {
                        message = message + "§8=*=*=*= §7Account No.1 §8=*=*=*=\n";
                        message = message + Countrydata.getlowInfo(Playerdata.getCountrytag(player, 1));
                    }
                    if (Playerdata.getCountrytag(player, 2).contains("null") == false) {
                        message = message + "\n§8=*=*=*= §7Account No.2 §8=*=*=*=\n";
                        message = message + Countrydata.getlowInfo(Playerdata.getCountrytag(player, 2));
                    }
                    if (Playerdata.getCountrytag(player, 3).contains("null") == false) {
                        message = message + "\n§8=*=*=*= §7Account No.3 §8=*=*=*=\n";
                        message = message + Countrydata.getlowInfo(Playerdata.getCountrytag(player, 3));
                    }
                    player.sendMessage(message);
                    e.getWhoClicked().closeInventory();
                }
            }if(name == "tpa"){
                Integer tps = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Potato.plugin,"tpa")
                        , PersistentDataType.INTEGER);
                Player player = (Player) e.getWhoClicked();
                if (Countrydata.getCountryTP(Playerdata.getNowCountry(player), tps - 1) != null) {
                    playertelepoat.telepoatPlayer(player,tps);
                    e.getWhoClicked().closeInventory();
                }
            }if(name == "ch"){
                e.getWhoClicked().openInventory(channelmenu.chatmenu((Player)e.getWhoClicked()));
            }
        }
        e.setCancelled(true);
    }

    public static ItemStack gettpitem(Integer i , Player player){
        if(i != 0) {
            ItemStack stack = new ItemStack(Material.REDSTONE);
            ItemMeta meta = stack.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName("§7§lTeleport §8§l[§7§lNo.3§8§l]");
            lore.clear();
            lore.add("§8§l>> §7§lRightClick");
            if(Playerdata.getNowCountry(player).contains("null") == false){
                if(Countrydata.getTPsize(Playerdata.getNowCountry(player)) >= i - 1) {
                    if (Countrydata.getCountryTP(Playerdata.getNowCountry(player), i - 1) != null) {
                        lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getNowCountry(player)));
                    } else {
                        lore.add("§7§l国家: " + Countrydata.getCountryName(Playerdata.getNowCountry(player)));
                        lore.add("§fTPが設定されていません");
                    }
                }else{
                    return null;
                }
            }else{
                return null;
            }

            meta.setLore(lore);
            meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"menu") , PersistentDataType.STRING, "tpa");
            meta.getPersistentDataContainer().set(new NamespacedKey(Potato.plugin,"tpa") , PersistentDataType.INTEGER, i);
            stack.setItemMeta(meta);
            return stack;
        }else{
            return null;
        }
    }
}
