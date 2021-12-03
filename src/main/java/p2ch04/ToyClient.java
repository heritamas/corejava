package p2ch04;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class ToyClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                Socket s = null;
                try {
                    s = new Socket("localhost", 4444);
                    OutputStream out = s.getOutputStream();
                    PrintWriter pwr = new PrintWriter(out, true, StandardCharsets.UTF_8);
                    pwr.println(LocalDateTime.now().toString());
                    Thread.sleep(5000);
                    //TODO addig var a kliens amig BYE-t nem kap
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }).start();
        }
        

    }
}
