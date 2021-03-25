package ch06.lambdas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

class Driver {

    Path path;

    Driver(String resourceName) throws URISyntaxException {
        path = Paths.get(getClass().getClassLoader()
                .getResource(resourceName).toURI());
    }

    void process(Collection<Consumer<String>> actions) throws IOException {

        Consumer<String> groupConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                actions.stream().forEach(stringConsumer -> stringConsumer.accept(s));
            }
        } ;

        Files.lines(path).forEach(groupConsumer);
    }
}



public class FileOperations {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Driver drv = new Driver("lambdafile.txt");

        drv.process(Set.of(System.out::println));
    }
}
