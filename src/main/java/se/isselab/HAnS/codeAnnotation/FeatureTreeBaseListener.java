package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Taymo/Documents/GitHub/HAnS-LSP/src/FeatureTree.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an empty implementation of {@link FeatureTreeListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
@SuppressWarnings("CheckReturnValue")
public class FeatureTreeBaseListener implements FeatureTreeListener {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	ArrayList<FeatureModelTree> trees;
	ArrayList<String> features;
	private static FileLogger logger;

	public FeatureTreeBaseListener(ArrayList<FeatureModelTree> trees, ArrayList<String> features) {
		logger = new FileLogger(FeatureTreeBaseListener.class);
		this.trees = trees;
		this.features = features;
	}
	@Override public void enterFeaturetree(FeatureTreeParser.FeaturetreeContext ctx) {
		logger.info("ChildCountFeaturetree:" + ctx.getChildCount());
		FeatureModelTree t = new FeatureModelTree(null,ctx.getChild(0).getText());
		List<ParseTree> ruletree = ctx.children;
		for (ParseTree parseTree : ruletree) {
			FeatureModelTree h = new FeatureModelTree(t);
			t.append(h);
			}
		trees.add(t);
		}
		//baum erstellen namen suchen auf jeziger regel (feature)
		//children nach subfeatures nach namen durchsuchen (feature, featuretree -> feature)(ghet nicht)

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeaturetree(FeatureTreeParser.FeaturetreeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeature(FeatureTreeParser.FeatureContext ctx) {

		features.add(ctx.getChild(0).getText());
		logger.info("foundfeature:" + ctx.getChild(0).getText() );

		/*
		for(int i = 0; i<ctx.getChildCount(); i++ ) {
			features.add(ctx.getChild(i).getText());
			logger.info("foundfeature:" + ctx.getChild(i).getText() );
		}
		*/

		//feature namen sind in depth first search daher linksableitung
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeature(FeatureTreeParser.FeatureContext ctx) { }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) { }
}