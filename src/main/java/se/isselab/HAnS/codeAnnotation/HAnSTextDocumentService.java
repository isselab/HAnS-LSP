package se.isselab.HAnS.codeAnnotation;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.lsp4j.*;

import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.services.TextDocumentService;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HAnSTextDocumentService implements TextDocumentService {
    private static FileLogger logger;
    private HAnSLanguageServer langugageServer;
    private FeatureModelTree tree;
    private FeatureModelTree currtree;
    private String eingabe;
    private Path currdoc;
    private Path currentFeatureModel;
    private HAnSWorkSpaceService workSpaceService;
    private List<WorkspaceFolder> workspaceFolders;
    
    private ArrayList<FeatureModelTree> featurtrees = new ArrayList<>();
    private ArrayList<String> featurenames = new ArrayList<>();

    private Path workspaceFolderPath;//Store the workspace folder path
    public HAnSTextDocumentService(HAnSLanguageServer x, FeatureModelTree y) {
       // System.setProperty("log4j.configurationFile", "log4jconfig.xml");
        logger = new FileLogger(HAnSTextDocumentService.class);
        this.langugageServer = x;
        this.tree = y;
        this.currtree = tree;
    }

    //Set the workspace folder path
    public void setWorkspaceFolderPath(Path workspaceFolderPath) {
        this.workspaceFolderPath = workspaceFolderPath;
    }

    public void setWorkspaceFolders(List<WorkspaceFolder> workspaceFolders) {
        this.workspaceFolders = workspaceFolders;
    }
    public void parseFeaturetree(){
        //walker erstellen listener erstellen
        logger.info("Parsing Featuretree");
        findNextFeatureModel();
        if (currentFeatureModel != null) {
            try {
                Lexer l = new FeatureTreeLexer(CharStreams.fromPath(currentFeatureModel));
                CommonTokenStream tokens = new CommonTokenStream(l);
                FeatureTreeParser parser = new FeatureTreeParser(tokens);
                ParseTree ptree = parser.featuretree();
                FeatureTreeBaseListener ftbl = new FeatureTreeBaseListener(featurtrees, featurenames);
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(ftbl, ptree);
                logger.info("Features found" + featurenames.toString());
                String featuretreerep ="";
                /*for(FeatureModelTree fmt : featurtrees){
                    featuretreerep += fmt.toString() + "\n";
                }
                logger.info("Featuretrees: " + featuretreerep );
                */
                 logger.info("Featuretrees: " + featurtrees.size());
            } catch (IOException e) {
                logger.error("");
            }
        }
    }

    public void findNextFeatureModel() {
        /*
        try {
            // Get the directory of the current document if it exists
            if (currdoc != null) {
                Path startDir = currdoc.getParent();  // Start search from the directory containing the current class

                // Find all files ending with .featureModel
                List<Path> featureModelFiles = new ArrayList<>();
                Files.walk(startDir)  // Use walk to traverse the directory recursively
                        .filter(Files::isRegularFile)
                        .filter(p -> p.getFileName().toString().equalsIgnoreCase(".feature-model"))
                        //.filter(path -> path.toString().endsWith(".feature-model"))  // Filter to get only .feature-model files
                        .forEach(featureModelFiles::add);  // Collect each matching file
                        //TODO walker should go through the rest of the project files        

                // Print or log found files
                for (Path file : featureModelFiles) {
                    //logger.info("Found feature model file: {}", file);
                    if(currentFeatureModel == null) {
                        currentFeatureModel = file;
                    }//konflickt lösen
                }
                logger.info("Found FeatureModel: " + currentFeatureModel.toString());
            } else {
                logger.warn("Current document path is not set. Cannot locate .featureModel files.");
            }
        }
        catch (IOException e) {
            logger.error("Error while searching for feature model files: " + e.toString());
        }
        */

        if (workspaceFolderPath != null) {
            logger.info("worspace path is :" + workspaceFolderPath.toString());//Updated condition
            try {
                Files.walk(workspaceFolderPath)
                        .filter(Files::isRegularFile)
                        .filter(p -> p.getFileName().toString().equalsIgnoreCase(".feature-model"))
                        .findFirst()
                        .ifPresent(featureModel -> currentFeatureModel = featureModel);

                if (currentFeatureModel != null) {
                    logger.info("Found feature model at: " + currentFeatureModel.toString());
                } else {
                    logger.info("No feature model file found in workspace.");
                }
            } catch (IOException e) {
                logger.error("Error while searching for feature model file: " + e.toString());
            }
        } else {
            logger.warn("Workspace folder path is not set. Cannot locate .feature-model files.");
        }
        }




    


    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams completionParams) {
        // Provide completion item.
        logger.info("completion");
        return CompletableFuture.supplyAsync(() -> {
            List<CompletionItem> completionItems = new ArrayList<>();
            try {
                // Completion Item (Begin End blocks)
                CompletionItem completionItem = new CompletionItem();
                // Define the text to be inserted in to the file if the completion item is selected.
                completionItem.setInsertText("//&Begin[]\n//&End[]\n");
                // Set the label that shows when the completion drop down appears in the Editor.
                completionItem.setLabel("//&Begin[] ... //&End[]");
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
                completionItem1.setLabel("//&Line[]");
                completionItem1.setKind(CompletionItemKind.Snippet);
                completionItem1.setDetail("Creating a new Line Annotation");
                completionItems.add(completionItem1);

                CompletionItem completionItem2 = new CompletionItem();
                completionItem2.setInsertText("&Line[]\n");
                completionItem2.setLabel("&Line[]");
                completionItem2.setKind(CompletionItemKind.Snippet);
                completionItem2.setDetail("Creating a new Line Annotation");
                completionItems.add(completionItem2);

                CompletionItem completionItem3 = new CompletionItem();
                completionItem3.setInsertText("&Begin[]\n");
                completionItem3.setLabel("&Begin[]");
                completionItem3.setKind(CompletionItemKind.Snippet);
                completionItem3.setDetail("Creating a new Begin Annotation");
                completionItems.add(completionItem3);

                CompletionItem completionItem4 = new CompletionItem();
                completionItem4.setInsertText("&Begin[]\n //&End[]");
                completionItem4.setLabel("&Begin[] ... //&End[]");
                completionItem4.setKind(CompletionItemKind.Snippet);
                completionItem4.setDetail("Creating a new block for feature");
                completionItems.add(completionItem4);

                CompletionItem completionItem5 = new CompletionItem();
                completionItem5.setInsertText("//&End[]");
                completionItem5.setLabel("//&End[]");
                completionItem5.setKind(CompletionItemKind.Snippet);
                completionItem5.setDetail("Creating a new End Annotation");
                completionItems.add(completionItem5);

                CompletionItem completionItem6 = new CompletionItem();
                completionItem6.setInsertText("&End[]");
                completionItem6.setLabel("&End[]");
                completionItem6.setKind(CompletionItemKind.Snippet);
                completionItem6.setDetail("Creating a new End Annotation");
                completionItems.add(completionItem6);


                //completion für featureliste
                for(String feature : featurenames){
                    CompletionItem completionItemi = new CompletionItem();
                    completionItemi.setInsertText(feature);
                    completionItemi.setLabel(feature);
                    completionItemi.setKind(CompletionItemKind.Snippet);
                    completionItemi.setDetail("a feature defined in the model");
                    completionItems.add(completionItemi);

                }


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
        logger.info("Hover request received.");

        return CompletableFuture.supplyAsync(() -> {
            int nextline = 0;
            int line = params.getPosition().getLine();
            int cha = params.getPosition().getCharacter();
            TextDocumentIdentifier doc = params.getTextDocument();
            Position position = params.getPosition();
            String workline = null;
            String filePath;

            try {
                URI uri = new URI(doc.getUri());
                filePath = Paths.get(uri).toString();
            } catch (Exception e) {
                throw new RuntimeException("Invalid URI: " + doc.getUri() + e);
            }
            try {
                BufferedReader text = new BufferedReader(new FileReader(filePath));
                logger.info("Document URI: {} " + doc.getUri());
                logger.info("Hover request received at line: {}, character: {} " + line+ " " + cha);
                while ((nextline ) <= line) {
                    workline = text.readLine();
                    nextline++;
                }
            } catch (FileNotFoundException e) {
                logger.info("FileNotFoundException e");
                throw new RuntimeException(e);
            } catch (IOException e) {
                logger.info("IOException e.");
                throw new RuntimeException(e);
            }
            if (workline != null) {
                logger.info("workline: "+ workline);
                return hoverForReferences(workline, cha);

            }
            return null;
        });
    }

    private Hover hoverForReferences(String selectedText, int cha) {
        logger.info("Entered hoverForReferences");

        // Additional check in hoverForReferences to avoid null references
        if (selectedText == null || selectedText.isEmpty()) {
            logger.info("No text available for analysis in hoverForReferences.");
            return new Hover(new MarkupContent(MarkupKind.MARKDOWN, "No text available for analysis."));
        }

        List<String> keywords = new ArrayList<String>() {{
            //add("Begin");
            //add("End");
            //add("Line");
            add("&Begin");
            add("&End");
            add("&Line");
            //add("Begin[");
            //add("End[");
            //add("Line[");
            //add("//$Begin");
            //add("//$End");
            //add("//$Line");
            //addAll(tree.PreorderNames());
            addAll(featurenames);
        }};
        logger.info("keywords :" + keywords.toString() );
        List<String> availableKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            //logger.info("testing available keyword:" + keyword);
            if (selectedText.contains(keyword)){
                availableKeywords.add(keyword);
                logger.info("available keyword:" + keyword);
            }
        }

        if (availableKeywords.isEmpty()) {
            logger.info("No matching keywords found.");
            return new Hover(new MarkupContent(MarkupKind.MARKDOWN, "No keyword found, availableKeywords.isEmpty"));
        }
        logger.info("found available keywords:" + availableKeywords.toString() );
        /*
        for (int i = Math.max(0, cha - 3); i < selectedText.length() && i <= cha + 3; i++) {
            char currentChar = selectedText.charAt(i);
            String potentialKeyword = "";
            for (String keyword : keywords) {
                if (keyword.charAt(0) == currentChar) {
                    potentialKeyword = keyword;
                    break;
                }
            }
            if (!potentialKeyword.isEmpty()) {
                // Check if the entire keyword is present starting from currentChar
                if (selectedText.substring(i).startsWith(potentialKeyword)) {
                    logger.info("Found keyword: " + potentialKeyword + " at position: " + i);
                    // Found the keyword at position i, return hover content
                    return createHoverForKeyword(potentialKeyword);
                }
            }
        }
        */
        for (String keyword : availableKeywords) {
            int startIndex = selectedText.indexOf(keyword);
            int endIndex = startIndex + keyword.length() -1;
            logger.info("keyword:" + keyword);
            if(selectedText.indexOf("[") == (startIndex - 1) && selectedText.indexOf("]") == (endIndex +1 )  ) {
                if (startIndex < cha && cha < endIndex) {
                    logger.info("found keyword:" + keyword);
                    return createHoverForKeyword(keyword);
                }
            }
        }
        return null;
    }

    private Hover createHoverForKeyword(String keyword) {
        logger.info("createHoverForKeyword");
        MarkupContent markupContent = new MarkupContent();
        markupContent.setKind(MarkupKind.PLAINTEXT); //MARKDOWN

        switch (keyword) {
            case "&Begin":
                markupContent.setValue("Beginning of a Feature annotation block");
                break;
            case "&End":
                markupContent.setValue("End of a Feature annotation block");
                break;
            case "&Line":
                markupContent.setValue("Feature Line annotation");
                break;
            default:
               // String featureDefinition = getFeatureDefinition(keyword);
                //if (featureDefinition != null) {
                markupContent.setValue(keyword + " is a Feature Feature defined in the feature-model" + System.lineSeparator() + "reference: "+ (currentFeatureModel) );
                //} else {
                //    markupContent.setValue("Feature not defined.");
                break;
        }

        Hover hover = new Hover(markupContent);
        return hover;
    }
    //hard coded hover method that works for tests:
    /**public CompletableFuture<Hover> hover(HoverParams params) {
        logger.info("Hover request received.");
        MarkupContent markupContent = new MarkupContent();
        markupContent.setKind(MarkupKind.MARKDOWN);
        markupContent.setValue("Test Hover Text");

        Hover hover = new Hover(markupContent);
        return CompletableFuture.completedFuture(hover);
    }*/


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

//    @Override
//    public CompletableFuture<List<? extends SymbolInformation>> documentSymbol(DocumentSymbolParams documentSymbolParams) {
//        return null;
//    }
//
//    @Override
//  public java.util.concurrent.CompletableFuture<java.util.List<org.eclipse.lsp4j.jsonrpc.messages.Either<Command,CodeAction>>> codeAction(CodeActionParams codeActionParams) {
//    return null
//  }

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
        logger.info("rename at:" + renameParams.getTextDocument().getUri().toString() + " " + renameParams.getPosition() );
        return null;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        logger.info("File has been opened:"+ params.getTextDocument().getUri() );
        String uri = params.getTextDocument().getUri();
        try {
            currdoc = Path.of(new URI(uri));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        featurenames.clear();
        parseFeaturetree();




    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {

        String uri = params.getTextDocument().getUri();
        List<TextDocumentContentChangeEvent> changes = params.getContentChanges();

        for (TextDocumentContentChangeEvent change : changes) {
            String newText = change.getText();
        }
        logger.info("File has been changed : "+ params.getTextDocument().getUri() ); //logging change range?
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        logger.info("File has been closed : {}"+ params.getTextDocument().getUri() );

    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        String uri = params.getTextDocument().getUri();
        logger.info("File has been saved : {}"+ params.getTextDocument().getUri() );
    }

}
