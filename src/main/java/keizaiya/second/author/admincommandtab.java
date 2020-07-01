package keizaiya.second.author;

import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.country.ideology;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class admincommandtab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> cmdlist = new ArrayList<>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1){
                cmdlist = new ArrayList<>();
                cmdlist.add("settp");
                cmdlist.add("setname");
                cmdlist.add("ideology");
                cmdlist.add("card");
                cmdlist.add("^^");
                cmdlist.add("country");
                return cmdlist;
            }else if(args.length >= 2){
                if(args[0].equalsIgnoreCase("ideology")){
                    if(args.length == 2) {
                        cmdlist = new ArrayList<>();
                        cmdlist.add("getname");
                        cmdlist.add("getcard");
                        cmdlist.add("list");
                        return cmdlist;
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("getname") || args[1].equalsIgnoreCase("getcard")) {
                            return ideology.getlist();
                        }
                    }
                }else if(args[0].equalsIgnoreCase("card")){
                    if(args.length == 2) {
                        cmdlist = new ArrayList<>();
                        cmdlist.add("country");
                        cmdlist.add("ideology");
                        return cmdlist;
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("ideology")){
                            return ideology.getlist();
                        }
                    }
                }else if(args[0].equalsIgnoreCase("country")){
                    if(args.length == 2) {
                        cmdlist = new ArrayList<>();
                        cmdlist.add("info");
                        cmdlist.add("list");
                        cmdlist.add("setPoint");
                        cmdlist.add("getPoint");
                        return cmdlist;
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("info") || args[1].equalsIgnoreCase("getpoint")){
                            return Countrydata.gettaglist();
                        }
                    }else if(args.length == 4){
                        if(args[1].equalsIgnoreCase("setpoint")){
                            return Countrydata.gettaglist();
                        }
                    }
                }
            }
        }
        return null;
    }
}
