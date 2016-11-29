import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

class Config {

    private String ip;
    private int port;
    private String nickname;
    private String login;
    private String password;
    private String[] admins;

    private Properties prop = new Properties();


    void readConfig(){
        try {
            InputStream input = new FileInputStream("src/config.properties");

            prop.load(input);

            ip = prop.getProperty("ip");

            port = Integer.parseInt(prop.getProperty("port"));

            nickname = prop.getProperty("nickname");

            login = prop.getProperty("login");

            password = prop.getProperty("password");

            admins = prop.getProperty("admins").split(",");
            Arrays.sort(admins);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveConfig(){

    }

    String getString(String index){
        switch (index){
            case "ip" :
                return ip;
            case "nickname" :
                return nickname;
            case "password" :
                return password;
            case "login" :
                return login;
        }
        return null;
    }

    Integer getInteger(String index){
        switch (index){
            case "port" :
                return port;
        }
        return null;
    }

    String[] getStringArray(String property){
        switch (property){
            case "admins" :
                return admins;
        }
        return null;
    }
}
