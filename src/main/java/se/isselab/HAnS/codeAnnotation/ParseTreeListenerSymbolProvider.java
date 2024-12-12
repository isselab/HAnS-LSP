package se.isselab.HAnS.codeAnnotation;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.eclipse.lsp4j.DocumentSymbol;

import java.util.List;

public interface ParseTreeListenerSymbolProvider extends ParseTreeListener {

    List<DocumentSymbol> getSymbolinformation();

}
