package ch12.completable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LinkProcessor {

    private static ExecutorService downloaders = Executors.newFixedThreadPool(10);

    public static Optional<Document> downloadPage(String uriString) {
        Document result = null;

        try {
            result = Jsoup.connect(uriString).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(result);
    }

}
