package ch05.resolution;

public class User {

    public static void main(String[] args) throws Exception {
        Base b = new Derived();

        b.methodOne(1);
        b.methodOne("foo");
        Base returned = b.methodReturns();
        b.methodAccepts(b);
    }
}
