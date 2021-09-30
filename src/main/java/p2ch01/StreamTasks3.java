package p2ch01;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTasks3 {

    private static Stream<Integer> getSequentialIntegers() {
        return null;
    }

    private static Stream<Integer> getRandomIntegers(int bound) {
        return null;
    }


    // sum of first N ints
    private static Integer sumN(Stream<Integer> input, int limit) {
        return null;
    }

    // sum of first N ints
    private static Integer sumNCollected(Stream<Integer> input, int limit) {
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(sumN(getSequentialIntegers(), 100));
        //System.out.println(sumN(getRandomIntegers(1000), 10000));
        System.out.println(sumNCollected(getSequentialIntegers(), 100));
    }
}
