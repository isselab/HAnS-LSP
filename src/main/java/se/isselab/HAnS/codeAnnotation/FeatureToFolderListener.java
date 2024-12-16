package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFolder.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FeatureToFolderParser}.
 */
public interface FeatureToFolderListener extends ParseTreeListenerSymbolProvider {
	/**
	 * Enter a parse tree produced by {@link FeatureToFolderParser#features}.
	 * @param ctx the parse tree
	 */
	void enterFeatures(FeatureToFolderParser.FeaturesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFolderParser#features}.
	 * @param ctx the parse tree
	 */
	void exitFeatures(FeatureToFolderParser.FeaturesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureToFolderParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(FeatureToFolderParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureToFolderParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(FeatureToFolderParser.FeatureContext ctx);
}