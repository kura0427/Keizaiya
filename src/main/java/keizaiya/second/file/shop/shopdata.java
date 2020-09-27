package keizaiya.second.file.shop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class shopdata {
    private Location location;
    private String owner;
    private String pass;
    private ItemStack product;
    private Integer productaom;
    private ItemStack give;
    private Integer giveaom;
    private String tag;
    private Location kanban;

    public shopdata(String uuid, String passs, String tags){
        owner = uuid;
        pass = passs;
        tag = tags;
        product = new ItemStack(Material.AIR);
        productaom = 0;
        give = new ItemStack(Material.AIR);
        giveaom = 0;
        location = new Location(Bukkit.getWorlds().get(0),0,0,0);
        kanban = new Location(Bukkit.getWorlds().get(0),0,0,0);
    }

    public void setLocation(Location locations){
        location = locations;
    }
    public void setPass(String name){
        pass = name;
    }
    public void setProduct(ItemStack name){
        product = name;
    }
    public void setProductaom(Integer data){
        productaom = data;
    }
    public void setGive(ItemStack stack){
        give = stack;
    }
    public void setGiveaom(Integer integer){
        giveaom = integer;
    }
    public void setKanban(Location data){ kanban = data;}

    public String getOwner(){
        return owner;
    }
    public String getPass(){
        return pass;
    }
    public String gettag(){
        return tag;
    }
    public ItemStack getProduct(){
        return product;
    }
    public Integer getProductaom(){
        return productaom;
    }
    public ItemStack getGive(){ return give; }
    public Integer getGiveaom(){
        return giveaom;
    }
    public Location getLocation(){
        return location;
    }
    public Location getKanban(){ return kanban;}



}
