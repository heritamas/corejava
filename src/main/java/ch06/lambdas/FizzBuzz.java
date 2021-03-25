package ch06.lambdas;

import java.util.stream.IntStream;


public class FizzBuzz {
    public static void main(String[] args) {
        IntStream.range(1, 100)
                .forEach(System.out::println);
    }
}
