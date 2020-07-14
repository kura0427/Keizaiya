package keizaiya.second.author;

import keizaiya.second.Potato;

import java.util.List;
import java.util.Random;

public class joinmessage {
    public static String joinmessage(){
        Random rnd = new Random();
        List<String> list = Potato.config.getStringList("joinmessage");
        Integer i = rnd.nextInt(list.size());
        return list.get(i);
    }
}
