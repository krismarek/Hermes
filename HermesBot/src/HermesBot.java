import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HermesBot {
    private static String botName;
    private static Config cfg;

    private static HermesBot ourInstance = null;

    private HermesBot() {
        botName = cfg.getBotName();
    }

    public static HermesBot getInstance() {
        if(ourInstance == null){
            ourInstance = new HermesBot();
        }
        return ourInstance;
    }

    public static void main(String[] args) {
        cfg = new Config();
        cfg.readConfig();
        getInstance();

        Socket s = new Socket();
        PrintWriter s_out = null;
        BufferedReader s_in = null;

        try {
            s.connect(new InetSocketAddress("ts3-hermes.com" , 10011));
            System.out.println("Connected");

            //writer for socket
            s_out = new PrintWriter( s.getOutputStream(), true);
            //reader for socket
            s_in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }

        //Host not found
        catch (UnknownHostException e) {
            System.err.println("Don't know about host : " + 10011);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Send message to server
        String message = "use 1";
        s_out.println( message );
        String message2= "clientupdate client_nickname="+botName;
        s_out.println( message2 );
        System.out.println("Message send");

        //Get response from server
        String response;
        try {
            while ((response = s_in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
