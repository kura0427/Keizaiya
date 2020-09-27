package keizaiya.second.author;

import keizaiya.second.Potato;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class music {
    private static Map<Integer,Float> pith = new HashMap<>();
    private static Map<String, Sound> gakki = new HashMap<>();

    public static void music(YamlConfiguration yml , List<Player> playerList){
        setdata();
        Integer yeah = yml.getInt("tick");
        String data = yml.getString("data");
        String[] data2 = data.split(";");
        new BukkitRunnable(){
            String[] maindata = data2;
            List<Player> playermainlist = playerList;
            Integer now = 0;
            @Override
            public void run(){
                if( now < data2.length) {
                    String[] lowdata2 = data2[now].split(":");
                    for (String hightdata : lowdata2) {
                        String[] hightdata2 = hightdata.split(",");
                        if (hightdata2.length == 3) {
                            try {
                                Integer data01 = Integer.parseInt(hightdata2[1]);
                                Float pith2 = pith.get(data01);
                                Float valum = Float.valueOf(hightdata2[2]) / 100;
                                Sound gakkisan = gakki.get(hightdata2[0]);
                                for (Player player : playermainlist) {
                                    player.playSound(player.getLocation(), gakkisan, SoundCategory.BLOCKS, (float)valum, (float)pith2);
                                }
                            } catch (Exception e) { }
                        }
                    }
                }else{
                    cancel();
                }
                now++;
            }
        }.runTaskTimer(Potato.plugin,0,yeah);
    }

    public static void setdata(){
        pith.clear();
        pith.put(0, (float) 0.5);pith.put(13, (float) 1.05);
        pith.put(1, (float) 0.53);pith.put(14, (float) 1.12);
        pith.put(2, (float) 0.56);pith.put(15, (float) 1.18);
        pith.put(3, (float) 0.59);pith.put(16, (float) 1.25);
        pith.put(4, (float) 0.63);pith.put(17, (float) 1.33);
        pith.put(5, (float) 0.67);pith.put(18, (float) 1.41);
        pith.put(6, (float) 0.71);pith.put(19, (float) 1.5);
        pith.put(7, (float) 0.74);pith.put(20, (float) 1.6);
        pith.put(8, (float) 0.79);pith.put(21, (float) 1.68);
        pith.put(9, (float) 0.84);pith.put(22, (float) 1.78);
        pith.put(10, (float) 0.88);pith.put(23, (float) 1.89);
        pith.put(11, (float) 0.93);pith.put(24, (float) 2.0);
        pith.put(12, (float) 0.99);

        gakki.clear();
        gakki.put("CC",Sound.BLOCK_NOTE_BLOCK_CHIME);
        gakki.put("DC",Sound.BLOCK_NOTE_BLOCK_HARP);

    }
}
