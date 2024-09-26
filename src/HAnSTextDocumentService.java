package se.isselab.HAnS.codeAnnotation;

import org.eclipse.lsp4j.*;

import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.isselab.HAnS.codeAnnotation.FeatureModelTree;

public class HAnSTextDocumentService implements TextDocumentService {
    private static final Logger logger = LoggerFactory.getLogger(HAnSTextDocumentService.class);
    private HAnSLanguageServer langugageServer;
    private FeatureModelTree tree;
    private FeatureModelTree currtree;
    private String eingabe;
    private Path currdoc;

    public HAnSTextDocumentService(HAnSLanguageServer x, FeatureModelTree y) {
        this.langugageServer = x;
        this.tree = y;
        this.currtree = tree;
    }


    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams completionParams) {
        // Provide completion item.
        return CompletableFuture.supplyAsync(() -> {
            List<CompletionItem> completionItems = new ArrayList<>();
            try {
                // Completion Item (Begin End blocks)
                CompletionItem completionItem = new CompletionItem();
                // Define the text to be inserted in to the file if the completion item is selected.
                completionItem.setInsertText("//&Begin[]\n//&End[]\n");
                // Set the label that shows when the completion drop down appears in the Editor.
                completionItem.setLabel("&Begin[]");
                // Set the completion kind. This is a snippet.
                // That means it replace character which trigger the completion and
                // replace it with what defined in inserted text.
                completionItem.setKind(CompletionItemKind.Snippet);
                // This will set the details for the snippet code which will help user to
                // understand what this completion item is.
                completionItem.setDetail("Creating a new block for feature");
                completionItems.add(completionItem);

                // Item 2 (Line block)
                CompletionItem completionItem1 = new CompletionItem();
                completionItem1.setInsertText("//&Line[]\n");
                completionItem1.setLabel("&Line[]");
                completionItem1.setKind(CompletionItemKind.Snippet);
                completionItem1.setDetail("Creating a new Line Annotation");
                completionItems.add(completionItem1);


            } catch (Exception e) {
                //TODO: Handle the exception.
            }

            // Return the list of completion items.
            return Either.forLeft(completionItems);
        });
    }


    @Override
    public CompletableFuture<CompletionItem> resolveCompletionItem(CompletionItem completionItem) {
        return null;
    }

    @Override
    public CompletableFuture<Hover> hover(HoverParams params) {

        return CompletableFuture.supplyAsync(() -> {
            int nextline = 0;
            int line = params.getPosition().getLine();
            int cha = params.getPosition().getCharacter();
            TextDocumentIdentifier doc = params.getTextDocument();
            String workline = null;
            try {
                BufferedReader text = new BufferedReader(new FileReader(doc.getUri()));
                while ((nextline - 1) <= line) {
                    workline = text.readLine();
                    nextline++;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (workline != null) {
                return hoverForReferences(workline, cha);

            }
            return null;
        });
    }
/*
    private Hover existiertImString(String input, int cha) {
        Hover hover = null;
        ArrayList<String> toCheck = new ArrayList<String>();
        ArrayList<String> isAvailable = new ArrayList<String>();
        toCheck.add("$Begin");
        toCheck.add("$End");
        toCheck.add("$Line");

        toCheck.addAll(tree.PreorderNames());

        for (int i = 0; i < toCheck.size(); i++) {
            if (input.contains(toCheck.get(i))) {
                isAvailable.add(toCheck.get(i));
            }
        }

        for (int i = 0; i < isAvailable.size(); i++) {
            if (input.indexOf(isAvailable.get(i)) < cha && cha < input.indexOf(isAvailable.get(i)) + isAvailable.get(i).length()) {

                switch (isAvailable.get(i)) {
                    case "$Begin":
                        hover = new Hover(
                                new MarkupContent(MarkupKind.PLAINTEXT, "Beginning of a Feature annotation block"));

                        break;
                    case "$End":
                        hover = new Hover(
                                new MarkupContent(MarkupKind.PLAINTEXT, "End of a Feature annotation block"));
                        break;
                    case "$Line":
                        hover = new Hover(
                                new MarkupContent(MarkupKind.PLAINTEXT, "Feature Line annotation"));
                        break;
                    default:
                        String featureDefinition = getFeatureDefinition(isAvailable.get(i));
                        hover = new Hover(
                                new MarkupContent(MarkupKind.PLAINTEXT, "a Feature \n \"Feature reference: \"" + featureDefinition)
                        );
                }

            }
        }
        return hover;
    }
*/

    //    @Override
    public CompletableFuture<SignatureHelp> signatureHelp(TextDocumentPositionParams textDocumentPositionParams) {
        return null;
    }


    //    @Override
    public CompletableFuture<List<? extends Location>> definition(TextDocumentPositionParams textDocumentPositionParams) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends Location>> references(ReferenceParams referenceParams) {
//        String documentUri = referenceParams.getTextDocument().getUri();
//        Position position = referenceParams.getPosition();
//
//        String documentText = getDocumentText(documentUri);
//
//        // Extract the symbol at the given position
//        String symbol = extractSymbolAtPosition(documentText, position);
//
//        // Find references to the symbol
//        List<Location> referenceLocations = findSymbolOccurrences(documentUri, documentText, symbol);
//
//        logger.info("Found references for symbol {}: {}", symbol, referenceLocations);
//
//        return CompletableFuture.completedFuture(referenceLocations);
        return null;
    }

    private Hover hoverForReferences(String selectedText, int cha) {
        List<String> keywords = new ArrayList<String>() {{
            add("$Begin");
            add("$End");
            add("$Line");
            addAll(tree.PreorderNames());
        }};

        List<String> availableKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            if (selectedText.contains(keyword)) {
                availableKeywords.add(keyword);
            }
        }

        for (String keyword : availableKeywords) {
            int startIndex = selectedText.indexOf(keyword);
            int endIndex = startIndex + keyword.length();

            if (startIndex < cha && cha < endIndex) {
                return createHoverForKeyword(keyword);
            }
        }

        return null;
    }

    private Hover createHoverForKeyword(String keyword) {
        MarkupContent markupContent = new MarkupContent();
        markupContent.setKind(MarkupKind.MARKDOWN);

        switch (keyword) {
            case "$Begin":
                markupContent.setValue("Beginning of a Feature annotation block");
                break;
            case "$End":
                markupContent.setValue("End of a Feature annotation block");
                break;
            case "$Line":
                markupContent.setValue("Feature Line annotation");
                break;
            default:
                String featureDefinition = getFeatureDefinition(keyword);
                if (featureDefinition != null) {
                    markupContent.setValue("a Feature \n \"Feature reference: \"" + featureDefinition);
                } else {
                    markupContent.setValue("Feature not defined.");
                }
                break;
        }

        Hover hover = new Hover(markupContent);
        return hover;
    }

    private String getFeatureDefinition(String featureName) {
        // Mock method: In an actual implementation, this should retrieve the definition from the feature model tree or symbol table
        // For demonstration, we return a simple string. Replace this logic as needed.
        FeatureModelTree h = searchForTree(tree, featureName);
        if (h != null) {
            return h.getLocation().getLocation() + " Line:" + h.getLocation().getLineBegin();
        }

        //TODO: fix this return
        return null;

    }

    private FeatureModelTree searchForTree(FeatureModelTree tree, String name) {

        if (Objects.equals(tree.getName(), name)) {
            return tree;
        }
        if (tree.getSubfeatures().isEmpty()) {
            return null;
        } else {
            for (FeatureModelTree tree1 : tree.getSubfeatures()) {
                FeatureModelTree tree2 = searchForTree(tree1, name);
                if (tree2 != null) {
                    return tree2;
                }
            }
            return null;
        }
    }


    //    @Override
    public CompletableFuture<List<? extends DocumentHighlight>> documentHighlight(TextDocumentPositionParams textDocumentPositionParams) {
        return null;
    }
    //$begin[parserdocument]
    private void buildTree() {
        int nextline = 1;
        String workline = "";
        int r = 0;
        ArrayList<String> pattern = new ArrayList<>();
        pattern.add("[.]*//\\$Begin\\[[a-z]*][.]*");
        pattern.add("[.]*//\\$End\\[[a-z]*][.]*");
        pattern.add("[.]*//\\$Line\\[[a-z]*][.]*");
        try {
            BufferedReader text = new BufferedReader(new FileReader(new File(currdoc.toString())));
            workline = text.readLine();
            while (workline != null) {

                // check workline for regex
                for (String i : pattern) {
                    String check = workline.strip();
                    if (check.matches(i)) {
                        int pos = 0;
                        pos = workline.indexOf("//$Begin[") + 9;
                        char n = workline.charAt(pos);
                        String FeatureName = "";
                        switch (pattern.indexOf(i)) {
                            case 0:

                                while(n != ']'){
                                    FeatureName += Character.toString(n);
                                    pos++;
                                    n = workline.charAt(pos);
                                }
                                if (!FeatureName.isEmpty()) {
                                    FeatureName = "undefinedName";
                                }
                                FeatureModelTree next = new FeatureModelTree(currtree,FeatureName,new FeatureLocation(currdoc,nextline-1));
                                currtree.append(next);
                                currtree = next;
                                break;
                            case 1:
                                while(n != ']'){
                                FeatureName += Character.toString(n);
                                pos++;
                                n = workline.charAt(pos);
                                if (!FeatureName.isEmpty()) {
                                    FeatureName = "undefinedName";
                                }
                                FeatureModelTree help = searchForTree(tree, FeatureName);
                                if (help != null) {
                                    help.getLocation().setLineEnd(nextline-1);
                                    currtree = currtree.getParent();
                                }
                                else{
                                    logger.info("missing $begin[] statement");
                                    //fehler merken
                                    //end statemente at line nextline-1 missing $begin
                                }
                            }
                                break;
                            case 2:
                                while(n != ']'){
                                    FeatureName += Character.toString(n);
                                    pos++;
                                    n = workline.charAt(pos);
                                }
                                if (!FeatureName.isEmpty()) {
                                    FeatureName = "undefinedName";
                                }
                                FeatureModelTree help2 = new FeatureModelTree(currtree,FeatureName,new FeatureLocation(currdoc,nextline-1,nextline-1));
                                currtree.append(help2);
                                break;
                                default:
                                    logger.info("parsing error at"+ (nextline-1));
                                    break;

                        }
                    }
                }
                workline = text.readLine();
                nextline++;



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        if(!checkTree()){
            //return errorreport
        }
        */
    }
//$End[parserdocument]
//    @Override
//    public CompletableFuture<List<? extends SymbolInformation>> documentSymbol(DocumentSymbolParams documentSymbolParams) {
//        return null;
//    }
//
//    @Override
//    public CompletableFuture<List<? extends Command>> codeAction(CodeActionParams codeActionParams) {
//        return null;
//    }

    @Override
    public CompletableFuture<List<? extends CodeLens>> codeLens(CodeLensParams codeLensParams) {
        return null;
    }

    @Override
    public CompletableFuture<CodeLens> resolveCodeLens(CodeLens codeLens) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> formatting(DocumentFormattingParams documentFormattingParams) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> rangeFormatting(DocumentRangeFormattingParams documentRangeFormattingParams) {
        return null;
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> onTypeFormatting(DocumentOnTypeFormattingParams documentOnTypeFormattingParams) {
        return null;
    }

    @Override
    public CompletableFuture<WorkspaceEdit> rename(RenameParams renameParams) {
        return null;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {

        currdoc = Path.of(params.getTextDocument().getUri());
        String uri = params.getTextDocument().getUri();
        logger.info("File has been opened : {}", uri);

    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {

        String uri = params.getTextDocument().getUri();
        List<TextDocumentContentChangeEvent> changes = params.getContentChanges();

        for (TextDocumentContentChangeEvent change : changes) {
            String newText = change.getText();
        }
        logger.info("File has been changed : {}", params);
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        logger.info("File has been closed : {}", params);

    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        String uri = params.getTextDocument().getUri();
        logger.info("File has been saved : {}", params);
    }

}
