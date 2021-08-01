package ch12.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskStarter {

    static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 20; ++i) {
            futures.add(service.submit(new RandomTask()));
        }

        for (Future<String> result : futures) {
            System.out.println(result.get());
        }

        service.shutdown();
        service.shutdownNow();
    }
}
