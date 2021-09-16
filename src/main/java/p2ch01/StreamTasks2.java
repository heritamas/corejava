package p2ch01;

import ch09.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamTasks2 {
    // get stream of lines from file
    private static Stream<String> getLineStream() throws URISyntaxException, IOException {
        TextReader tr = new TextReader("Hamlet.txt");
        return tr.getContent().stream();
    }

    // get stream of words from file
    private static Stream<String> getWordStream() throws URISyntaxException, IOException {
        TextReader tr = new TextReader("Hamlet.txt");
        return tr.getContent()
                .stream()
                .flatMap(Pattern.compile("\\PL+")::splitAsStream);
    }

    // infinite number sequence, helper method
    private static Stream<Long> getNumbers() {
        return null;
    }

    // create enumerated stream from string stream
    private static Object enumerateStream(Stream<String> input) {
        return null;
    }

    // which line has the first encounter of "whatever?"
    private static Optional<Long> getFirstEncounter(Stream<String> input, String whatever) {
        return null;
    }

    // every line where "something" is metioned
    // use optionals
    private Stream<String> allMentions(Stream<String> input, String something) {
        return null;
    }

    // list of all roles
    private List<String> allRoles(Stream<String> input) {
        return null;
    }

    // ... separated by ","
    private String allRolesOneline(Stream<String> input) {
        return null;
    }

    // in which lines, "something" is mentioned?
    private Object mentionedIn(Stream<String> input, String something) {
        return null;
    }

    // use partitioning!
    private Object mentionedInPartitioned(Stream<String> input, String something) {
        return null;
    }



    public static void main(String[] args) throws URISyntaxException, IOException {

        getLineStream().forEach(System.out::println);
    }
}
