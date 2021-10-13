package p2ch02;

import ch09.TextReader;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

public class InputOutput {

    private static List<String> readReadMe() throws IOException, URISyntaxException {
        Path path = Paths.get(InputOutput.class.getResource("readme.txt").toURI());
        return Files.readAllLines(path, Charset.forName("ISO-2022-KR"));
    }

    private static void convertReadMe() throws URISyntaxException, IOException {
        List<String> original = readReadMe();
        Path path = Paths.get(InputOutput.class.getResource(".").toURI()).resolve("converted.txt");
        Files.write(path, original, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW);
    }

    private static Map<String, Map<String, String>> readIniFile(String fileName) throws URISyntaxException, IOException {
        Map<String, Map<String, String>> result = new HashMap<>();
        Path path = Paths.get(InputOutput.class.getResource(fileName).toURI());
        Scanner scanner = new Scanner(path, Charset.forName("UTF-8"));
        scanner.useDelimiter("\\n");
        while (scanner.hasNext()) {
            String key = readHeader(scanner);
            Map<String, String> values = readSection(scanner);
            result.put(key, values);
        }
        return result;
    }



    private static String readHeader(Scanner in) {
        String header = "";
        while (in.hasNext("")) {
            in.nextLine();
        }
        if (in.hasNext("\\[.*\\]")) {
            header = in.next();
        }
        return header;
    }

    private static Map<String, String> readSection(Scanner in) {
        Map<String, String> result = new HashMap<>();
        while (in.hasNext(".*=.*")) {
            Pattern delimiter = in.delimiter();
            in.useDelimiter("=|\\n");
            String key = in.next();
            String value = in.next();
            result.put(key, value);
            in.useDelimiter(delimiter);
            in.nextLine();
        }
        return result;
    }

    private static void writeObjects(String fileName) throws IOException {
        Map map1 = new HashMap();
        map1.put("color", "green");
        TestData data1 = new TestData("bogre", map1);
        Map map2 = new HashMap();
        map2.put("weight", "15 kg");
        TestData data2 = new TestData("kancso", map2);
        OutputStream outputStream = Files.newOutputStream(Path.of(fileName));
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeInt(2);
        oos.writeObject(data1);
        oos.writeObject(data2);
        oos.close();
    }

    private static void readObjects(String fileName) throws Exception {
        InputStream is = Files.newInputStream(Path.of(fileName));
        ObjectInputStream ois = new ObjectInputStream(is);
        Integer number = ois.readInt();
        for (int i = 0; i<number; i++) {
            TestData o = (TestData) ois.readObject();
            System.out.println(o);
        }
        ois.close();

    }

    // can a data stream read back into  a Buffer?
    private static void readObjectsViaBuffer() throws Exception {
        String fileName = "numbers.dat";
        OutputStream os = Files.newOutputStream(Path.of(fileName));
        DataOutputStream dos = new DataOutputStream(os);
        Random random = new Random();
        DoubleStream.generate(() -> random.nextDouble())
            .limit(10)
            .forEach(d -> {
                try {
                    dos.writeDouble(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        dos.close();
        
        FileChannel fileChannel = FileChannel.open(Path.of(fileName));
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        fileChannel.read(byteBuffer);
        byteBuffer.rewind();
        DoubleBuffer doubleBuffer = byteBuffer.asDoubleBuffer();
        for (int i=0; i<10;i++) {
            System.out.println(doubleBuffer.get());
        }

    }



    public static void main(String[] args) throws Exception {
//        readReadMe().stream().forEach(System.out::println);
        //convertReadMe();
//        System.out.println(readIniFile("test.ini"));
//        convertReadMe();
//        writeObjects("tmp.dat");
//        readObjects("tmp.dat");
        readObjectsViaBuffer();
    }
}
