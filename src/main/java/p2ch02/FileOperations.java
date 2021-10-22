package p2ch02;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileOperations {

    private static Stream<Path> listHiddenFiles(String inPath) throws Exception {
            Stream<Path> entries = Files.list(Path.of(inPath));
            return entries.filter(path -> {
                    try {
                        return Files.isHidden(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                ;
    }

    private static void listHiddenVoidFiles(String inPath)  {
        try (Stream<Path> entries = Files.list(Path.of(inPath))){
            entries.filter(path -> {
                try {
                    return Files.isHidden(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            })
                .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void walkFileTreeFiles(String path) throws IOException {
        Files.walkFileTree(Path.of(path), new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.isHidden(file)) {
                    System.out.println(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    private static List<String> findSameNamedFiles(String path) throws IOException {
        List<String> duplicates = new ArrayList<>();
        Files.walkFileTree(Path.of(path), new SimpleFileVisitor<>(){
            List<String> existingFiles = new ArrayList<>();
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (existingFiles.contains(file.getFileName().toString())) {
                    duplicates.add(file.getFileName().toString());
                } 
                existingFiles.add(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
        });
        return duplicates;
    }
    

    public static void main(String[] args) throws Exception {
//        listHiddenVoidFiles("/Users/csengeviragmaruzsi/");
//        listHiddenFiles("/Users/csengeviragmaruzsi/")
//            .forEach(System.out::println);
//        walkFileTreeFiles("/Users/csengeviragmaruzsi/Documents");
        findSameNamedFiles("/Users/csengeviragmaruzsi/Documents").forEach(System.out::println);
    }
}
