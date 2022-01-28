package extmod.impl;

import extmod.intf.ExtInterface;

public class ExtImpl implements ExtInterface {

    @Override
    public void performTask(String s) {
        System.out.printf("Task %s performed%n", s);
    }
}
