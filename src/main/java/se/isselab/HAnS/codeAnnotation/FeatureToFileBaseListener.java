package se.isselab.HAnS.codeAnnotation; // Generated from C:/Users/Tim/Documents/GitHub/HAnS-LSP/src/main/java/se/isselab/HAnS/codeAnnotation/FeatureToFile.g4 by ANTLR 4.13.2

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.eclipse.lsp4j.DocumentSymbol;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.SymbolKind;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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
		logger.info("symbollist: " + SymbolList);
		return SymbolList;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterDocument(FeatureToFileParser.DocumentContext ctx) {
		logger.info("enter Document");
	}
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
		logger.info("enter Mapping");
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
		for (FeatureToFileParser.FileContext fc : ctx.file() ){
			files.add(fc.getText());
			logger.info("found file:" + fc.getText());
			int line = fc.start.getLine()-1;
			Range range = new Range(new Position(line, fc.start.getCharPositionInLine()), new Position(line, fc.start.getCharPositionInLine()+fc.getText().length()));
			SymbolList.add(new DocumentSymbol(fc.getText(), SymbolKind.Variable, range, range));
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFiles(FeatureToFileParser.FilesContext ctx) { }

	@Override
	public void enterFile(FeatureToFileParser.FileContext ctx) {

	}

	@Override
	public void exitFile(FeatureToFileParser.FileContext ctx) {

	}

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
		logger.info("found feature: " +ctx.getText());
		FeatureModelTree fmt = featureModelTree;
		if(ctx.getText().contains("::")) {
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
		}
		else{
			fmt = fmt.search(ctx.getText());
		}
		if(fmt != null) {
			String parrent = "/";
			int index = currentdoc.lastIndexOf('/');
			if (index > 0) {
				parrent = currentdoc.substring(0, index);
			}
			for (String file : files) {
				File f = new File(parrent + "/" + file);
				if(f.exists()) {
					if(file.endsWith(".java")||file.endsWith(".c")||file.endsWith(".json")||file.endsWith(".yaml")) {
						fmt.addlocation(new FeatureLocation(parrent + "/" + file, type.File));
					}
				}
			}

			fmt.addlocation(new FeatureLocation(currentdoc , type.File, ctx.start.getLine()-1,ctx.start.getLine()-1));

			int line = ctx.String(0).getSymbol().getLine() - 1;

			//Range range = new Range(new Position(line, ctx.start.getStartIndex()), new Position(line, ctx.stop.getStopIndex()));
			Range range = new Range(new Position(line, ctx.start.getCharPositionInLine()), new Position(line, ctx.stop.getCharPositionInLine() + ctx.stop.getText().length()));
			SymbolList.add(new DocumentSymbol(ctx.getText(), SymbolKind.Constant, range, range));
		}
		else{
			logger.info("fmt is null");
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