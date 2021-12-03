package p2ch04;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ToyServer {

    static ExecutorService xs = Executors.newFixedThreadPool(10);
    static ExecutorCompletionService<InetAddress> xcs = new ExecutorCompletionService<InetAddress>(xs);

    static class ClientHandler implements Callable<InetAddress> {
        Socket incoming;

        public ClientHandler(Socket incoming) {
            this.incoming = incoming;
        }

        @Override
        public InetAddress call() throws Exception {
            InputStream inputStream = incoming.getInputStream();
            try (var in = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                while (in.hasNextLine()) {
                    System.out.println(in.nextLine());
                }
                //TODO var 5 masodpercet es ir egy BYE-t
            }
            return incoming.getInetAddress();
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4444);
        
        while (true) {
            xs.submit(new ClientHandler(ss.accept()));
        }
    }
}
