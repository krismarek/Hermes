import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private String botName;
    public void readConfig(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("/home/sta534/IdeaProjects/HermesBot/Hermes/Hermes/HermesBot/src/config.properties");

            prop.load(input);

            botName = prop.getProperty("BotName");

        } catch (IOException ex) {

        ex.printStackTrace();
        } finally {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }

    public String getBotName(){
        return botName;
    }
}
