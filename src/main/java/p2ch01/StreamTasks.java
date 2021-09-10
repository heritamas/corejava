package p2ch01;

import ch09.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

public class StreamTasks {

    // get stream of lines from file
    private static Stream<String> getLineStream() throws URISyntaxException, IOException {
        TextReader tr = new TextReader("Hamlet.txt");
        return ???;
    }

    // get stream of words from file
    private static Stream<String> getWordStream() throws URISyntaxException, IOException {
        TextReader tr = new TextReader("Hamlet.txt");
        return ???;
    }

    // get lines containing a certain substring
    private static Stream<String> filterLines(Stream<String> lineStream, String has) {
        return ???;
    }

    // first n elements of stream
    private static Stream<String> firstNElements(Stream<String> stream, long n) {
        return ???;
    }

    // Collatz:
    // The next element, when the previous was n:
    // n/2 if n was even
    // 3n+1 if n was odd
    // stop at reaching 1
    private static Stream<Long> generateCollatzSequence(Long start) {
        return ???;

    }

    // prefix each element with its order number
    private static Stream<String> numberElements(Stream<String> stream) {
        return ???;

    }

    // list words until someone is mentioned
    private static Stream<String> untilMentioned(Stream<String> origin, String who) {
        return ???;
    }

    // list roles
    private static Stream<String> alphabeticRoles(Stream<String> origin) {
        return ???;
    }


    public static void main(String[] args) throws URISyntaxException, IOException {

        getWordStream().forEach(System.out::println);
    }
}
