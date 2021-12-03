// Generated from /Users/csengeviragmaruzsi/workspace/workspace/corejava/src/main/java/p2parser/Ini.g4 by ANTLR 4.9.1
package p2parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IniParser}.
 */
public interface IniListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IniParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(IniParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IniParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(IniParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IniParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(IniParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link IniParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(IniParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link IniParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(IniParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link IniParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(IniParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code validDefinition}
	 * labeled alternative in {@link IniParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterValidDefinition(IniParser.ValidDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code validDefinition}
	 * labeled alternative in {@link IniParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitValidDefinition(IniParser.ValidDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyDefinition}
	 * labeled alternative in {@link IniParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterEmptyDefinition(IniParser.EmptyDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyDefinition}
	 * labeled alternative in {@link IniParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitEmptyDefinition(IniParser.EmptyDefinitionContext ctx);
}