package se.isselab.HAnS.codeAnnotation;
// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureTree.g4 by ANTLR 4.13.2

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
	ArrayList<FeatureModelTree> trees;
	FeatureModelTree root = new FeatureModelTree(null, ".feature-model");
	FeatureModelTree currentTree = root ;
	ArrayList<String> features;
	ArrayList<FeatureTreeParser.FeatureContext> featureContexts = new ArrayList<>();
	private static FileLogger logger;

	public FeatureTreeBaseListener(ArrayList<FeatureModelTree> trees, ArrayList<String> features) {
		logger = new FileLogger(FeatureTreeBaseListener.class);
		this.trees = trees;
		this.features = features;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeaturetree(FeatureTreeParser.FeaturetreeContext ctx) {
		logger.info("ChildCountFeaturetree:" + ctx.getChildCount());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeaturetree(FeatureTreeParser.FeaturetreeContext ctx) {
		int currentindentdepht = 0;
		int featurecount = 0;
		for(FeatureTreeParser.FeatureContext f : ctx.feature()) {
			if(f.FEATURENAME() == null) {
				if(currentTree.getParent() != null) {
					currentTree = currentTree.getParent();
				}
			}
			else {
				/*
				if (f.WS(0) != null) {
					logger.info("currentindentdepht: " + currentindentdepht + " WSchildcount: " + f.WS(0).toString()+ " in line: " + featurecount);
					while (currentindentdepht  > f.getChild(0).getChildCount()) {

						if (currentTree.getParent() != null) {
							currentTree = currentTree.getParent();
							logger.info("escaping featuretree in line: " + featurecount);
						}
						currentindentdepht++;
					}
					currentindentdepht = f.WS(0).getChildCount();
				}
				*/




				String LO = "NONE";
				boolean isOPtional = false;
				if (f.LO() != null) {
					LO = f.LO().getText();
				}
				if (f.OPTIONAL() != null) {
					isOPtional = true;
				}
				FeatureModelTree t = new FeatureModelTree(currentTree, f.FEATURENAME().toString(), isOPtional, LO);
				currentTree.addSubFeatureTree(t);
				currentTree = t;
				currentindentdepht ++;
				featurecount++;
			}
		}
		trees.add(root);
		logger.info("FeatureTree:" + root.toString());
		logger.info("FeatureTree:" + root.PreorderNames());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeature(FeatureTreeParser.FeatureContext ctx) {
		featureContexts.add(ctx);
		if(ctx.FEATURENAME() != null) {
			features.add(ctx.FEATURENAME().toString());
			logger.info("foundfeature:" + ctx.FEATURENAME().getText());
		}
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