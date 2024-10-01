// Generated from C:/Users/Taymo/Documents/GitHub/HAnS-LSP/src/FeatureTree.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FeatureTreeParser}.
 */
public interface FeatureTreeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FeatureTreeParser#featuretree}.
	 * @param ctx the parse tree
	 */
	void enterFeaturetree(FeatureTreeParser.FeaturetreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureTreeParser#featuretree}.
	 * @param ctx the parse tree
	 */
	void exitFeaturetree(FeatureTreeParser.FeaturetreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FeatureTreeParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(FeatureTreeParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FeatureTreeParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(FeatureTreeParser.FeatureContext ctx);
}