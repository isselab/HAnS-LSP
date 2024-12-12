package se.isselab.HAnS.codeAnnotation;// Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/TextDocument.g4 by ANTLR 4.13.2

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
 * This class provides an empty implementation of {@link TextDocumentListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
@SuppressWarnings("CheckReturnValue")
public class TextDocumentBaseListener implements TextDocumentListener {
	enum Annotationtype{
		Begin,
		End,
		Line,
	}

	private class FeatureLocationTuple{
		private FeatureLocation featureLocation;
		private String feature;
		public FeatureLocationTuple(FeatureLocation featureLocation, String feature){
			this.featureLocation = featureLocation;
			this.feature = feature;
		}
		public FeatureLocation getFeatureLocation(){
			return featureLocation;
		}
		public String getFeature(){
			return feature;
		}
	}


	FileLogger logger;
	ArrayList<DocumentSymbol> SymbolList;
	FeatureModelTree featureModelTree;
	String currentdoc;
	private Annotationtype Atype;
	private ArrayList<FeatureLocationTuple> unfinishedFeatureLocations;

	public TextDocumentBaseListener(FeatureModelTree featureModelTree, String currentdoc) {
		logger = new FileLogger(TextDocumentBaseListener.class);
		this.featureModelTree = featureModelTree;
		this.currentdoc = currentdoc;
		SymbolList = new ArrayList<>();
		unfinishedFeatureLocations = new ArrayList<>();
	}

	public List<DocumentSymbol> getSymbolinformation(){

		return SymbolList;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterDocument(TextDocumentParser.DocumentContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitDocument(TextDocumentParser.DocumentContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBlock(TextDocumentParser.BlockContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBlock(TextDocumentParser.BlockContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterType(TextDocumentParser.TypeContext ctx) {
		String annotationTypeString = "";
		switch (ctx.getChild(0).getText().toLowerCase()){
			case "begin": Atype = Annotationtype.Begin;
				annotationTypeString = "begin";
				break;
			case "end": Atype = Annotationtype.End;
				annotationTypeString = "end";
				break;
			case "line": Atype = Annotationtype.Line;
				annotationTypeString = "line";
				break;
			default:
				logger.info("Unknown type: " + ctx.getChild(0).getText());

				break; //[23 16]
		}
		int line = ctx.start.getLine()-1;
		Range range =  new Range(new Position(line, ctx.start.getCharPositionInLine()), new Position(line, ctx.stop.getCharPositionInLine()+ctx.stop.getText().length()));
		SymbolList.add(new DocumentSymbol(annotationTypeString, SymbolKind.Method, range, range));
		if (!featureModelTree.getSubfeatures().isEmpty()) {
			logger.info("annotation found in line: " + line);
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitType(TextDocumentParser.TypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeatures(TextDocumentParser.FeaturesContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeatures(TextDocumentParser.FeaturesContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFeature(TextDocumentParser.FeatureContext ctx) {
		FeatureModelTree fmt = featureModelTree;
		if (!featureModelTree.getSubfeatures().isEmpty()) {
			if (ctx.getText().contains("::")) {
				String[] features = ctx.getText().split("::");
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

			} else {
				fmt = fmt.search(ctx.getText());
			}

			if (fmt != null) {
				int line = ctx.FEATURENAME(0).getSymbol().getLine() - 1;

				logger.info("feature: " + ctx.getText() + " found in line: " + line + " adding to tree: " + fmt.toString());


				Range range = new Range(new Position(line, ctx.start.getCharPositionInLine()), new Position(line, ctx.stop.getCharPositionInLine() + ctx.stop.getText().length()));
				SymbolList.add(new DocumentSymbol(ctx.getText(), SymbolKind.Constant, range, range));

				switch (Atype) {
					case Begin:
						FeatureLocation beginoffeaturelocation = new FeatureLocation(currentdoc, type.File, line);
						beginoffeaturelocation.setCharBegin(ctx.start.getCharPositionInLine());
						unfinishedFeatureLocations.add(new FeatureLocationTuple(beginoffeaturelocation, ctx.getText()));
						break;
					case Line:
						FeatureLocation featureLocation = new FeatureLocation(currentdoc, type.File, line, line);
						featureLocation.setCharBegin(ctx.start.getCharPositionInLine());
						featureLocation.setCharEnd(ctx.stop.getCharPositionInLine() + ctx.stop.getText().length());
						fmt.addlocation(featureLocation);
						break;
					case End:
						for (FeatureLocationTuple fl : unfinishedFeatureLocations) {
							if (fl.getFeature().equals(ctx.getText())) {
								fl.getFeatureLocation().setLineEnd(line);
								fl.getFeatureLocation().setCharEnd(ctx.stop.getCharPositionInLine() + ctx.stop.getText().length());
								fmt.addlocation(fl.getFeatureLocation());
								unfinishedFeatureLocations.remove(fl);
								break;
							}
						}
						break;
				}
			}
			else{
				logger.info("symbol not found in tree");
			}
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFeature(TextDocumentParser.FeatureContext ctx) { }

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