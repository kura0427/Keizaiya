package keizaiya.second.connection;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class tellrow {
    public static void sendtellraw(Location location, String json){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"data merge block " + location.getBlockX()
         + " " + location.getBlockY() + " " + location.getBlockZ() + " " + json);
    }

    public static String gettellraw(String data,String command){
        String str = "{Text2:'{\"text\":\"" + data + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"}}'}";
        return str;
    }

    public static String getsugget(String data,String command){
        String str = "{Text2:'{\"text\":\"" + data + "\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"" + command + "\"}}'}";
        return str;
    }
}
