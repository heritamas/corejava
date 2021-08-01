package ch12.completable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.Stream;

public class PageProcessor {

    public static Stream<String> parsePage(Document document) {

        Stream.Builder<String> resultBuilder = Stream.builder();
        Elements paragraphs = document.select("li, p, a");
        for (Element p : paragraphs) {
            resultBuilder.add(p.text());
        }
        
        return resultBuilder.build();
    }
}
