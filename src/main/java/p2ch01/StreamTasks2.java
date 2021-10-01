package p2ch01;

import ch09.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
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
        LongStream ls = LongStream.iterate(0L, num -> num + 1L);
        return ls.boxed();
    }

    // create enumerated stream from string stream
    private static Stream<Map.Entry<Long, String>> enumerateStream(Stream<String> input) {
        Iterator<Long> iterator = getNumbers().iterator();
        return input.map(word -> new AbstractMap.SimpleEntry<Long, String>(iterator.next(), word));
    }

    // which line has the first encounter of "whatever?"
    private static Optional<Long> getFirstEncounter(Stream<String> input, String whatever) {
        Stream<Map.Entry<Long, String>> entryStream = enumerateStream(input);
        return entryStream
            .filter(entry -> entry.getValue().contains(whatever))
            .map(entry -> entry.getKey())
            .findFirst();
    }

    // every line where "something" is metioned
    // use optionals
    private static Stream<String> allMentions(Stream<String> input, String something) {
        return input
            .map(line -> {
                if (line.contains(something)) {
                    return Optional.of(line);
                }
                else {
                    return Optional.<String>empty();
                }
            })
            .flatMap(Optional::stream);
    }

    // list of all words
    // by first letter
    private static Map<Character, Set<String>> allWords(Stream<String> input) {
        return input
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(word -> word.charAt(0), Collectors.toSet()));
    }

    private static Map<Character, Integer> getNumberOfWords(Stream<String> input) {
        return input
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(word -> word.charAt(0), Collectors.collectingAndThen(Collectors.toSet(), Set::size)));
    }

    // map roles to its text
    private static Stream<AbstractMap.SimpleEntry<String, String>> linesOfRoles(Stream<String> input) {
        final AtomicReference<String> role = new AtomicReference<>("");
        return input
            .map(line -> {
                if (line.matches("\\*.*\\*")) {
                    role.set(line);
                }
                return new AbstractMap.SimpleEntry<>(role.get(), line);
            })
            .filter(entry -> !entry.getKey().equals(entry.getValue()));
    }

    // ... separated by ","
    private static String allRolesOneline(Stream<String> input) {
        return input
            .filter(line -> line.matches("\\*.*\\*"))
            .collect(Collectors.collectingAndThen(Collectors.toSet(), Set::toString));
    }

    // in which lines, "something" is mentioned?
    private static List<Long> mentionedIn(Stream<String> input, String something) {
        return null;
    }

    // use partitioning!
    private static List<Long> mentionedInPartitioned(Stream<String> input, String something) {
        return null;
    }

    // script for actors
    private static List linesOfRole(Stream<String> input, String role) {
//        Map<String, String> script = linesOfRoles(input)
//            .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (word1, word2) -> word1 + "\n" + word2));
//        return script.get(role);
        return linesOfRoles(input)
            .collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.mapping(entry -> entry.getValue(), Collectors.toList()))).get(role);
    }

    // number of lines per role
    private static Map<String, Long> noLinesOfRoles(Stream<String> input) {
        return linesOfRoles(input)
            .collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.mapping(entry -> entry.getValue(), Collectors.counting())));
    }

    // roles, who speak about another roles
    private static Map<String, Set<String>> rolesAboutRoles(Stream<String> input) throws URISyntaxException, IOException {
        Set<String> roles = getWordStream()
            .filter(Pattern.compile("\\p{Lu}{4,}").asMatchPredicate())
            .map(String::toLowerCase)
            .collect(Collectors.toSet());

        Pattern splitter = Pattern.compile("\\PL+");
        //System.out.println(roles);

        return linesOfRoles(input)
            .collect(
                Collectors.groupingBy(
                    Map.Entry::getKey,
                    Collectors.mapping(
                        Map.Entry::getValue,
                        Collectors.flatMapping(
                            splitter::splitAsStream,
                            Collectors.mapping(
                                String::toLowerCase,
                                Collectors.filtering(
                                    roles::contains,
                                    Collectors.toSet()))))));
    }




    public static void main(String[] args) throws URISyntaxException, IOException {

//        getLineStream().forEach(System.out::println);
//       enumerateStream(getLineStream()).forEach(System.out::println);
//        System.out.println(getFirstEncounter(getLineStream(), "HAMLET").orElse(0L));
//        allMentions(getLineStream(), "thy").forEach(System.out::println);
//        System.out.println(mentionedIn(getLineStream(), "blood"));
//        linesOfRoles(getLineStream()).values().forEach(System.out::println);
//        System.out.println(noLinesOfRoles(getLineStream()));
//        linesOfRoles(getLineStream()).forEach(System.out::println);
//        System.out.println(allRolesOneline(getLineStream()));
//        System.out.println(linesOfRole(getLineStream(), "*Ghost*"));
//        linesOfRole(getLineStream(), "*HORATIO*").forEach(System.out::println);
//        System.out.println(noLinesOfRoles(getLineStream()));
        System.out.println(rolesAboutRoles(getLineStream()));
    
    }
    
    
}
