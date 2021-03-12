package ch05.resolution;

import org.mockito.internal.matchers.Null;

import java.nio.file.AccessDeniedException;

public class Base {

    private void methodZero1() {
        System.out.println("Base::methodZero1()");
    }

    void methodZero2() {
        System.out.println("Base::methodZero2()");
    }

    String methodOne(String param) {
        System.out.println("Base::methodOne(String)");
        return param;
    }

    String methodOne(Integer param) {
        System.out.println("Base::methodOne(Integer)");
        return param.toString();
    }

    Base methodReturns()  {
        System.out.println("Base::methodReturns()");
        return this;
    }

    void methodAccepts(Base b) {
        System.out.println("Base::methodAccepts()");
    }
}
