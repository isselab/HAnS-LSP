package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFolder.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.eclipse.lsp4j.DocumentSymbol;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SymbolKind;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an empty implementation of {@link FeatureToFolderListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
@SuppressWarnings("CheckReturnValue")
public class FeatureToFolderBaseListener implements FeatureToFolderListener {
	FileLogger logger;
	ArrayList<DocumentSymbol>SymbolList;
	FeatureModelTree featureModelTree;
	String currentdoc;

	public FeatureToFolderBaseListener(FeatureModelTree featureModelTree, String currentdoc) {
		logger = new FileLogger(FeatureToFolderBaseListener.class);
		this.featureModelTree = featureModelTree;
		this.currentdoc = currentdoc;
		SymbolList = new ArrayList<>();
	}

	public List<DocumentSymbol> getSymbolinformation(){
		logger.info("symbols: " + SymbolList);
		return SymbolList;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeatures(FeatureToFolderParser.FeaturesContext ctx) {
		logger.info("enterFeatures: " +ctx.getText());
		for(FeatureToFolderParser.FeatureContext tk : ctx.feature()){
			logger.info("feature found: " + tk.getText());
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeatures(FeatureToFolderParser.FeaturesContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeature(FeatureToFolderParser.FeatureContext ctx) {
		logger.info("enterFeature: " + ctx.getText());
		FeatureModelTree fmt = featureModelTree;
		logger.info("featuremodel: "+ fmt.toString());

		for (TerminalNode tn : ctx.FEATURENAME()){
			logger.info("feature name: " + tn.getText());
		}

		if(ctx.getText().contains("::")) {
			String[] features = ctx.getText().split("::");
			FeatureModelTree feature = featureModelTree.search(features[0]);
			for (int i = 1; i < features.length; i++) {
				if (feature != null) {
					int j= i;
					String possibleChild = features[i];
					while(j+1 < features.length) {
						possibleChild += ("::" + features[j+1]);
						if (feature.getChiled(possibleChild) != null) {
							feature = feature.getChiled(possibleChild);
							break;
						}
						j++;
					}
					if(j+1 >= features.length) {
						feature = feature.getChiled(features[i]);
					}
				}
				else {
					break;
				}
			}
			fmt = feature;
		}
		else{
			fmt = fmt.search(ctx.getText());
		}

		if(fmt != null) {
			fmt.addlocation(new FeatureLocation(currentdoc, type.Folder));
			int line = ctx.start.getLine() - 1;


			//Range range = new Range(new Position(line, ctx.start.getStartIndex()), new Position(line, ctx.stop.getStopIndex()));
			Range range = new Range(new Position(line, ctx.start.getCharPositionInLine()), new Position(line, ctx.stop.getCharPositionInLine() + ctx.stop.getText().length()));
			SymbolList.add(new DocumentSymbol(ctx.getText(), SymbolKind.Constant, range, range));

		}
		else {
			logger.info("fmt is null");
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeature(FeatureToFolderParser.FeatureContext ctx) { }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) {
		//logger.info("enterEveryRule: " + ctx.getText());
	}
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