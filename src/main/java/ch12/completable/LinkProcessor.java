package ch12.completable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LinkProcessor {

    private static ExecutorService downloaders = Executors.newFixedThreadPool(10);

    public static Document downloadPage(String uriString) throws IOException {
        Document result = Jsoup.connect(uriString).get();
        return result;
    }

}
