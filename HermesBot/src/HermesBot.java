import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class HermesBot extends Thread{
    private static final BlockingQueue<String> sendQueue = new LinkedBlockingQueue<>();
    private static Socket socket;
    public static void main(String[] args) {
        Config cfg = new Config();
        cfg.readConfig();

        socket = new Socket();

        try {
            socket.connect(new InetSocketAddress(cfg.getString("ip"), cfg.getInteger("port")));

            putCommand("use 1");
            putCommand("login client_login_name=sqa client_login_password=m8t4SUcS");
            putCommand("servernotifyregister event=server");
            putCommand("servernotifyregister event=channel id=0");
            putCommand("servernotifyregister event=textserver");
            putCommand("servernotifyregister event=textchannel id=0");
            putCommand("servernotifyregister event=textprivate");

            (new Thread(new SocketWriter())).start();

            (new Thread(new SocketReader())).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Socket getSocket(){
        return socket;
    }

    public static void putCommand(String msg){
        try {
            sendQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BlockingQueue getQueue(){
        return sendQueue;
    }
}
