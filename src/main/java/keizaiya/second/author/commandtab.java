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
                cmdlist.add("Create");
                cmdlist.add("Invite");
                cmdlist.add("chat");
                cmdlist.add("permission");
                cmdlist.add("Tp");
                cmdlist.add("item");
                cmdlist.add("remove");
                cmdlist.add("delete");
                cmdlist.add("nickname");
                cmdlist.add("REconsent");
                cmdlist.add("Inconsent");
                return cmdlist;
            }else if(args.length == 2){
                if(args[0].equalsIgnoreCase("invite")){
                    cmdlist = new ArrayList<>();
                    for(Player p: Bukkit.getOnlinePlayers()){
                        cmdlist.add(p.getDisplayName());
                    }
                    return cmdlist;
                }else if(args[0].equalsIgnoreCase("tp")){
                    cmdlist = new ArrayList<>();
                    cmdlist.add("set");
                    cmdlist.add("1");
                    cmdlist.add("2");
                }else if(args[0].equalsIgnoreCase("item")){
                    cmdlist = new ArrayList<>();
                    cmdlist.add("card");
                    cmdlist.add("country");
                    cmdlist.add("banner");
                }else if(args[0].equalsIgnoreCase("remove")){
                    cmdlist = new ArrayList<>();
                    for(Player p: Countrydata.getOnlinemember(Playerdata.getNowCountry(player))){
                        cmdlist.add(p.getDisplayName());
                    }
                    return cmdlist;
                }else if(args[0].equalsIgnoreCase("remove")){
                    cmdlist = new ArrayList<>();
                    for(Player p: Countrydata.getOnlinemember(Playerdata.getNowCountry(player))){
                        cmdlist.add(p.getDisplayName());
                    }
                    return cmdlist;
                }
            }
        }
        return null;
    }
}
