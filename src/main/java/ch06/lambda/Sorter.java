package ch06.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class Sorter {
    static class IntegerComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }
    public Integer[] array = new Integer[]{6,8,1,0,-5};
    
    public void sort(Comparator<Integer> comparator) {
        Arrays.sort(array, comparator);
    }
    

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
       // sorter.sort(new IntegerComparator());
//        sorter.sort((o1,o2) -> {
//            Integer o12 = o1*o1;
//            Integer o22 = o2*o2;
//            return o12-o22;
//        });
        sorter.sort(Integer::compareUnsigned);
        for (Integer i : sorter.array){
            System.out.println(i);
        }
        
    }
}
