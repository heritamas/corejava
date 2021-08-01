package ch12.completable;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class MediaWatch {


    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath(args[0]);
        ParagraphProcessor pp = new ParagraphProcessor(args[1]);

        Files
                .lines(path)
                .map(LinkProcessor::downloadPage)
                .flatMap(Optional::stream)
                .flatMap(PageProcessor::parsePage)
                .map(pp::checkParagraph)
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
}
