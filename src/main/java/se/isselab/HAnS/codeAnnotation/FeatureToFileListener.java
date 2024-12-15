package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFile.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FeatureToFileParser}.
 */
public interface FeatureToFileListener extends ParseTreeListenerSymbolProvider {
	/**
	 * Enter a parse tree produced by {@link FeatureToFileParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(FeatureToFileParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFileParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(FeatureToFileParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureToFileParser#mapping}.
	 * @param ctx the parse tree
	 */
	void enterMapping(FeatureToFileParser.MappingContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFileParser#mapping}.
	 * @param ctx the parse tree
	 */
	void exitMapping(FeatureToFileParser.MappingContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureToFileParser#files}.
	 * @param ctx the parse tree
	 */
	void enterFiles(FeatureToFileParser.FilesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFileParser#files}.
	 * @param ctx the parse tree
	 */
	void exitFiles(FeatureToFileParser.FilesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureToFileParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(FeatureToFileParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFileParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(FeatureToFileParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureToFileParser#features}.
	 * @param ctx the parse tree
	 */
	void enterFeatures(FeatureToFileParser.FeaturesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFileParser#features}.
	 * @param ctx the parse tree
	 */
	void exitFeatures(FeatureToFileParser.FeaturesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureToFileParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(FeatureToFileParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFileParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(FeatureToFileParser.FeatureContext ctx);
}