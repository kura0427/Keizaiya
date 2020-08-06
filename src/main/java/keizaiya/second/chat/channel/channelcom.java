package keizaiya.second.chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class channelcom {
    public static void onauthorcommnand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("channel")) {
                if(args.length >= 1){
                    if(args[0] == "create"){
                        if (args.length == 2){
                            channel.Createchannel(player,args[1]);
                        }
                    }else if(args[0] == "invite"){

                    }
                }
            }
        }
    }
}
