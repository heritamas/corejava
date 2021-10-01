package p2ch01;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasks3 {

    private static Stream<Integer> getSequentialIntegers() {
        return IntStream.iterate(1, n -> n+1).boxed();
    }

    private static Stream<Integer> getRandomIntegers(int bound) {
        Random random = new Random();
        return random
            .ints(-bound, bound)
            .boxed();
    }


    // sum of first N ints
    private static Integer sumN(Stream<Integer> input, int limit) {
        return input
            .limit(limit)
            .reduce(0, (list, number) -> list += number);
    }

    // sum of first N ints 
//    use collect instead of reduce!!!
    private static Integer sumNCollected(Stream<Integer> input, int limit) {
        return input
            .limit(limit)
            .collect(Collectors.summingInt(Integer::intValue));
    }

    public static void main(String[] args) {
        System.out.println(sumN(getSequentialIntegers(), 100));
//        System.out.println(sumN(getRandomIntegers(1000), 10000));
//        System.out.println(sumNCollected(getSequentialIntegers(), 100));
        System.out.println(sumNCollected(getSequentialIntegers(), 100));
    }
}
