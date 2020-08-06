package keizaiya.second.chat.channel;

import java.util.List;

public class channeldata {

    private String name;
    private List<String> member;
    private String owner;
    private String tag;
    private Integer modenum;

    public channeldata(String name, String owner, String tag, List<String> member){
        this.name = name;
        this.owner = owner;
        this.tag = tag;
        this.member = member;
        this.modenum = Integer.valueOf(tag.replace("ch","")) + 1000;
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

    public Integer getModenum(){ return modenum;}
}
