import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

class SocketReader extends Thread{

    private BufferedReader reader = null;
    private String last = "";
    private Config cfg;

    private void setup(Socket socket, Config cfg){
        try {
            this.cfg = cfg;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setup(HermesBot.getSocket(), HermesBot.getCfg());
        String response;
        while(!isInterrupted()) {
            try {
                while ((response = reader.readLine()) != null) {
                    System.out.println(response);
                    if (isDuplicate(response)) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isDuplicate(String response) {
        if (!(response.contains("notifyclientmoved") ||  response.contains("notifycliententerview") || response.contains("notifyclientleftview"))) {
            return false;
        }

        if (response.equals(last)) {
            last = "";
            return true;
        }

        last = response;
        return false;
    }
}
