package ch12.processes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ListHardware {

    public static void main(String[] args) throws IOException {

        List<Process> list = ProcessBuilder.startPipeline(List.of(
                new ProcessBuilder("ioreg", "-p", "IODeviceTree"),
                new ProcessBuilder("grep", "IORegistryEntry")));
        Process last = list.get(list.size()-1);

        try (Scanner in = new Scanner(last.getInputStream())) {
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        }

    }
}
