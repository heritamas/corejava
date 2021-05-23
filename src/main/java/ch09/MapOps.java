package ch09;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TextReader {

    private final List<String> content;

    TextReader(String what) throws URISyntaxException, IOException {
        Path pathOfWhat = Paths.get(TextReader.class.getResource(what).toURI());
        content = Files.readAllLines(pathOfWhat);
    }

    public List<String> getContent() {
        return content;
    }
}


public class MapOps {

    public static void main(String[] args) throws URISyntaxException, IOException {
        TextReader tr = new TextReader("Hamlet.txt");
        Map<String, List<String>> acts = new LinkedHashMap<>();
        Pattern role = Pattern.compile("\\*(.*)\\*");

        String currentRole = "UNKNOWN";
        String currentText = "";
        for (String text : tr.getContent()) {
            //TODO
        }

        // How many times did Hamlet tell something?
        System.out.format("%d%n", acts.get("HAMLET").size());

        // And Fortinbras?
        System.out.format("%d%n", acts.get("PRINCE FORTINBRAS").size());

        // Which thought is the "to be or not to be"
        System.out.format("%d%n", acts.get("HAMLET").indexOf(new Object() {

            @Override
            public boolean equals(Object obj) {
                return ((String) obj).matches(".*To be, or not to be.*");
                //return ((String) obj).matches(".*");
            }
        }));


    }
}
