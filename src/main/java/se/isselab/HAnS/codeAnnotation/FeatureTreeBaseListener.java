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

	private int currentindentdepht = 0;
	private int featurecount = 0;
	private int line = 1;

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
		currentindentdepht = 0;
		featurecount = 0;
		line = 1;
		logger.info("ChildCountFeaturetree:" + ctx.getChildCount());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeaturetree(FeatureTreeParser.FeaturetreeContext ctx) {
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
			logger.info("foundfeature:" + ctx.FEATURENAME().getText() + " in line: "+ line);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeature(FeatureTreeParser.FeatureContext ctx) {
		int lineindent = 0;
		if(ctx.FEATURENAME() == null) {
			logger.info("empty line in line: " + line);
		}
		else {
			/*
			if (ctx.WS() != null) {
				logger.info("currentindentdepht: " + currentindentdepht + " WSchildcount: " + ctx.WS().size() +   " in line: " + line);
				while (currentindentdepht  > ctx.WS().size()) {
					if (currentTree.getParent() != null) {
						currentTree = currentTree.getParent();
						logger.info("escaping featuretree in line: " + line +" currentindent: "+ currentindentdepht + " ws size: " +ctx.WS().size() );
					}
					currentindentdepht++;
				}
				currentindentdepht = ctx.WS().size();
			}
			*/

			String LO = "NONE";
			boolean isOPtional = false;
			if (ctx.LO() != null) {
				LO = ctx.LO().getText();
				for(int i =0; i < ctx.getChildCount(); i++) {
					if(!ctx.getChild(i).equals(ctx.LO())) {
						lineindent++;
					}
					else {
						break;
					}
				}
			}
			else{
				for(int i =0; i < ctx.getChildCount(); i++) {
					if(!ctx.getChild(i).equals(ctx.FEATURENAME())) {
						lineindent++;
					}
					else {
						break;
					}
				}
			}
			if (ctx.OPTIONAL() != null) {
				isOPtional = true;
			}
			logger.info("currentindentdepht: " + currentindentdepht + " WSchildcount: " + lineindent +   " in line: " + line);
			int workingindent = lineindent;
			int workingcurrentindent = currentindentdepht;
			while ( workingcurrentindent > workingindent) {
				if (currentTree.getParent() != null) {
					currentTree = currentTree.getParent();
					logger.info("escaping featuretree in line: " + line +" currentindent: "+ workingcurrentindent + " ws size: " +workingindent );
				}
				logger.info("diffrent WS in line : " + line +" currentindent: "+ workingcurrentindent + " ws size: " +workingindent );
				workingindent += 4;
			}
			currentindentdepht = lineindent;



			FeatureModelTree t = new FeatureModelTree(currentTree, ctx.FEATURENAME().toString(), isOPtional, LO);
			currentTree.addSubFeatureTree(t);
			currentTree = t;
			currentindentdepht ++;
			featurecount++;
		}
		line++;
	}

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