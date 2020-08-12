package keizaiya.second;

import keizaiya.second.armmy.armmer;
import keizaiya.second.armmy.armmy;
import keizaiya.second.author.*;
import keizaiya.second.chat.*;
import keizaiya.second.chat.channel.channel;
import keizaiya.second.chat.channel.channelcom;
import keizaiya.second.chat.channel.channelmenu;
import keizaiya.second.connection.tuusin;
import keizaiya.second.file.Admin.Admin;
import keizaiya.second.file.Admin.adminfile;
import keizaiya.second.file.Admin.keepinventory;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.country.countrycommand;
import keizaiya.second.file.country.ideology;
import keizaiya.second.file.country.point;
import keizaiya.second.file.player.Playerdata;
import keizaiya.second.file.servermember;
import keizaiya.second.file.shop.shop;
import keizaiya.second.inventory.menu;
import keizaiya.second.item.superpixcel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.ServerSocket;
import java.util.*;


public final class Potato extends JavaPlugin implements Listener {

    public static Plugin plugin;
    public static FileConfiguration config;
    public static Class clname;
    public static Map<String,String> countrylist = new HashMap<>();
    public static String joinmessages = "";
    public static Thread tuusins = null;

    @Override
    public void onEnable() {
        System.out.println("経済屋プラグインSecond");
        saveDefaultConfig();
        config = getConfig();
        clname = getClass();
        plugin = getPlugin(this.getClass());

        File file = new File("KeizaiyaMain");
        if(file.exists() == false){
            if(file.mkdirs() == true){ System.out.println("MainFile Create Sucsess");
            }else{System.out.println("MainFile Create miss");}
        }

        getCommand("country").setTabCompleter(new commandtab());
        getCommand("admin").setTabCompleter(new admincommandtab());
        getCommand("channel").setTabCompleter(new channelcomtab());

        point.updatelist();
        adminfile.checkAdmindata();
        channel.CheckFile();
        chengewoad.checkfile();
        channel.updatechannel();
        shop.updatefile();

        try {
            ServerSocket socket = new ServerSocket(36846);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tuusins = tuusin.startcom();
        tuusins.start();
        tuusin.checktps();

        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        tuusin.stop = true;
        tuusins.stop();
        if(tuusin.kidou){
            tuusin.sendserver("server&close@");
        }
        if(tuusin.socket != null){
            try {
                tuusin.socket.close();
                System.out.println("closed sucsess");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent event){
        if(Playerdata.CheckPlayerDataFile(event.getPlayer()) == false){
            Playerdata.CreatePlayerdata(event.getPlayer());
        }
        servermember.checkmember(event.getPlayer());
        event.setJoinMessage(joinmessage.joinmessage()
        .replace("&","§").replace("%Player%",event.getPlayer().getDisplayName()));
        System.out.println(joinmessages.length());
        if(joinmessages.length() > 1) {
            String message = ("&8[&e&lお知らせ&8]&f" + joinmessages).replace("&", "§");
            event.getPlayer().sendMessage(message);
        }
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e){
        if(e.getView().getTitle() == "Menu"){ menu.clickmenu(e); }
        if(e.getView().getTitle() == "Channel"){ channelmenu.clickmenu(e); }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
        chat.chat(e);
    }

    @EventHandler
    public void Break(BlockBreakEvent event){
        if(point.checkBlock(event.getBlock().getType())){
            Countrydata.addBreakblock(Playerdata.getNowCountry(event.getPlayer()),event.getPlayer());
        }
        superpixcel.breakblocktoSP(event);
    }
    @EventHandler
    public void click(PlayerInteractEvent e){
        ideology.clickideologycard(e);
        authorclick.authorclick(e);
        armmy.used(e);
        armmer.click(e);
        if(e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType().equals(Material.CHEST)) {
                Chest chest = (Chest) e.getClickedBlock().getState();
                e.getPlayer().openInventory(chest.getInventory());
                System.out.println(chest);
            }
        }
    }

    @EventHandler
    public void Entitykill(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null) {
            if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                Countrydata.addEntity(Playerdata.getNowCountry(e.getEntity().getKiller()), e.getEntity().getKiller());
            }
        }
    }

    @EventHandler
    public void Playerdeath(PlayerDeathEvent e){
        keepinventory.deathPlayer(e);
    }

    @EventHandler
    public void damegeEvent(EntityDamageByEntityEvent e){
        armmer.damege(e);
    }

    @EventHandler
    public void kickkorobka(PlayerKickEvent event){
        System.out.println(event.getPlayer().getUniqueId().toString());
        if(event.getPlayer().getUniqueId().toString().contains("c09517d3-08e7-459e-adcc-116c435ae53e")){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendTitle("korobka kicked by Server^^","",0,60,20);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        countrycommand.onCountrycommnand(sender,cmd,commandLabel,args);
        Admin.onAdmincommnand(sender,cmd,commandLabel,args);
        authorcommand.onauthorcommnand(sender,cmd,commandLabel,args);
        chatcommand.onauthorcommnand(sender,cmd,commandLabel,args);
        channelcom.onchannelcommnand(sender,cmd,commandLabel,args);
        shop.onCommand(sender,cmd,commandLabel,args);
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("menu")) {
                if (sender instanceof Player) {
                    menu.opengui(p);
                }
            }if(cmd.getName().equalsIgnoreCase("test")) {
                if (args.length == 0) {
                    InputStream stream = Potato.clname.getResourceAsStream("/sample/Meitetu.yml");
                    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                    YamlConfiguration yml = new YamlConfiguration();
                    try {
                        yml.load(br);
                        music.music(yml, (List<Player>) Bukkit.getOnlinePlayers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                }else if(args.length == 1){
                    //InputStream stream = Potato.clname.getResourceAsStream("/sample/Avicii-Waiting_for_love.yml");
                    File file = new File("KeizaiyaMain/Avicii-Waiting_for_love.yml");
                    //BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                    YamlConfiguration yml = new YamlConfiguration();
                    try {
                        yml.load(file);
                        music.music(yml, (List<Player>) Bukkit.getOnlinePlayers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                }else {
                    File file = new File("KeizaiyaMain/test.yml");
                    YamlConfiguration yml = new YamlConfiguration();
                    try {
                        yml.load(file);
                        music.music(yml, (List<Player>) Bukkit.getOnlinePlayers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InvalidConfigurationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}
