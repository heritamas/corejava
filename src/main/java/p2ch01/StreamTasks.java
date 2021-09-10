package p2ch01;

import ch09.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamTasks {

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

    // get lines containing a certain substring
    private static Stream<String> filterLines(Stream<String> lineStream, String has) {
        return lineStream
                .filter(ln -> ln.contains(has));
    }

    // first n elements of stream
    private static Stream<String> firstNElements(Stream<String> stream, long n) {
        return stream.limit(n);
    }

    // Collatz:
    // The next element, when the previous was n:
    // n/2 if n was even
    // 3n+1 if n was odd
    // stop at reaching 1
    private static Stream<Long> generateCollatzSequence(Long start) {
        return Stream.iterate(
                start,
                Predicate.not(Long.valueOf(1L)::equals),
                n -> n % 2 == 0 ? n/2 : 3 * n + 1);

    }

    // prefix each element with its order number
    private static Stream<String> numberElements(Stream<String> stream) {
        Iterator<String> iter = stream.iterator();
        return Stream.iterate(1L, n -> iter.hasNext(), n -> n+1)
                .map(i -> String.format("%-7d:%s", i, iter.next()));

    }

    // list words until someone is mentioned
    private static Stream<String> untilMentioned(Stream<String> origin, String who) {
        return origin.takeWhile(str -> !str.equalsIgnoreCase("hamlet"));
    }

    // list roles
    private static Stream<String> alphabeticRoles(Stream<String> origin) {
        return origin
                .filter(str -> str.matches("\\p{Lu}{4,}"))
                .sorted()
                .distinct();
    }


    public static void main(String[] args) throws URISyntaxException, IOException {

        alphabeticRoles(getWordStream()).forEach(System.out::println);
    }
}
