package se.isselab.HAnS.codeAnnotation; // Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFile.g4 by ANTLR 4.13.2

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
 * This class provides an empty implementation of {@link FeatureToFileListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
@SuppressWarnings("CheckReturnValue")
public class FeatureToFileBaseListener implements FeatureToFileListener {
	FileLogger logger;
	ArrayList<DocumentSymbol> SymbolList;
	FeatureModelTree featureModelTree;
	String currentdoc;
	ArrayList<String> files;

	public FeatureToFileBaseListener(FeatureModelTree featureModelTree, String currentdoc) {
		logger = new FileLogger(FeatureToFileBaseListener.class);
		this.featureModelTree = featureModelTree;
		this.currentdoc = currentdoc;
		files = new ArrayList<>();
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
	@Override public void enterDocument(FeatureToFileParser.DocumentContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitDocument(FeatureToFileParser.DocumentContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMapping(FeatureToFileParser.MappingContext ctx) {

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMapping(FeatureToFileParser.MappingContext ctx) {
		files.clear();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFiles(FeatureToFileParser.FilesContext ctx) {
		for (TerminalNode tm : ctx.Dateiname() ){
			files.add(tm.getText());

			int line = tm.getSymbol().getLine()-1;
			Range range = new Range(new Position(line, tm.getSymbol().getCharPositionInLine()), new Position(line, tm.getSymbol().getCharPositionInLine()+tm.getText().length()));
			SymbolList.add(new DocumentSymbol(tm.toString(), SymbolKind.Variable, range, range));
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFiles(FeatureToFileParser.FilesContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeatures(FeatureToFileParser.FeaturesContext ctx) {
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeatures(FeatureToFileParser.FeaturesContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeature(FeatureToFileParser.FeatureContext ctx) {
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
			for (String file : files) {
				fmt.addlocation(new FeatureLocation(currentdoc + "\\" + file, type.File));
			}

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
	@Override public void exitFeature(FeatureToFileParser.FeatureContext ctx) { }

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