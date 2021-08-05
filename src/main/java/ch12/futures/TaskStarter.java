package ch12.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskStarter {

    static ExecutorService executor = Executors.newFixedThreadPool(20);
    static ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(executor);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        
        for (int i = 0; i < 20; ++i) {
            service.submit(new RandomTask());
        }
        for (int i = 0; i<20; i++) {
            System.out.println(service.take().get());
        }

        executor.shutdown();
        executor.shutdownNow();
    }
}
