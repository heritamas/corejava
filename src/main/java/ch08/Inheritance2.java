package ch08;

class Super2 <T> {
    public void set( T arg) {  }
    public T get() { return null; }
}

class Sub2< A , B extends A > extends Super2 <A> {
    // why?
    @Override
    public void set( B arg) {  }

    @Override
    public B get() {  return null; }
}

public class Inheritance2 {
}
