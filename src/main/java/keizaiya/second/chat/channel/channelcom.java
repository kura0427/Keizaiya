package keizaiya.second.chat.channel;

import keizaiya.second.file.player.Playerdata;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static keizaiya.second.inventory.help.gettellrawtocommand;

public class channelcom {
    public static void onchannelcommnand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("channel")) {
                if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("create")){
                        if (args.length == 2){
                            channel.Createchannel(player,args[1]);
                        }
                    }else if(args[0].equalsIgnoreCase("invite")){
                        if(args.length == 2){
                            if(Playerdata.chatmode(player) >= 1000) {
                                String data = channel.getchanneltag(Playerdata.chatmode(player));
                                if (data != null) {
                                    if (channel.getvalum(data).getOwner().contains(player.getUniqueId().toString())) {
                                        channel.addinvite(data,args[1]);
                                        player.sendMessage("招待しました。");
                                    }else{
                                        player.sendMessage("あなたには権限がありません");
                                    }
                                }else{
                                    player.sendMessage("チャンネルがありません");
                                }
                            }else{
                                player.sendMessage("チャットを招待したいチャンネルにしてください");
                            }
                        }else{
                            player.sendMessage("リスト");
                            for(String key : channel.invite.keySet()){
                                List<Player> playerList = channel.invite.get(key);
                                if(playerList.contains(player)){
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"tellraw " + player.getDisplayName() + gettellrawtocommand(
                                            channel.list.get(key).getname(),"/channel join " + key));
                                }
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("close")){
                        if(Playerdata.chatmode(player) >= 1000){
                            String data = channel.getchanneltag(Playerdata.chatmode(player));
                            if(data != null){
                                if(channel.getvalum(data).getOwner().contains(player.getUniqueId().toString())) {
                                    channel.closechannel(data, player.getUniqueId().toString());
                                    Playerdata.chengchatmode(player,0);
                                    player.sendMessage("チャンネルを閉じました");
                                }else{
                                    player.sendMessage("あなたには権限がありません。");
                                }
                            }else{
                                player.sendMessage("範囲外または指定したチャンネルはありません。");
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("join")){
                        if(args.length == 2){
                            if(channel.invite.containsKey(args[1])){
                                if(channel.invite.get(args[1]).contains(player)){
                                    channel.addmember(args[1],player.getUniqueId().toString());
                                    channel.removeinvite(args[1],player.getDisplayName());
                                    player.sendMessage("参加しました");
                                }
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("remove")){
                        if(args.length == 2){
                            if(Playerdata.chatmode(player) >= 1000) {
                                String data = channel.getchanneltag(Playerdata.chatmode(player));
                                if (data != null) {
                                    if (channel.getvalum(data).getOwner().contains(player.getUniqueId().toString())) {
                                        Player player1 = Bukkit.getPlayer(args[1]);
                                        if(player1 != null){
                                            if(player1 != player) {
                                                if (channel.getvalum(data).getMember().contains(player1.getUniqueId().toString())) {
                                                    channel.removemember(data, player1.getUniqueId().toString());
                                                    Playerdata.chengchatmode(player1, 0);
                                                    player.sendMessage("退出させました");
                                                }
                                            }else{
                                                player.sendMessage("自分がオーナーの時退出はできません");
                                            }
                                        }
                                    }
                                }
                            }
                        }else if (args.length == 1){
                            if(Playerdata.chatmode(player) >= 1000) {
                                String data = channel.getchanneltag(Playerdata.chatmode(player));
                                if (data != null) {
                                    if (channel.getvalum(data).getOwner().contains(player.getUniqueId().toString())) {
                                        player.sendMessage("自分がオーナーの時退出はできません");
                                    }else{
                                        channel.removemember(data,player.getUniqueId().toString());
                                        player.sendMessage("退出しました");
                                        Playerdata.chengchatmode(player,0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
