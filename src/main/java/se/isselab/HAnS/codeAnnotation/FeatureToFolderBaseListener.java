package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFolder.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.ParserRuleContext;
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

		return SymbolList;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeatures(FeatureToFolderParser.FeaturesContext ctx) { }
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
		FeatureModelTree fmt = featureModelTree;
		if(ctx.toString().contains("::")) {
			String[] features = ctx.toString().split("::");
			FeatureModelTree feature = featureModelTree.search(features[0]);
			for (int i = 1; i < features.length; i++) {
				if (feature != null) {
					feature = feature.getChiled(features[i]);
				}
				else {
					break;
				}
			}
			fmt = feature;
		}
		else{
			fmt = fmt.search(ctx.toString());
		}

		if(fmt != null) {
			fmt.addlocation(new FeatureLocation(currentdoc, type.Folder));
			int line = ctx.FEATURENAME(0).getSymbol().getLine() - 1;


			//Range range = new Range(new Position(line, ctx.start.getStartIndex()), new Position(line, ctx.stop.getStopIndex()));
			Range range = new Range(new Position(line, ctx.start.getCharPositionInLine()), new Position(line, ctx.stop.getCharPositionInLine() + ctx.stop.getText().length()));
			SymbolList.add(new DocumentSymbol(ctx.FEATURENAME().toString(), SymbolKind.Constant, range, range));

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