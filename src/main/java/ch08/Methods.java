package ch08;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Methods {

    public static void printArray(String[] arr) {
        for (String s: arr) {
            System.out.println(s);
        }
    }

    public static void printArray(Long[] arr) {
        for (Long l: arr) {
            System.out.println(l);
        }
    }

    public static void printArray(Character[] arr) {
        for (Character c: arr) {
            System.out.println(c);
        }
    }

    public static List<Integer> convertAndMapToList(Integer[] arr, Function<Integer, Integer> mapping) {
        return Arrays.stream(arr).map(mapping).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        printArray(new Long[]{1L, 2L, -5L, 4237864237846L});
        printArray(new String[]{"foo", "bar", "foobar"});
        printArray(new Character[]{'c', 'd', 'e', '☺'});

        Integer[] arr = new Integer[]{1,2,3,4,5};
        System.out.println(convertAndMapToList(arr, i -> i*2));
    }
}
