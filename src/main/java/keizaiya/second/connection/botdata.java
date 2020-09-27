package keizaiya.second.connection;

import java.util.ArrayList;
import java.util.List;

public class botdata {
    private int id = 0;
    private String request = "";
    private List<String> list = new ArrayList<>();

    public botdata(int ids , String requests ,List<String> lists){
        id = ids;
        request = requests;
        list = lists;
    }

    public int getid(){
        return id;
    }
    public String getRequest(){
        return request;
    }
    public List<String> getList(){
        return list;
    }

    public void setId(int ids){
        id = ids;
    }
    public void setRequest(String requests){
        request = requests;
    }
    public void setList(List<String> lists){
        list = lists;
    }
}
