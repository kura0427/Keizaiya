package keizaiya.second.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



public class chatsiliarize {

    public static void sendtuuti(Player player , String message , Integer number){
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + " {\"text\":\"" + message + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/notice set " +
                        player.getDisplayName() + " 0 " + number + "\"}" +
                        ",\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"非表示\",\"color\":\"white\"}]}}");

    }

    //"tellraw Kura_LClaudia2 {\"text\":\"" + message + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/notice " + number + "\"}}"

    public static void sendremove(Player player , Player player2){
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + " [ {\"text\":\"§8[§7System§8] §7又は選択肢をクリックしてください \"}," +
                        "{\"text\":\"§b§l[受諾]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/country REconsent " + player2.getDisplayName() + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§3完全に離脱させます\"}}" +
                        ",{\"text\":\"   \"}" +
                        ",{\"text\":\"§c§l[拒否]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/country REconsent " + player2.getDisplayName() + " cancel\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§3離脱処理を終了させます\"}}]");
    }

    public static void sendinvite(Player player){
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + " [ {\"text\":\"§8[§7System§8] §7又は選択肢をクリックしてください \"}," +
                        "{\"text\":\"§b§l[受諾]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/country INconsent\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§3国家に所属します\"}}" +
                        ",{\"text\":\"   \"}" +
                        ",{\"text\":\"§c§l[拒否]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/country INconsent cancel\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§6招待をキャンセルします\"}}]");
    }
}
