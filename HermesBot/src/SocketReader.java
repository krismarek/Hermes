import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class SocketReader extends Thread{

    private BufferedReader reader = null;

    void setup(Socket socket){
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setup(HermesBot.getSocket());
        while(!isInterrupted()) {
            String response;
            try {
                while ((response = reader.readLine()) != null) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
