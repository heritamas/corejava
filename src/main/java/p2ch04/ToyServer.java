package p2ch04;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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

        @Override
        public InetAddress call() throws Exception {
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4444);

        while (true) {
            Socket incoming = ss.accept();
        }
    }
}
