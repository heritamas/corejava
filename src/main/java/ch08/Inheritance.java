package ch08;

class Super<T> {
    T t;

    public Super(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class Sub extends Super<Number> {

    public Sub(Number number) {
        super(number);
    }

    @Override
    public Long getT() {
        return super.getT().longValue();
    }

    // why?
    @Override
    public void setT(Number number) {
        super.setT(number);
    }

//    // why?
//    public void setT(Object number) {
//        setT((Number)number);
//    }

}


public class Inheritance {

    Number n = 1L;
}
