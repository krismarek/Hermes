import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String ip;
    private String port;
    private String nickname;

    Properties prop = new Properties();
    InputStream input = null;


    public void readConfig(){
        try {
            input = new FileInputStream("src/config.properties");

            prop.load(input);

            ip = prop.getProperty("ip");

            port = prop.getProperty("port");

            nickname = prop.getProperty("nickname");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveConfig(){

    }

    public String getString(String index){
        switch (index){
            case "ip" :
                return ip;
            case "port" :
                return port;
            case "nickname" :
                return nickname;
        }
        return null;
    }
}
