package keizaiya.second.connection;

import keizaiya.second.Potato;
import keizaiya.second.file.Yamlfile;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;

public class config {

    public static YamlConfiguration TCPconfig = new YamlConfiguration();

    public static void checkfile(){
        File file = new File("KeizaiyaMain/TCPconfig.yml");
        if(file.exists() == false){
            YamlConfiguration yml = new YamlConfiguration();
            try {
                InputStream stream = Potato.clname.getResourceAsStream("/sample/TCPconfig.yml");
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                yml.load(br);
                yml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadfile(){
        checkfile();
        File file = new File("KeizaiyaMain/TCPconfig.yml");
        TCPconfig = Yamlfile.loadyaml(file);
    }

}
