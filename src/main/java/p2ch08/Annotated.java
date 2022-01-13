package p2ch08;

public class Annotated {

    @Foo(object = "hello")
    public String methodA(String str) {
        return String.format("%s, I'm method A", str);
    }

    @Foo(object = "szia")
    public String methodB(String str) {
        return String.format("%s, I'm method B", str);
    }

    public boolean methodC() {
        return true;
    }
}
