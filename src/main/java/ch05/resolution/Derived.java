package ch05.resolution;


public class Derived extends Base {

    void doesNotExistsinBase() {
        System.out.println("I'm unique!");
    }

    void methodZero2() {
        System.out.println("Derived::methodZero2()");
    }

    String methodOne(String param) {
        System.out.println("Derived::methodOne(String)");
        return param;
    }

    String methodOne(int param) {
        System.out.println("Derived::methodOne(int)");
        return "" + param;
    }

    Derived methodReturns()  {
        System.out.println("Derived::methodReturns()");
        return this;
    }

    void methodAccepts(Derived b) {
        System.out.println("Derived::methodAccepts()");
        doesNotExistsinBase();
    }

}
