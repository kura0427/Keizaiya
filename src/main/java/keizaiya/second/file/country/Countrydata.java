package keizaiya.second.file.country;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import keizaiya.second.file.player.Playerdata;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.*;

public class Countrydata {

    public static Map<String,List<Player>> repuestlist = new HashMap<>();

    public static boolean CreateCountry(Player player, String Name , String nickname){
        CheckFile();
        System.out.println(Playerdata.getCountrytag(player,Playerdata.CountryNomber(player)));
        if(nickname.length() >= 8){
            nickname = nickname.substring(0,8);
        }
        if(Playerdata.getCountrytag(player,Playerdata.CountryNomber(player)).contains("Cnt") == false) {
            YamlConfiguration yml = new YamlConfiguration();
            try {
                InputStream stream = Potato.clname.getResourceAsStream("/sample/countrydata.yml");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                yml.load(br);
                yml.set("country", Name);
                yml.set("head", player.getUniqueId().toString());
                yml.set("member", new ArrayList<String>(Arrays.asList(player.getUniqueId().toString())));
                yml.set("nickname", nickname);
                String tag = getNewtag();
                savecounty(tag, yml);
                updateCountrylist();
                Playerdata.setCountrytag(player,Playerdata.CountryNomber(player),tag);
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendMessage("§8[§7Broadcast§8] §f" + player.getDisplayName() + " §7が §f" + Name + " §7を建国しました");
                }
                player.sendMessage("§8[§7System§8] §7建国しました。Discordで報告してください。(§f" + Name + "§7)");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }else{
            player.sendMessage("§8[§7System§8] 既に国に所属しています。");
        }
        return false;

    }

    public static void CheckFile(){
        File file = new File("KeizaiyaMain/Country/");
        if(file.exists() == false){
            file.mkdirs();
        }
    }

    private static YamlConfiguration loadCountry(String tag){
        File file = new File("KeizaiyaMain/Country/" + tag + ".yml");
        return Yamlfile.loadyaml(file);
    }

    private static void savecounty(String tag , YamlConfiguration yml){
        File file = new File("KeizaiyaMain/Country/" + tag + ".yml");
        Yamlfile.Saveyaml(file,yml);
    }

    public static void updateCountrylist(){
        File file = new File("KeizaiyaMain/Country/");
        File[] listfile = file.listFiles();
        if(listfile.length != 0) {
            Potato.countrylist.clear();
            for (int i = 0; i < listfile.length; i++) {
                YamlConfiguration yml = new YamlConfiguration();
                yml = Yamlfile.loadyaml(listfile[i]);
                String tag = listfile[i].getName().replace(".yml","");
                String Name = yml.getString("country");
                Potato.countrylist.put(tag, Name);
            }
        }
    }
    public static String getNewtag(){
        File file = new File("KeizaiyaMain/Country/");
        File[] listfile = file.listFiles();
        if(listfile != null) {
            return "Cnt" + String.valueOf(listfile.length);
        }else{return "Cnt0";}
    }

    public static String getTAG(String CountyNamoe){
        if(Potato.countrylist != null) {
            if (Potato.countrylist.size() != 0) {
                for (String Key : Potato.countrylist.keySet()) {
                    if (Potato.countrylist.get(Key).contains(CountyNamoe)) {
                        return Key;
                    }
                }
            }
        }
        return null;
    }

    public static String getCountryName(String TAG){
        updateCountrylist();
        if(Potato.countrylist.containsKey(TAG)){
            String name = Potato.countrylist.get(TAG).replace("&","§");
            return name;
        }
        return null;
    }

    public static String getCountryInfo(String tag){
        if(tag.contains("null") == false) {
            YamlConfiguration yml = loadCountry(tag);
            String message = "§8=*=*=*=*= §7Info §8=*=*=*=*=";
            message = message + "\n国名 >> §7" + yml.getString("country");
            message = message + "\n§8国家長 >> §7" + Playerdata.getPlayername(yml.getString("head"));
            message = message + "\n§8主義 >> §7" + ideology.getideologyname(yml.getString("ideology"));
            message = message + "\n§8宗教 >> §7" + yml.getString("religion");
            List<?> list = yml.getStringList("member");
            String membermessage = "";
            for (String member : yml.getStringList("member")) {
                if (membermessage != "") {
                    membermessage = membermessage + " , ";
                }
                membermessage = membermessage + Playerdata.getPlayername(member);
            }
            message = message + "\n§8国民 >> §7" + membermessage;
            message = message + "\n§8人数 >> §7" + String.valueOf(yml.getStringList("member").size());
            message = message + "\n§8ポイント >> §7" + String.valueOf(yml.getInt("point"));
            return message;
        }else{return "あなたは現在国に所属していません。";}
    }

    public static String getlowInfo(String tag){
        if(tag.contains("null") == false) {
            YamlConfiguration yml = loadCountry(tag);
            String message = "";
            message = message + "\n国名 >> §7" + yml.getString("country");
            message = message + "\n§8国家長 >> §7" + Playerdata.getPlayername(yml.getString("head"));
            message = message + "\n§8主義 >> §7" + ideology.getideologyname(yml.getString("ideology"));
            message = message + "\n§8宗教 >> §7" + yml.getString("religion");
            return message;
        }else{ return "Null";}
    }

    public static List<Player> getOnlinemember(String tag){
        List<Player> member = new ArrayList<>();
        if(tag.contains("null") == false) {
            YamlConfiguration yml = loadCountry(tag);
            List<String> memberuuid = yml.getStringList("member");
            for (Player player : Potato.plugin.getServer().getOnlinePlayers()) {
                if (memberuuid.contains(player.getUniqueId().toString())) {
                    member.add(player);
                }
            }
        }
        return member;
    }

    public static void addBreakblock(String tag , Player player) {
        if (tag.contains("null") == false) {
            YamlConfiguration yml = loadCountry(tag);
            Integer breakblock = yml.getInt("nowbreak") + 1;
            if (breakblock >= Potato.config.getInt("pointparbreak")) {
                breakblock = 0;
                Integer point = yml.getInt("point") + 1;
                yml.set("point",point);

            }
            yml.set("nowbreak",breakblock);
            savecounty(tag, yml);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                    "§8[§7Point§8] §7" + breakblock + "/" + Potato.config.getInt("pointparbreak")));
        }
    }

    public static void addEntity(String tag,Player player){
        if (tag.contains("null") == false) {
            YamlConfiguration yml = loadCountry(tag);
            Integer breakblock = yml.getInt("nowbreak") + 1;
            if (breakblock >= Potato.config.getInt("pointparbreak")) {
                breakblock = 0;
                Integer point = yml.getInt("point") + 1;
                yml.set("point",point);
            }
            yml.set("nowbreak",breakblock);
            savecounty(tag, yml);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                    "§8[§7Point§8] §7" + breakblock + "/" + Potato.config.getInt("pointparbreak")));
        }
    }

    public static void addmember(String tag ,Player player){
        YamlConfiguration yml = loadCountry(tag);
        List<String> member = yml.getStringList("member");
        member.add(player.getUniqueId().toString());
        yml.set("member",member);
        savecounty(tag,yml);
    }

    public static void deleatmember(String tag , Player player){
        YamlConfiguration yml = loadCountry(tag);
        if(player.getUniqueId().toString() != yml.getString("head")){
            List<String> member = yml.getStringList("member");
            if(member.contains(player.getUniqueId().toString())){
                member.remove(player.getUniqueId().toString());
                yml.set("member",member);
                savecounty(tag,yml);
            }
        }
    }
    public static boolean checkmember(String tag , Player player){
        YamlConfiguration yml = loadCountry(tag);
        List<String> member = yml.getStringList("member");
        return member.contains(player.getUniqueId().toString());
    }

    public static List<String> getpermissionList(Player player, String tag){
        if(tag.contains("null") == false) {
            if (getOnlinemember(tag).contains(player)) {
                YamlConfiguration yml = loadCountry(tag);
                String uuid = player.getUniqueId().toString();
                List<String> premission = new ArrayList<>();
                premission.add("member");
                if (yml.getString("head").contains(uuid)) {
                    premission.add("head");
                }
                if (yml.getStringList("permission.invite").contains(uuid)) {
                    premission.add("invite");
                }
                if (yml.getStringList("permission.chat").contains(uuid)) {
                    premission.add("chat");
                }
                if (yml.getStringList("permission.permission").contains(uuid)) {
                    premission.add("permission");
                }
                if (yml.getStringList("permission.item").contains(uuid)) {
                    premission.add("item");
                }
                if (yml.getStringList("permission.tp").contains(uuid)) {
                    premission.add("tp");
                }
                if (yml.getStringList("permission.remove").contains(uuid)) {
                    premission.add("remove");
                }
                return premission;
            }
        }
        return null;
    }
    public static boolean checkpermission(Player player,String permissonname){
        List<String> list = getpermissionList(player,Playerdata.getNowCountry(player));
        if(list != null) {
            if (list.contains("head")) {
                return true;
            }
            if (list.contains(permissonname)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkpermissioncountry(Player player,String permissonname , String TAG){
        List<String> list = getpermissionList(player,TAG);
        if(list != null) {
            if (list.contains("head")) {
                return true;
            }
            if (list.contains(permissonname)) {
                return true;
            }
        }
        return false;
    }


    public static void setpermisson(Player player , String permissonname , String TAG){
        YamlConfiguration yml = loadCountry(TAG);
        if(yml.getKeys(true).contains("permission." + permissonname)) {
            List<String> pemin = yml.getStringList("permission." + permissonname);
            if(pemin.contains(player.getUniqueId().toString()) == false) {
                pemin.add(player.getUniqueId().toString());
            }else{
                pemin.remove(player.getUniqueId().toString());
            }
            yml.set("permission." + permissonname,pemin);
            savecounty(TAG,yml);
        }else{System.out.println("名前が違います。");}
    }

    public static void setCountryTP(Location location , String TAG , Integer number){

        YamlConfiguration yml = loadCountry(TAG);
        if(number == 0) {
            yml.set("TP.world", location.getWorld().getName());
            yml.set("TP.X", location.getX());
            yml.set("TP.Y", location.getY());
            yml.set("TP.Z", location.getZ());
        }else{
            yml.set("TP" + number + ".world", location.getWorld().getName());
            yml.set("TP" + number + ".X", location.getX());
            yml.set("TP" + number + ".Y", location.getY());
            yml.set("TP" + number + ".Z", location.getZ());
        }
        savecounty(TAG,yml);
    }
    public static Location getCountryTP(String TAG , Integer number){
        if(TAG.contains("null") == false) {
            YamlConfiguration yml = loadCountry(TAG);
            if(number == 0) {
                if (yml.getKeys(true).contains("TP")) {
                    Location location = new Location(Bukkit.getWorld(yml.getString("TP.world")), yml.getDouble("TP.X")
                            , yml.getDouble("TP.Y"), yml.getDouble("TP.Z"));
                    return location;
                }
            }else {
                if (yml.getKeys(true).contains("TP" + number)) {
                    Location location = new Location(Bukkit.getWorld(yml.getString("TP" + number + ".world")), yml.getDouble("TP" + number + ".X")
                            , yml.getDouble("TP" + number + ".Y"), yml.getDouble("TP" + number + ".Z"));
                    return location;
                }
            }
        }
        return null;
    }

    public static void setIdeology(String TAG , String Ideology){
        YamlConfiguration yml = loadCountry(TAG);
        yml.set("ideology",Ideology);
        savecounty(TAG,yml);
    }

    public static String getIdeology(String TAG){
        YamlConfiguration yml = loadCountry(TAG);
        return yml.getString ("ideology");
    }

    public static String getnickname(String TAG){
        YamlConfiguration yml = loadCountry(TAG);
        if(yml.getKeys(true).contains("nickname")){
            return yml.getString("nickname");
        }else{
            return getCountryName(TAG);
        }
    }

    public static void setnickname(String TAG,String nickname){
        Integer yeah = checkhan(nickname);
        nickname = nickname.substring(0,yeah);
        YamlConfiguration yml = loadCountry(TAG);
        yml.set("nickname",nickname);
        savecounty(TAG,yml);
    }

    public static void setcountryname(String TAG,String nickname){
        if(nickname.length() >= 8){
            nickname = nickname.substring(0,8);
        }
        YamlConfiguration yml = loadCountry(TAG);
        yml.set("country",nickname);
        savecounty(TAG,yml);
    }

    public static void deleatcountry(String TAG , Player player){
        if(checkpermission(player,"head")) {
            YamlConfiguration yml = loadCountry(TAG);
            String head = yml.getString("head");
            yml.set("head", head + " [deleat]");
            yml.set("end", 20);
            savecounty(TAG, yml);
            Playerdata.deleatcountry(player,TAG);
            Playerdata.chengchatmode(player,0);
        }
    }

    public static void chengehead(Player player,String TAG){
        YamlConfiguration yml = loadCountry(TAG);
        yml.set("head",player.getUniqueId().toString());
        savecounty(TAG,yml);
    }

    public static void setrole(Player player , String role,String TAG){
        YamlConfiguration yml = loadCountry(TAG);
        yml.set("prefix." + player.getUniqueId().toString(),role);
        savecounty(TAG,yml);
    }
    public static String getrole(Player player,String TAG){
        YamlConfiguration yml = loadCountry(TAG);
        if(yml.getKeys(true).contains("prefix." + player.getUniqueId().toString())) {
            return yml.getString("prefix." + player.getUniqueId().toString());
        }else{
            return "";
        }
    }
    public static void setCountrypoint(String tag,Integer point){
        YamlConfiguration yml = loadCountry(tag);
        yml.set("point",point);
        savecounty(tag,yml);
    }

    public static Integer getCountrypoint(String tag){
        YamlConfiguration yml = loadCountry(tag);
        return yml.getInt("point");
    }

    public static List<String> getAllContrytag(){
        File file = new File("KeizaiyaMain/Country/");
        File[] listfile = file.listFiles();
        List<String> list = new ArrayList<>();
        for(File file2 : listfile){
            String name = file2.getName().replace(".yml","");
            YamlConfiguration yml = Yamlfile.loadyaml(file2);
            list.add( "§7" + yml.getString("country") + ":§3" +name);
        }
        return list;
    }

    public static List<String> getContrytag(){
        File file = new File("KeizaiyaMain/Country/");
        File[] listfile = file.listFiles();
        List<String> list = new ArrayList<>();
        for(File file2 : listfile){
            String name = file2.getName().replace(".yml","");
            YamlConfiguration yml = Yamlfile.loadyaml(file2);
            if(yml.getKeys(true).contains("end")){
                if(yml.getInt("end") != 20){
                    list.add("§7" + yml.getString("country") + ":§3" + name);
                }
            }else {
                list.add("§7" + yml.getString("country") + ":§3" + name);
            }
        }
        return list;
    }

    public static List<String> gettaglist(){
        File file = new File("KeizaiyaMain/Country/");
        File[] listfile = file.listFiles();
        List<String> list = new ArrayList<>();
        for(File file2 : listfile){
            String name = file2.getName().replace(".yml","");
            YamlConfiguration yml = Yamlfile.loadyaml(file2);
            if(yml.getKeys(true).contains("end")){
                if(yml.getInt("end") != 20){
                    list.add(name);
                }
            }else {
                list.add(name);
            }
        }
        return list;
    }

    public static void removepoint(String tag ,Integer number){
        Integer now = getCountrypoint(tag);
        setCountrypoint(tag,now - number);
    }


    public static Integer checkhan(String s){
        Integer yeah = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] >= 0xff61 && chars[i] <= 0xff9f) {
                yeah = yeah + 1;
            } else {
                yeah = yeah +2;
            }
            if(yeah >= 16){
                return i + 1;
            }
        }
        return s.length();
    }

    public static void setreligion(String tag , String religion){
        YamlConfiguration yml = loadCountry(tag);
        yml.set("religion",religion);
        savecounty(tag,yml);
    }

    public static String getreligion(String tag){
        YamlConfiguration yml = loadCountry(tag);
        return yml.getString("religion");
    }

    public static void setBanner(String tag , ItemStack stack){
        YamlConfiguration yml = loadCountry(tag);
        yml.set("banner",stack);
        savecounty(tag,yml);
    }

    public static ItemStack getBanner(String tag){
        YamlConfiguration yml = loadCountry(tag);
        if(yml.getKeys(true).contains("banner")){
            return (ItemStack) yml.get("banner");
        }
        return null;
    }

    public static Integer getTPsize(String tag){
        YamlConfiguration yml = loadCountry(tag);
        if(yml.getKeys(true).contains("TPsize")){
            return yml.getInt("TPsize");
        }
        return 0;
    }

    public static void setTPsize(String tag , Integer size){
        YamlConfiguration yml = loadCountry(tag);
        yml.set("TPsize",size);
        savecounty(tag,yml);
    }

    public static List<String> getTPsizelist(String tag){
        Integer tpsize = getTPsize(tag);
        List<String> tplist = new ArrayList<>();
        for(int i = 0 ; tpsize >= i ; i++){
            tplist.add(String.valueOf(i));
        }
        return tplist;

    }

    public static boolean addrepuest(String tag , Player player){
        if(getOnlinemember(tag).contains(player) == false){
            List<Player> list = new ArrayList<>();
            if(repuestlist.containsKey(tag)){
                if(repuestlist.get(tag).contains(player)){ return true;}
                list = repuestlist.get(tag);
            }
            list.add(player);
            repuestlist.put(tag,list);
            return true;
        }
        return false;
    }

    public static boolean removerepuest(String tag,Player player){
        if(repuestlist.containsKey(tag)){
            if(repuestlist.get(tag).contains(player)){
                repuestlist.get(tag).remove(player);
                return true;
            }
        }
        return false;
    }


}
