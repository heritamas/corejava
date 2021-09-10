package p2ch01;

import ch09.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.PrimitiveIterator;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
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
        return tr.getContent().stream()
            .flatMap(line -> Arrays.stream(line.split("\\PL+")));
    }

    // get lines containing a certain substring
    private static Stream<String> filterLines(Stream<String> lineStream, String has) {
        return lineStream.filter(line -> line.contains(has));
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
        return Stream.iterate(start, num -> num != 1L, num -> {
            if (num % 2 == 0) {
                return num/2;
            }
            else {
                return 1+(3*num);
            }
        });
    }

    // prefix each element with its order number
    private static Stream<String> numberElements(Stream<String> stream) {
        IntStream intStream = IntStream.iterate(1, num -> num + 1);
        PrimitiveIterator.OfInt iterator = intStream.iterator();
        return stream.map(string -> String.format("%d: %s", iterator.nextInt(), string));

    }

    // list words until someone is mentioned
    private static Stream<String> untilMentioned(Stream<String> origin, String who) {
        return origin.takeWhile(word -> !word.equals(who));
    }

    // list roles
    private static Stream<String> alphabeticRoles(Stream<String> origin) {
        return origin.filter(word -> word.matches("\\p{Lu}{4,}")).sorted().distinct();
    }


    public static void main(String[] args) throws URISyntaxException, IOException {

//        firstNElements(getLineStream(), 12).forEach(System.out::println);
//        generateCollatzSequence(27L).forEach(System.out::println);
//        numberElements(getLineStream()).forEach(System.out::println);
//        untilMentioned(getWordStream(), "HORATIO").forEach(System.out::println);
        alphabeticRoles(getWordStream()).forEach(System.out::println);
    }
}
