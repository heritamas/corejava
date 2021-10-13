package p2ch02;

import ch09.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputOutput {

    private static List<String> readReadMe() throws IOException, URISyntaxException {
        Path path = Paths.get(InputOutput.class.getResource("readme.txt").toURI());
        return Files.readAllLines(path, Charset.forName("ISO-2022-KR"));
    }

    private static void convertReadMe() throws URISyntaxException, IOException {
        List<String> original = readReadMe();
        Path path = Paths.get(InputOutput.class.getResource(".").toURI()).resolve("converted.txt");
        Files.write(path, original, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW);
    }

    private static Map<String, Map<String, String>> readIniFile(String fileName) throws URISyntaxException, IOException {
        Map<String, Map<String, String>> result = new HashMap<>();
        Path path = Paths.get(InputOutput.class.getResource(fileName).toURI());
        Scanner scanner = new Scanner(path, Charset.forName("UTF-8"));
        scanner.useDelimiter("\\n");
        while (scanner.hasNext()) {
            String key = readHeader(scanner);
            Map<String, String> values = readSection(scanner);
            result.put(key, values);
        }
        return result;
    }



    private static String readHeader(Scanner in) {
        String header = "";
        while (in.hasNext("")) {
            in.nextLine();
        }
        if (in.hasNext("\\[.*\\]")) {
            header = in.next();
        }
        return header;
    }

    private static Map<String, String> readSection(Scanner in) {
        Map<String, String> result = new HashMap<>();
        while (in.hasNext(".*=.*")) {
            Pattern delimiter = in.delimiter();
            in.useDelimiter("=|\\n");
            String key = in.next();
            String value = in.next();
            result.put(key, value);
            in.useDelimiter(delimiter);
            in.nextLine();
        }
        return result;
    }

    private static void writeObjects(String fileName) {

    }

    private static void readObjects() {

    }

    // can a data stream read back into  a Buffer?
    private static void readObjectsViaBuffer() {
    }



    public static void main(String[] args) throws IOException, URISyntaxException {
//        readReadMe().stream().forEach(System.out::println);
        //convertReadMe();
        System.out.println(readIniFile("test.ini"));
//        convertReadMe();
    }
}
