package ch12.completable;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MediaWatch {


    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath(args[0]);
        ParagraphProcessor pp = new ParagraphProcessor(args[1]);

        List<CompletableFuture<Stream<String>>> completableFutureList = Files.lines(path).map(url -> {
            CompletableFuture<Optional<Document>> downloadPage = CompletableFuture.supplyAsync(() ->
                    LinkProcessor.downloadPage(url));
            CompletableFuture<Document> createDoc = downloadPage.thenApply(document -> {
                if (document.isPresent()) {
                    return document.get();
                } else {
                    return new Document(url);
                }
            });
            CompletableFuture<Stream<String>> parsePage = createDoc.thenApplyAsync(document -> PageProcessor.parsePage(document));
            CompletableFuture<Stream<String>> matchedParagraphs = parsePage.thenApplyAsync(stringStream -> stringStream.map(pp::checkParagraph).flatMap(Optional::stream));
            return matchedParagraphs;
        }).collect(Collectors.toList());

        System.out.println("Downloading pages...");
        
        for(CompletableFuture<Stream<String>> future : completableFutureList){
            try {
                future.get().forEach(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
