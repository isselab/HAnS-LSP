package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/TextDocument.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TextDocumentParser}.
 */
public interface TextDocumentListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link TextDocumentParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(TextDocumentParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(TextDocumentParser.FeatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#begin}.
	 * @param ctx the parse tree
	 */
	void enterBegin(TextDocumentParser.BeginContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#begin}.
	 * @param ctx the parse tree
	 */
	void exitBegin(TextDocumentParser.BeginContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(TextDocumentParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(TextDocumentParser.EndContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(TextDocumentParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(TextDocumentParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link TextDocumentParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(TextDocumentParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link TextDocumentParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(TextDocumentParser.TextContext ctx);
}