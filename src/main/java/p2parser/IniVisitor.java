// Generated from /Users/csengeviragmaruzsi/workspace/workspace/corejava/src/main/java/p2parser/Ini.g4 by ANTLR 4.9.1
package p2parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link IniParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface IniVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link IniParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(IniParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link IniParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(IniParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link IniParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(IniParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code validDefinition}
	 * labeled alternative in {@link IniParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValidDefinition(IniParser.ValidDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyDefinition}
	 * labeled alternative in {@link IniParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyDefinition(IniParser.EmptyDefinitionContext ctx);
}