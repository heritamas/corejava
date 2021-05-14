package ch08;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

class SmallNumber {

    private int thisNum;

    public SmallNumber(int thisNum) {
        this.thisNum = thisNum;
    }

    public boolean isSmall() {
        return thisNum < 100;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SmallNumber.class.getSimpleName() + "[", "]")
                .add("thisNum=" + thisNum)
                .toString();
    }
}

public class Bounds {

    public static <T extends SmallNumber> List<T> convertAndFilterToList(List<T> ls) {
        return ls.stream().filter(SmallNumber::isSmall).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,5};
        List<SmallNumber> ls = Arrays.stream(arr).map(SmallNumber::new).collect(Collectors.toList());
        System.out.println(convertAndFilterToList(ls));

    }
}
