package ch06.lambdas;

import java.util.stream.IntStream;


public class FizzBuzz {
    public static void main(String[] args) {
        IntStream.range(1, 100)
                .forEach(x -> {
                    String buffer = "";
                    if (divider(x, 3)){
                        buffer += "Fizz";
                    }
                    if (divider(x, 5)){
                        buffer += "Buzz";
                    }
                    if (buffer.isEmpty()){
                        buffer += x;
                    }
                    System.out.println(buffer);
                    
                });
    }
    
    private static boolean divider(Integer number, int divider) {
        return number % divider == 0;
    }
}
