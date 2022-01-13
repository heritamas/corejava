package p2ch08;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Scripting {

    static final ScriptEngineManager manager = new ScriptEngineManager();

    public static void main(String[] args) throws URISyntaxException, IOException {
        manager.getEngineFactories()
                .stream()
                .map(ScriptEngineFactory::getEngineName)
                .forEach(System.out::println);


        ScriptEngine engine = manager.getEngineByName("nashorn");
        URL scriptUrl = Scripting.class.getResource("test.js");
        String script = Files.readString(Paths.get(scriptUrl.toURI()));

        // execute script using variables coming from data.csv

    }
}
