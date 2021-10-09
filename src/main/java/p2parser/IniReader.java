package p2parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import p2ch02.InputOutput;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;

public class IniReader {

    public static void main(String[] args) throws URISyntaxException, IOException {
        CharStream input = CharStreams.fromPath(Paths.get(IniReader.class.getResource("test.ini").toURI()));

        // create a lexer that feeds off of input CharStream
        IniLexer lexer = new IniLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        IniParser parser = new IniParser(tokens);

        ParseTree tree = parser.file();
        MapBuilder mb = new MapBuilder(new HashMap<>());

        mb.visit(tree);

        System.out.println(mb.getContent());
    }


}
