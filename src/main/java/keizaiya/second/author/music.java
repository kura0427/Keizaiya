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
        data = data.replaceAll("[\r \n]", "");
        String[] data2 = data.split("[;(){}]");
        System.out.println(data);
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
                        System.out.println(hightdata);
                        if (hightdata2.length == 3) {
                            try {
                                Integer data01 = Integer.parseInt(hightdata2[1]);
                                Float pith2 = pith.get(data01);
                                Float valum = Float.valueOf(hightdata2[2]) / 100;
                                Sound gakkisan = gakki.get(hightdata2[0]);
                                for (Player player : playermainlist) {
                                    player.playSound(player.getLocation(), gakkisan, SoundCategory.MUSIC, (float)valum, (float)pith2);
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
        pith.put(0, (float) 0.5);pith.put(13, (float) 1.059463);
        pith.put(1, (float) 0.529732);pith.put(14, (float) 1.122462);
        pith.put(2, (float) 0.561231);pith.put(15, (float) 1.189207);
        pith.put(3, (float) 0.594604);pith.put(16, (float) 1.259921);
        pith.put(4, (float) 0.629961);pith.put(17, (float) 1.334840);
        pith.put(5, (float) 0.667420);pith.put(18, (float) 1.414214);
        pith.put(6, (float) 0.707107);pith.put(19, (float) 1.498307);
        pith.put(7, (float) 0.749154);pith.put(20, (float) 1.587401);
        pith.put(8, (float) 0.793701);pith.put(21, (float) 1.681793);
        pith.put(9, (float) 0.840896);pith.put(22, (float) 1.781797);
        pith.put(10, (float) 0.890899);pith.put(23, (float) 1.887749);
        pith.put(11, (float) 0.943874);pith.put(24, (float) 2.0);
        pith.put(12, (float) 1);

        gakki.clear();
        gakki.put("CC",Sound.BLOCK_NOTE_BLOCK_CHIME);
        gakki.put("DC",Sound.BLOCK_NOTE_BLOCK_HARP);
        gakki.put("FL",Sound.BLOCK_NOTE_BLOCK_FLUTE);
        gakki.put("SD",Sound.BLOCK_NOTE_BLOCK_SNARE);
        gakki.put("BS",Sound.BLOCK_NOTE_BLOCK_BASS);
        gakki.put("BD",Sound.BLOCK_NOTE_BLOCK_BASEDRUM);
        gakki.put("CS",Sound.BLOCK_NOTE_BLOCK_HAT);
        gakki.put("BE",Sound.BLOCK_NOTE_BLOCK_BELL);

    }
}
