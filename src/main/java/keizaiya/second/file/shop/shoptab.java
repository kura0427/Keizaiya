package keizaiya.second.file.shop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class shoptab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> cmdlist = new ArrayList<>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if("create".contains(args[0])) {
                    cmdlist.add("create");
                }if("geteditstick".contains(args[0])) {
                    cmdlist.add("geteditstick");
                }if("chengepass".contains(args[0])) {
                    cmdlist.add("chengepass");
                }
                return cmdlist;
            }if(args.length == 2){
                if(args[0].equalsIgnoreCase("geteditstick") || args[0].equalsIgnoreCase("chengepass")){
                    for(String key : shop.shoplist.keySet()){
                        shopdata data = shop.shoplist.get(key);
                        if(data.getOwner().equals(player.getUniqueId().toString())){
                            cmdlist.add(data.gettag());
                        }
                    }
                }
                return cmdlist;
            }
        }
        return cmdlist;
    }
}
