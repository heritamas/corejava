package ch12.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskStarter {

    static ExecutorService service = Executors.newFixedThreadPool(10);
    static ExecutorCompletionService<String> completion = new ExecutorCompletionService<>(service);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < 20; ++i) {
            completion.submit(new RandomTask());
        }


        for (int i = 0; i < 20; ++i) {
            Future<String> completed = completion.take();
            System.out.println(completed.get());
        }

        service.shutdown();
        service.shutdownNow();
    }
}
