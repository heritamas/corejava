package p2ch04;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws IOException {

        InetAddress address = InetAddress.getByName("444.hu");
        System.out.println(address);

        Socket s = new Socket("444.hu", 80);
        InputStream in = s.getInputStream();
        Scanner scanner = new Scanner(in);

        OutputStream out = s.getOutputStream();
        PrintWriter pwr = new PrintWriter(out, true, StandardCharsets.UTF_8);

        pwr.println("GET / HTTP/1.1");
        pwr.println("Host: 444.hu");
        pwr.println("");

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        s.close();
    }
}
