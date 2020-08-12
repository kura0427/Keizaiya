package keizaiya.second.file.shop;

import keizaiya.second.file.Yamlfile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class shop {

    public static List<shopdata> shoplist = new ArrayList<>();

    public static void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("chestshop")) {
                if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("Create")){
                        if(args.length == 2){
                            shopdata shopdata = new shopdata(player.getUniqueId().toString(),args[1],getnewtag());
                            System.out.println(shopdata.gettag());
                            addshop(shopdata);
                            updatefile();
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
                if(yml.get(key) instanceof shopdata) {
                    String tag = yml.getString(key + ".tag");
                    String uuid = yml.getString(key + ".owner");
                    String pass = yml.getString(key + ".pass");
                    ItemStack stack = (ItemStack) yml.get(key + ".product");
                    Integer aom = yml.getInt(key + ".productaom");
                    ItemStack stack1 = (ItemStack) yml.get(key + ".give");
                    Integer aom2 = yml.getInt(key + ".giveaom");
                    Location location = (Location) yml.get(key + ".location");
                    shopdata shopdatas = new shopdata(uuid,pass,tag);
                    shopdatas.setGive(stack1);
                    shopdatas.setGiveaom(aom2);
                    shopdatas.setProduct(stack);
                    shopdatas.setProductaom(aom);
                    shopdatas.setLocation(location);
                    shoplist.add(shopdatas);
                }
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

    public static void addshop(shopdata data){
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
        Yamlfile.Saveyaml(file,yml);
    }
}