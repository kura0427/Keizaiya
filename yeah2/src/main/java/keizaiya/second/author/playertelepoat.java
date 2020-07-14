package keizaiya.second.author;

import keizaiya.second.Potato;
import keizaiya.second.file.Admin.adminfile;
import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class playertelepoat {
    public static boolean telepoatPlayer(Player player, Integer tpnumber){
        Location tppoint2;
        String name;
        Integer tptimenow = Potato.config.getInt("TPtime",200);
        if(tpnumber == 0){
            tppoint2 = adminfile.getMainTP();
            name = adminfile.getTPname();
            if(tppoint2 == null){
                player.sendMessage("§8[§7System§8] §7テレポートポイントが設定されていません。");
                return false;}
        }else if(tpnumber >= 1){
            if(Playerdata.getNowCountry(player).contains("null") == false) {
                tppoint2 = Countrydata.getCountryTP(Playerdata.getNowCountry(player) , tpnumber - 1);
                name = Countrydata.getCountryName(Playerdata.getNowCountry(player));
            }else{
                tppoint2 = null;
                name = "";
            }
            if(tppoint2 == null){
                player.sendMessage("§8[§7System§8] §7テレポートポイントが設定されていません。");
                return false; }
        }else{
            player.sendMessage("§8[§7System§8] §7テレポートポイントが設定されていません。");
            return false;
        }
        if(player.getHealth() != 20){
            tptimenow = 600;
        }
        Integer finalTptimenow = tptimenow;
        new BukkitRunnable(){
            Player nowplayer = player;
            Location playerlocation = player.getLocation();
            Integer TPtime = finalTptimenow;
            Integer now = 0;
            Integer number = tpnumber;
            Location TPpoint = tppoint2;
            String TPname = name;
            @Override
            public void run(){
                if(now == 0){nowplayer.sendMessage("§8[§7System§8] §f" + TPname + " §7にテレポートを開始します。動かないでください");}
                now++;
                if(playerlocation.getX() != nowplayer.getLocation().getX() | playerlocation.getY() != nowplayer.getLocation().getY()
                | playerlocation.getZ() != nowplayer.getLocation().getZ() | playerlocation.getPitch() != nowplayer.getLocation().getPitch()
                | playerlocation.getYaw() != nowplayer.getLocation().getYaw()){
                    nowplayer.sendMessage("§8[§7System§8] §7テレポートをキャンセルしました");
                    this.cancel();
                }if(Potato.plugin.getServer().getOnlinePlayers().contains(nowplayer) == false){
                    System.out.println("^^");
                    this.cancel();
                }
                if(now >= TPtime){
                    nowplayer.teleport(TPpoint);
                    this.cancel();
                }
                Integer time = (TPtime - now)/20;
                nowplayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                        "§8[§7Telepoat§8] 残り §e" + time + "§8秒"));
            }
        }.runTaskTimer(Potato.plugin,0,1);
        return true;
    }

}
