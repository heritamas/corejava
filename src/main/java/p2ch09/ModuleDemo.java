package p2ch09;

import extmod.intf.DateService;
import extmod.intf.ExtInterface;

import java.util.ServiceLoader;

public class ModuleDemo {
    public static void main(String[] args) {
        ExtInterface ei = ExtInterface.newInstance();

        ei.performTask("funny");

        ServiceLoader<DateService> loader = ServiceLoader.load(DateService.class);
        loader
                .stream()
                .map(ServiceLoader.Provider::get)
                .map(DateService::getDate)
                .forEach(System.out::println);

    }

}
