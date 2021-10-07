package p2ch02;

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
        return null;
    }

    private static void convertReadMe() throws URISyntaxException, IOException {
        return;
    }

    private static Map<String, Map<String, String>> readIniFile(String fileName) throws URISyntaxException, IOException {
        return null;
    }

    private static String readHeader(Scanner in) {
        return null;
    }

    private static Map<String, String> readSection(Scanner in) {
        return null;
    }

    private static void writeObjects(String fileName) {

    }

    private static void readObjects() {

    }



    public static void main(String[] args) throws IOException, URISyntaxException {
        //readReadMe().stream().forEach(System.out::println);
        //convertReadMe();
        System.out.println(readIniFile("test.ini"));
    }
}
