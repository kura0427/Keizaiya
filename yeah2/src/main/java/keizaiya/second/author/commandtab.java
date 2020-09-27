package keizaiya.second.author;

import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.player.Playerdata;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class commandtab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> cmdlist = new ArrayList<>();
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 1){
                cmdlist = new ArrayList<>();
                if(args[0].length() <= 2){
                    if("tp".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("Tp");
                    }
                }if(args[0].length() <= 4){
                    if("item".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("item");
                    }if("chat".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("chat");
                    }
                }if(args[0].length() <= 6){
                    if("create".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("Create");
                    }if("invite".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("Invite");
                    }if("remove".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("remove");
                    }if("delete".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("delete");
                    }
                }if(args[0].length() <= 8){
                    if("nickname".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("nickname");
                    }
                }if(args[0].length() <= 9){
                    if("reconsent".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("REconsent");
                    }if("inconsent".substring(0,args[0].length()).contains(args[0])) {
                        cmdlist.add("Inconsent");
                    }
                }if(args[0].length() <= 10) {
                    if ("permission".substring(0, args[0].length()).contains(args[0])) {
                        cmdlist.add("permission");
                    }
                }if("religion".contains(args[0])) {
                    cmdlist.add("religion");
                }if("promote".contains(args[0])) {
                    cmdlist.add("promote");
                }
                return cmdlist;
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("invite")){
                    cmdlist = new ArrayList<>();
                    for(Player p: Bukkit.getOnlinePlayers()){
                        if(p.getDisplayName().contains(args[1])) {
                            cmdlist.add(p.getDisplayName());
                        }
                    }
                    return cmdlist;
                }else if(args[0].equalsIgnoreCase("tp")){
                    cmdlist = new ArrayList<>();
                    if("set".contains(args[1])) {
                        cmdlist.add("set");
                    }if("1".contains(args[1])) {
                        cmdlist.add("1");
                    }if("2".contains(args[1])) {
                        cmdlist.add("2");
                    }
                    return cmdlist;
                }else if(args[0].equalsIgnoreCase("item")){
                    cmdlist = new ArrayList<>();
                    if("card".contains(args[1])) {
                        cmdlist.add("card");
                    }if("country".contains(args[1])) {
                        cmdlist.add("country");
                    }if("banner".contains(args[1])) {
                        cmdlist.add("banner");
                    }
                    return cmdlist;
                }else if(args[0].equalsIgnoreCase("remove")){
                    cmdlist = new ArrayList<>();
                    for(Player p: Countrydata.getOnlinemember(Playerdata.getNowCountry(player))){
                        if(p.getDisplayName().contains(args[1])) {
                            cmdlist.add(p.getDisplayName());
                        }
                    }
                    return cmdlist;
                }else if(args[0].equalsIgnoreCase("remove")){
                    cmdlist = new ArrayList<>();
                    for(Player p: Countrydata.getOnlinemember(Playerdata.getNowCountry(player))){
                        if(p.getDisplayName().contains(args[1])) {
                            cmdlist.add(p.getDisplayName());
                        }
                    }
                    return cmdlist;
                }
            }else if(args.length == 3){
                if(args[0].equalsIgnoreCase("item")){
                    if(args[1].equalsIgnoreCase("banner")){
                        if("set".contains(args[2])) {
                            cmdlist.add("set");
                        }if("get".contains(args[2])) {
                            cmdlist.add("get");
                        }
                        return cmdlist;
                    }
                }
            }
        }
        return null;
    }
}
