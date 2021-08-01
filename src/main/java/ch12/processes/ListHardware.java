package ch12.processes;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class ListHardware {

    public static void main(String[] args) throws IOException {

        Process lister = new ProcessBuilder("lshw", "-json", "-class storage")
                .start();

        try (Scanner in = new Scanner(lister.getInputStream())) {
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        }

    }
}
