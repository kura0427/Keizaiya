package keizaiya.second.file;

import keizaiya.second.Potato;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class updatefiles {

    public static void updateAll(){
        updateCountryfile();
    }

    public static void updateCountryfile(){
        File file = new File("KeizaiyaMain/Country");
        if(file.exists() == true) {
            File fileArray[] = file.listFiles();

            InputStream stream = Potato.clname.getResourceAsStream("/sample/countrydata.yml");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            YamlConfiguration defoult = new YamlConfiguration();
            try {
                defoult.load(br);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }

            for (File f : fileArray) {
                YamlConfiguration yml = defoult;
                f = new File(f.getPath());
                try {
                    yml.load(f);
                    yml.save(f);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
