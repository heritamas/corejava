package ch06.lambdas;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
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
               actions.stream().forEach(action -> action.accept(s));
            }
        } ;

        Files.lines(path).forEach(groupConsumer);
    }
    
    Stream<String> pipe(Collection<Function<String, String>> actions) throws IOException {
        Function<String, String> groupFunction = new Function<String, String>(){

            @Override
            public String apply(String s) {
                String result = s;
                for(Function<String, String> action : actions){
                    result = action.apply(result);
                }
                return result;
            }
        };
        return Files.lines(path).map(groupFunction);
    }

    Stream<String> pipe2(Collection<Function<String, String>> actions) throws IOException {
        Function<String, String> groupFunction = actions.stream().reduce(x -> x, (f1,f2) -> f2.compose(f1));
        return Files.lines(path).map(groupFunction);
    }
}



public class FileOperations {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Driver drv = new Driver("lambdafile.txt");

//        drv.process(List.of(System.out::println, str -> System.out.println(str.toUpperCase()), str -> System.out.println(str.contains("ac"))));
        drv.pipe2(List.of(str -> str.replaceAll("N", "79"), 
                str -> str.toUpperCase(), 
                str -> str.replaceAll("AC", "sddshgdrfghsths")))
                .forEach(System.out::println);
        
    }
}
