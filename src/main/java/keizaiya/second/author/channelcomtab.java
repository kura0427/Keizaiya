package keizaiya.second.author;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class channelcomtab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> cmdlist = new ArrayList<>();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if("invite".contains(args[0])) {
                    cmdlist.add("invite");
                }if("create".contains(args[0])) {
                    cmdlist.add("create");
                }if("close".contains(args[0])) {
                    cmdlist.add("close");
                }if("remove".contains(args[0])) {
                    cmdlist.add("remove");
                }
                return cmdlist;
            }
        }
        return null;
    }
}
