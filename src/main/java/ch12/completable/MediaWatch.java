package ch12.completable;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class MediaWatch {


    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath(args[0]);
        ParagraphProcessor pp = new ParagraphProcessor(args[1]);

        Stream<CompletableFuture<Stream<String>>> promises = Files.lines(path)
                .parallel()
                .map(outleturl -> {
                    CompletableFuture<Stream<String>> result = CompletableFuture.supplyAsync(() -> {
                                try {
                                    return LinkProcessor.downloadPage(outleturl);
                                } catch (IOException e) {
                                    throw new UncheckedIOException(e);
                                }
                            })
                            .exceptionally(throwable -> new Document(outleturl))
                            .thenApplyAsync(PageProcessor::parsePage)
                            .thenApply(stream -> stream
                                    .map(pp::checkParagraph)
                                    .flatMap(Optional::stream));

                    return result;
                });

        System.out.println("Fetching outlets");

        promises.flatMap(cf -> {
                Stream<String> result = Stream.empty();
                try {
                    result = cf.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                return result;
            })
            .forEach(System.out::println);

    }
}
