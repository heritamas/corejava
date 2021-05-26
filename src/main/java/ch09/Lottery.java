package ch09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Draw {

    private static final List<Integer> upTo200  = new ArrayList<>();

    static {
        for ( int i = 1; i <= 200 ; ++i) {
            upTo200.add(i);
        }
    }

    static List<Integer> draw(int num, int outOf) {
        if (outOf > 200) {
            throw new IllegalArgumentException("C'mon!");
        }
        ArrayList<Integer> subList = new ArrayList<Integer>(upTo200.subList(0,outOf));
        Collections.shuffle(subList);

        ArrayList<Integer> sortedList = new ArrayList<Integer>(subList.subList(0,num));
        Collections.sort(sortedList);
        return sortedList;

    }
}

public class Lottery {

    public static void main(String[] args) {
        System.out.format("Lottery: %s", Draw.draw(5, 90));
    }
}
