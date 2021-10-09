package p2parser;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapBuilder implements IniVisitor<Void> {

    Map<String, Map<String, String >> content;
    ParseTreeProperty<Optional<Map.Entry<String, String>>> definitions = new ParseTreeProperty<>();

    public MapBuilder(Map<String, Map<String, String>> content) {
        this.content = content;
    }

    public Map<String, Map<String, String>> getContent() {
        return content;
    }

    @Override
    public Void visitFile(IniParser.FileContext ctx) {
        ctx.section().stream().forEach(this::visitSection);
        return null;
    }

    @Override
    public Void visitSection(IniParser.SectionContext ctx) {
        ctx.definition().stream().forEach(def -> def.accept(this));

        String sectionName = ctx.header().ID().getText();
        Map<String, String> sectionContent = ctx.definition().stream()
                .map(definitions::get)
                .flatMap(Optional::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        content.put(sectionName, sectionContent);

        return null;
    }

    @Override
    public Void visitHeader(IniParser.HeaderContext ctx) {
        return null;
    }

    @Override
    public Void visitValidDefinition(IniParser.ValidDefinitionContext ctx) {
        String value = ctx.VALUE().getText().replaceAll("\'|\"", "");
        definitions.put(ctx, Optional.of(new AbstractMap.SimpleEntry<>(ctx.ID().getText(), value)));
        return null;
    }

    @Override
    public Void visitEmptyDefinition(IniParser.EmptyDefinitionContext ctx) {
        definitions.put(ctx, Optional.empty());
        return null;
    }

    @Override
    public Void visit(ParseTree parseTree) {
        parseTree.accept(this);
        return null;
    }

    @Override
    public Void visitChildren(RuleNode ruleNode) {
        for (int i = 0 ; i < ruleNode.getChildCount(); ++i) {
            ruleNode.getChild(i).accept(this);
        }
        return null;
    }

    @Override
    public Void visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Void visitErrorNode(ErrorNode errorNode) {
        return null;
    }
}
