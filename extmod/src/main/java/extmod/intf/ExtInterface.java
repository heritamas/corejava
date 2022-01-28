package extmod.intf;

import extmod.impl.ExtImpl;

public interface ExtInterface {

    static ExtInterface newInstance() {
        return new ExtImpl();
    }

    void performTask(String s);
}
