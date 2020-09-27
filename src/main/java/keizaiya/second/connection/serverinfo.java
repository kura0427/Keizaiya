package keizaiya.second.connection;

import java.util.List;

public class serverinfo {
    private List<String> member;
    private List<String> info;
    private long time;
    private float TPS;
    private int id;

    public serverinfo(Float TPSs , Long times , List<String> members , List<String> infos , int numbers){
        time = times;
        TPS = TPSs;
        member = members;
        info = infos;
        id = numbers;
    }

    public Float getTPS(){return TPS;}

    public void setId(int ids){
        id = ids;
    }

}
