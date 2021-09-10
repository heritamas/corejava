package ch09;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader {

    private final List<String> content;

    public TextReader(String what) throws URISyntaxException, IOException {
        Path pathOfWhat = Paths.get(TextReader.class.getResource(what).toURI());
        content = Files.readAllLines(pathOfWhat);
    }

    public List<String> getContent() {
        return content;
    }

}
