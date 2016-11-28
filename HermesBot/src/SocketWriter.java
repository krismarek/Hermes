import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class SocketWriter extends Thread{

    private BufferedWriter writer = null;
    private BlockingQueue<String> sendQueue = new LinkedBlockingQueue<>();

    void setup(Socket socket, BlockingQueue queue){
        try {
            sendQueue = queue;
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        setup(HermesBot.getSocket(), HermesBot.getQueue());
        while(!isInterrupted()){
            if(!sendQueue.isEmpty()){
                try {
                    String msg = sendQueue.take();
                    try {
                        writer.write(msg);
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
