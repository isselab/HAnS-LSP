package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/TextDocument.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TextDocumentParser}.
 */
public interface TextDocumentListener extends ParseTreeListenerSymbolProvider {
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(TextDocumentParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(TextDocumentParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TextDocumentParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TextDocumentParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TextDocumentParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TextDocumentParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#features}.
	 * @param ctx the parse tree
	 */
	void enterFeatures(TextDocumentParser.FeaturesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#features}.
	 * @param ctx the parse tree
	 */
	void exitFeatures(TextDocumentParser.FeaturesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(TextDocumentParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(TextDocumentParser.FeatureContext ctx);
}