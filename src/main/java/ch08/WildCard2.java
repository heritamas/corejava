package ch08;

import java.util.List;

class Box<T>{
    private T inner;

    public Box(T inner) {
        this.inner = inner;
    }

    public T getInner() {
        return inner;
    }

    public void setInner(T inner) {
        this.inner = inner;
    }
}

public class WildCard2 {
    public static void something(Box<? super Number> oneBox){
//        Double doubleValue = oneBox.getInner();
        oneBox.setInner(1L);
    }
}
