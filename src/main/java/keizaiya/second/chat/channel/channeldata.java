package keizaiya.second.chat;

import java.util.List;

public class channeldata {

    private static String name;
    private static List<String> member;
    private static String owner;
    private static String tag;

    public channeldata(String name , String owner,String tag,List<String> member){
        this.name = name;
        this.owner = owner;
        this.tag = tag;
        this.member = member;
    }

    public String getname(){
        return name;
    }

    public String gettag(){
        return tag;
    }

    public List<String> getMember(){
        return member;
    }

    public String getOwner(){
        return owner;
    }

    public void addmember(String uuid){
        member.add(uuid);
    }
}
