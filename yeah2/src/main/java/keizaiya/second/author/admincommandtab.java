package keizaiya.second.author;

import keizaiya.second.file.country.Countrydata;
import keizaiya.second.file.country.ideology;
import org.bukkit.Bukkit;
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
                if("settp".contains(args[0])) {
                    cmdlist.add("settp");
                }if("setname".contains(args[0])) {
                    cmdlist.add("setname");
                }if("ideology".contains(args[0])) {
                    cmdlist.add("ideology");
                }if("card".contains(args[0])) {
                    cmdlist.add("card");
                }if("^^".contains(args[0])) {
                    cmdlist.add("^^");
                }if("country".contains(args[0])) {
                    cmdlist.add("country");
                }if("item".contains(args[0])) {
                    cmdlist.add("item");
                }if("word".contains(args[0])) {
                    cmdlist.add("word");
                }if("remove".contains(args[0])) {
                    cmdlist.add("remove");
                }if("keepinventory".contains(args[0])) {
                    cmdlist.add("keepinventory");
                }if("tp".contains(args[0])) {
                    cmdlist.add("tp");
                }if("villager".contains(args[0])) {
                    cmdlist.add("villager");
                }if("announce".contains(args[0])) {
                    cmdlist.add("announce");
                }
                return cmdlist;
            }else if(args.length >= 2){
                if(args[0].equalsIgnoreCase("ideology")){
                    if(args.length == 2) {
                        cmdlist = new ArrayList<>();
                        if("getname".contains(args[1])) {
                            cmdlist.add("getname");
                        }if("getcard".contains(args[1])) {
                            cmdlist.add("getcard");
                        }if("list".contains(args[1])) {
                            cmdlist.add("list");
                        }
                        return cmdlist;
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("getname") || args[1].equalsIgnoreCase("getcard")) {
                            for(String mem : ideology.getlist()){
                                if(mem.contains(args[2])) {
                                    cmdlist.add(mem);
                                }
                            }
                            return cmdlist;
                        }
                    }
                }else if(args[0].equalsIgnoreCase("card")){
                    if(args.length == 2) {
                        cmdlist = new ArrayList<>();
                        if("country".contains(args[1])) {
                            cmdlist.add("country");
                        }if("ideology".contains(args[1])) {
                            cmdlist.add("ideology");
                        }
                        return cmdlist;
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("ideology")){
                            for(String mem : ideology.getlist()){
                                if(mem.contains(args[2])) {
                                    cmdlist.add(mem);
                                }
                            }
                            return cmdlist;
                        }
                    }
                }else if(args[0].equalsIgnoreCase("country")){
                    if(args.length == 2) {
                        cmdlist = new ArrayList<>();
                        if("info".contains(args[1])) {
                            cmdlist.add("info");
                        }if("list".contains(args[1])) {
                            cmdlist.add("list");
                        }if("setpoint".contains(args[1])) {
                            cmdlist.add("setPoint");
                        }if("addpoint".contains(args[1])) {
                            cmdlist.add("addPoint");
                        }if("getpoint".contains(args[1])) {
                            cmdlist.add("getPoint");
                        }if("addmember".contains(args[1])) {
                            cmdlist.add("addmember");
                        }if("removemember".contains(args[1])) {
                            cmdlist.add("removemember");
                        }if("sethead".contains(args[1])) {
                            cmdlist.add("sethead");
                        }if("getcard".contains(args[1])) {
                            cmdlist.add("getcard");
                        }if("setreligion".contains(args[1])) {
                            cmdlist.add("setreligion");
                        }if("getreligioncard".contains(args[1])) {
                            cmdlist.add("getreligioncard");
                        }if("tp".contains(args[1])) {
                            cmdlist.add("tp");
                        }if("banner".contains(args[1])) {
                            cmdlist.add("banner");
                        }
                        return cmdlist;
                    }else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("info") || args[1].equalsIgnoreCase("getpoint")){
                            return Countrydata.gettaglist();
                        }if(args[1].equalsIgnoreCase("addmember") || args[1].equalsIgnoreCase("removemember")
                                || args[1].equalsIgnoreCase("sethead")){
                            for(Player p : Bukkit.getOnlinePlayers()){
                                if(p.getDisplayName().contains(args[2])) {
                                    cmdlist.add(p.getDisplayName());
                                }
                            }
                            return cmdlist;
                        }if(args[1].equalsIgnoreCase("getcard")){
                            if("攻勢".contains(args[2])) {
                                cmdlist.add("攻勢");
                            }if("侵略".contains(args[2])) {
                                cmdlist.add("侵略");
                            }if("聖戦".contains(args[2])) {
                                cmdlist.add("聖戦");
                            }if("電撃戦".contains(args[2])) {
                                cmdlist.add("電撃戦");
                            }if("強行軍".contains(args[2])) {
                                cmdlist.add("強行軍");
                            }if("防御".contains(args[2])) {
                                cmdlist.add("防御");
                            }if("守勢".contains(args[2])) {
                                cmdlist.add("守勢");
                            }if("平和を守るための戦争".contains(args[2])) {
                                cmdlist.add("平和を守るための戦争");
                            }
                            return cmdlist;
                        }if(args[1].equalsIgnoreCase("tp") || args[1].equalsIgnoreCase("banner")){
                            return Countrydata.gettaglist();
                        }
                    }else if(args.length == 4){
                        if(args[1].equalsIgnoreCase("setpoint") || args[1].equalsIgnoreCase("addpoint")
                                || args[1].equalsIgnoreCase("addmember") || args[1].equalsIgnoreCase("removemember")
                                || args[1].equalsIgnoreCase("sethead") || args[1].equalsIgnoreCase("setreligion")){
                            return Countrydata.gettaglist();
                        }
                    }
                }else if(args[0].equalsIgnoreCase("word") || args[0].equalsIgnoreCase("keepinventory")) {
                    if (args.length == 2) {
                        if ("add".contains(args[1])) {
                            cmdlist.add("add");
                        }
                        if ("remove".contains(args[1])) {
                            cmdlist.add("remove");
                        }
                        if ("list".contains(args[1])) {
                            cmdlist.add("list");
                        }
                        return cmdlist;
                    }
                }else if(args[0].equalsIgnoreCase("announce")){
                    if (args.length == 2) {
                        if ("add".contains(args[1])) {
                            cmdlist.add("add");
                        }
                        if ("remove".contains(args[1])) {
                            cmdlist.add("remove");
                        }
                        if ("list".contains(args[1])) {
                            cmdlist.add("list");
                        }if ("setjoinmessage".contains(args[1])) {
                            cmdlist.add("setjoinmessage");
                        }
                        return cmdlist;
                    }if(args.length == 3){
                        if(args[1].equalsIgnoreCase("setjoinmessage")) {
                            if ("clear".contains(args[2])) {
                                cmdlist.add("clear");
                            }
                            return cmdlist;
                        }
                    }
                }
            }
        }
        return null;
    }
}
