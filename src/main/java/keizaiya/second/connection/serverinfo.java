package keizaiya.second.connection;

import java.util.List;

public class serverinfo {
    private List<String> member;
    private List<String> info;
    private long time;
    private float TPS;
    private int number;

    public serverinfo(Float TPSs , Long times , List<String> members , List<String> infos , int numbers){
        time = times;
        TPS = TPSs;
        member = members;
        info = infos;
        number = numbers;
    }

    public Float getTPS(){return TPS;}

}
