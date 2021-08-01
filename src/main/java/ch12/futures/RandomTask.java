package ch12.futures;

import ch12.Util;

import java.util.concurrent.Callable;

public class RandomTask implements Callable<String> {

    final int waitTime;

    public RandomTask() {
        waitTime = Util.nextInt(5000);
    }

    @Override
    public String call()  {
        Util.sleep(waitTime);
        return String.format("Slept %s ms.", waitTime);
    }
}
