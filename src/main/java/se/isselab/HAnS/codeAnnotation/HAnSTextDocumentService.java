package se.isselab.HAnS.codeAnnotation;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
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
import java.nio.file.Paths;

public class HAnSTextDocumentService implements TextDocumentService {
    private static FileLogger logger;
    private HAnSLanguageServer langugageServer;
    private FeatureModelTree tree;
    private FeatureModelTree currtree;
    private String eingabe;
    private Path currdoc;
    private Path currentFeatureModel;
    private Path projectroot;
    private HAnSWorkSpaceService workSpaceService;
    private List<WorkspaceFolder> workspaceFolders;
    private List<String> files = new ArrayList<>();
    private List<DocumentSymbol> symboslofcurrentfile;
    private List<DocumentSymbol> featuremodelsymbols;
    
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
    public void parseTextdocument(){
        try {
            Lexer l = new TextDocumentLexer(CharStreams.fromPath(currdoc));
            CommonTokenStream tokens = new CommonTokenStream(l);

            String comp = currdoc.toUri().toString().split("/")[currdoc.toUri().toString().split("/").length - 1];
            String uri = currdoc.toUri().toString();
            if(tree == null){
                parseFeaturetree();
            }
            ParseTreeListenerSymbolProvider tdbl;
            ParseTree ptree;
            if(comp != ".feature-model") {
                switch (comp.toLowerCase()) {
                    case ".feature-to-file":
                        FeatureToFileParser parser1 = new FeatureToFileParser(tokens);
                        ptree = parser1.document();
                        tdbl = new FeatureToFileBaseListener(tree, uri);
                        break;
                    case ".feature-to-folder":
                        FeatureToFolderParser parser2 = new FeatureToFolderParser(tokens);
                        ptree = parser2.features();
                        tdbl = new FeatureToFolderBaseListener(tree, uri);
                        break;
                    default:
                        TextDocumentParser parser0 = new TextDocumentParser(tokens);
                        ptree = parser0.document();
                        tdbl = new TextDocumentBaseListener(tree, uri);
                        break;
                }

                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(tdbl, ptree);
                symboslofcurrentfile = tdbl.getSymbolinformation();
            }
            else{
                symboslofcurrentfile = featuremodelsymbols;
            }
        } catch (IOException e) {
            logger.error("");
        }
    }
    public void parseTextdocument(String uri){

        try {
            Lexer l = new TextDocumentLexer(CharStreams.fromPath(Paths.get(new URI(uri))));
            CommonTokenStream tokens = new CommonTokenStream(l);
            String comp = uri.split("/")[uri.split("/").length - 1];
            ParseTreeListenerSymbolProvider tdbl;
            ParseTree ptree;
            if(comp != ".feature-model") {
                switch (comp.toLowerCase()) {
                    case ".feature-to-file":
                        FeatureToFileParser parser1 = new FeatureToFileParser(tokens);
                        ptree = parser1.document();
                        tdbl = new FeatureToFileBaseListener(tree, uri);
                        break;
                    case ".feature-to-folder":
                        FeatureToFolderParser parser2 = new FeatureToFolderParser(tokens);
                        ptree = parser2.features();
                        tdbl = new FeatureToFolderBaseListener(tree, uri);
                        break;
                    default:
                        TextDocumentParser parser0 = new TextDocumentParser(tokens);
                        ptree = parser0.document();
                        tdbl = new TextDocumentBaseListener(tree, uri);
                        break;
                }
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(tdbl, ptree);
                if (currdoc.toUri().equals(new URI(uri))) {
                    symboslofcurrentfile = tdbl.getSymbolinformation();
                    logger.info("symbols of current doc: " + tdbl.getSymbolinformation());
                }
            }
            else {
                symboslofcurrentfile = featuremodelsymbols;
            }

        } catch (IOException e) {
            logger.error("");
        } catch (URISyntaxException e) {
            logger.error("uri error");
        }
    }

    public void parseFeaturetree(){
        //walker erstellen listener erstellen
        logger.info("Parsing Featuretree");
        findProjektRoot();
        currentFeatureModel = findNextFeatureModel();

        if (currentFeatureModel != null) {
            try {
                Lexer l = new FeatureTreeLexer(CharStreams.fromPath(currentFeatureModel));
                CommonTokenStream tokens = new CommonTokenStream(l);
                FeatureTreeParser parser = new FeatureTreeParser(tokens);
                featurtrees.clear();
                ParseTree ptree = parser.featuretree();
                FeatureTreeBaseListener ftbl = new FeatureTreeBaseListener(featurenames);
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(ftbl, ptree);
                logger.info("Features found" + featurenames.toString());
                String featuretreerep ="";
                /*for(FeatureModelTree fmt : featurtrees){
                    featuretreerep += fmt.toString() + "\n";
                }
                logger.info("Featuretrees: " + featuretreerep );
                */
                tree = ftbl.getFeatureModelTree();
                 logger.info("Featuretrees: " + featurtrees.size());
                 featuremodelsymbols = ftbl.getSymbolinformation();
                 logger.info("symbols of featuremodel: " + featuremodelsymbols.toString());
            } catch (IOException e) {
                logger.error("");
            }
        }
    }

    public void parseFeatureTreeAfterChange(){
        if (currentFeatureModel != null) {
            try {
                Lexer l = new FeatureTreeLexer(CharStreams.fromPath(currentFeatureModel));
                CommonTokenStream tokens = new CommonTokenStream(l);
                FeatureTreeParser parser = new FeatureTreeParser(tokens);
                featurtrees.clear();
                ParseTree ptree = parser.featuretree();
                FeatureTreeBaseListener ftbl = new FeatureTreeBaseListener(featurenames);
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(ftbl, ptree);
                logger.info("Features found" + featurenames.toString());
                String featuretreerep ="";
                /*for(FeatureModelTree fmt : featurtrees){
                    featuretreerep += fmt.toString() + "\n";
                }
                logger.info("Featuretrees: " + featuretreerep );
                */
                tree = ftbl.getFeatureModelTree();
                logger.info("Featuretrees: " + featurtrees.size());
                featuremodelsymbols = ftbl.getSymbolinformation();
                logger.info("symbols of featuremodel: " + featuremodelsymbols.toString());
            } catch (IOException e) {
                logger.error("");
            }
        }
    }

    public Path findNextFeatureModel() {

        if (currdoc != null) {

            Path currparrent = currdoc.getParent();
            File currDir;
            Path featuremodel = null;
            Path endpath = currparrent.getRoot();
            if (projectroot != null) {
                endpath = projectroot;
            } else {
                logger.info("no workspacefolder found: searching whole path");
            }
            while (featuremodel == null && currparrent.startsWith(endpath)) {
                currDir = new File(currparrent.toString() + "\\" + ".feature-model");
                if (currDir.exists()) {
                    logger.info("found featuremodel at: " + currparrent.toString()+ "\\" + ".feature-model");
                    featuremodel = currDir.toPath();
                } else {
                    if (currparrent.getParent() != null) {
                        currparrent = currparrent.getParent();
                    } else {
                        logger.info("no feature model found");
                        break;

                    }
                }
            }
            return featuremodel;
        }
        return null;
/*
        if (workspaceFolders != null) {
            logger.info("worspace path is :" + workspaceFolders.getFirst().toString());//Updated condition
            try {
                Files.walk(Path.of(workspaceFolders.getFirst().getUri()))
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
     */
    }


    public void findProjektRoot(){
        if(currdoc != null){
            projectroot = null;
            Path currparrent = currdoc.getParent();
            File currDir;
            while (projectroot == null) {
                currDir = new File(currparrent.toString() + "\\" + "src");
                if (currDir.exists()) {
                    logger.info("found project root: " + currparrent.toString());
                    projectroot = Path.of(currDir.getParent());
                } else {
                    if (currparrent.getParent() != null) {
                        currparrent = currparrent.getParent();
                    } else {
                        logger.info("no project root found");
                        break;

                    }
                }
            }
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
                completionItem.setInsertText("//&Begin[]\n\n//&End[]\n");
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
                completionItem1.setInsertText("//&Line[]");
                completionItem1.setLabel("//&Line[]");
                completionItem1.setKind(CompletionItemKind.Snippet);
                completionItem1.setDetail("Creating a new Line Annotation");
                completionItems.add(completionItem1);

                CompletionItem completionItem2 = new CompletionItem();
                completionItem2.setInsertText("&Line[]");
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
                completionItem4.setInsertText("&Begin[]\n\n//&End[]");
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


                ArrayList<String> duplicates = tree.getDuplicates();
                //completion für featureliste
                logger.info("duplicates: "+duplicates.toString() );
                for(String feature : featurenames){
                    if(!duplicates.contains(feature)){
                        CompletionItem completionItemi = new CompletionItem();
                        completionItemi.setInsertText(feature);
                        completionItemi.setLabel(feature);
                        completionItemi.setKind(CompletionItemKind.Snippet);
                        completionItemi.setDetail("a feature defined in the model");
                        completionItems.add(completionItemi);
                    }
                }
                ArrayList<String> DuplicateFeatures = tree.getDuplicatesWithParrent();
                logger.info("duplicates ersetzt: "+DuplicateFeatures.toString() );
                if(!DuplicateFeatures.isEmpty()) {
                    for (String feature : DuplicateFeatures) {
                        CompletionItem completionItemi = new CompletionItem();
                        completionItemi.setInsertText(feature);
                        completionItemi.setLabel(feature);
                        completionItemi.setKind(CompletionItemKind.Snippet);
                        completionItemi.setDetail("a feature defined in the model");
                        completionItems.add(completionItemi);
                    }
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
            //return new Hover(new MarkupContent(MarkupKind.MARKDOWN, "No text available for analysis."));
            return null;
        }

        ArrayList<String> featurenameswithoutduplicates = new ArrayList<>(featurenames);
        for(String dup : tree.getDuplicates()){
            featurenameswithoutduplicates.remove(dup);
        }
        /*
        logger.info("duplicates: "+ tree.getDuplicates());
        logger.info("featurenameswithoutduplicates: "+ featurenameswithoutduplicates);
        logger.info("duplicateswithparrent: "+ tree.getDuplicatesWithParrent());

         */

        List<String> keywords = new ArrayList<String>() {{
            add("&Begin");
            add("&End");
            add("&Line");
            add("&begin");
            add("&end");
            add("&line");
            addAll(featurenameswithoutduplicates);
            addAll(tree.getDuplicatesWithParrent());
        }};
        //logger.info("duplicates: " + tree.getDuplicates());
        logger.info("keywords :" + keywords.toString() );
        List<String> availableKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            //logger.info("testing available keyword:" + keyword);
            if (selectedText.contains(keyword)){
                availableKeywords.add(keyword); // name::name
                logger.info("available keyword:" + keyword);
            }
        }

        if (availableKeywords.isEmpty()) {
            logger.info("No matching keywords found.");
            //return new Hover(new MarkupContent(MarkupKind.MARKDOWN, "No keyword found, availableKeywords.isEmpty"));
            return null;
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
            logger.info("keyword:" + keyword + " at: "+startIndex +", "+endIndex);
            if (keyword.equalsIgnoreCase("&begin") || keyword.equalsIgnoreCase("&end") || keyword.equalsIgnoreCase("&line")) {
                if (startIndex-1 < cha && cha < endIndex + 1) {
                    logger.info("found keyword:" + keyword);
                    return createHoverForKeyword(keyword);
                }
            }
            else{

                /*
                if (selectedText.charAt((startIndex - 1)) == '['  && selectedText.charAt((endIndex + 1)) == ']' ) {
                    if (startIndex < cha && cha < endIndex) {
                        logger.info("found keyword:" + keyword);
                        return createHoverForKeyword(keyword);
                    }
                }
                */
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

        switch (keyword.toLowerCase()) {
            case "&begin":
                markupContent.setValue("Beginning of a Feature annotation block");
                break;
            case "&end":
                markupContent.setValue("End of a Feature annotation block");
                break;
            case "&line":
                markupContent.setValue("Feature Line annotation");
                break;
            default:
                FeatureModelTree fmt;
                if (keyword.toLowerCase().contains("::")) {
                    //logger.info("contains: ::");
                    String[] features = keyword.toLowerCase().split("::");
                    FeatureModelTree feature = tree.search(features[0]);
                    //logger.info("feature:" + feature.getName());
                    for (int i = 1; i < features.length; i++) {
                        if (feature != null) {
                            feature = feature.getChiled(features[i]);
                            //logger.info("feature:" + feature.getName());
                        }
                        else {
                            break;
                        }
                    }
                    fmt = feature;

                } else {
                    fmt = tree.search(keyword.toLowerCase());
                }

                if(fmt != null){
                    String children = "";
                    boolean first = true;
                    for(FeatureModelTree subf : fmt.getSubfeatures()){
                        if(first){
                            children += subf.getName();
                            first = false;
                        }
                        else{
                            children += ", "+subf.getName();
                        }

                    }

                    if(fmt.getParent() != null){
                        if (fmt.getParent().getParent() != null){
                            markupContent.setValue(keyword + " is a Feature defined in the feature-model"
                                    + System.lineSeparator() + "parrent: " + fmt.getParent().getName()
                                    + System.lineSeparator() + "children: " + children
                                    + System.lineSeparator() + "Defined  at: " + (currentFeatureModel)
                                    + System.lineSeparator() + "in line: " + fmt.getFeatureLine());
                        }
                        else {
                            markupContent.setValue(keyword + " is a Feature defined in the feature-model"
                                    + System.lineSeparator() + "children: " + children
                                    + System.lineSeparator() + "Defined  at: " + (currentFeatureModel)
                                    + System.lineSeparator() + "in line: " + fmt.getFeatureLine());
                        }
                    }
                    else {
                        markupContent.setValue(keyword + " is a Feature defined in the feature-model"
                                + System.lineSeparator() + "Defined  at: " + (currentFeatureModel)
                                + System.lineSeparator() + "in line: " + fmt.getFeatureLine());
                    }
                }
                else {
                    markupContent.setValue(keyword + " is a Feature defined in the feature-model" + System.lineSeparator() + "Defined  at: " + (currentFeatureModel));
                }
                break;
        }


        Hover hover = new Hover(markupContent);
        return hover;
    }
    //    @Override
    public CompletableFuture<SignatureHelp> signatureHelp(TextDocumentPositionParams textDocumentPositionParams) {
        return null;
    }


    @Override
    public CompletableFuture<Either<List<? extends Location>, List<? extends LocationLink>>> definition(DefinitionParams params) {
        try {
            currdoc = Path.of(new URI(params.getTextDocument().getUri()));
            parseTextdocument();
        } catch (URISyntaxException e) {
            logger.info("URi problem");
        }
        //logger.info("symbols: "+symboslofcurrentfile.toString());
        logger.info("definition called with params: " + params.getTextDocument().getUri() + " at: " + params.getPosition().toString());
        return CompletableFuture.supplyAsync(()->{
                ArrayList<Location> locations = new ArrayList<>();
                for (DocumentSymbol ds : symboslofcurrentfile) {
                    if (ds.getRange().getStart().getLine() == params.getPosition().getLine()) {
                        if (ds.getRange().getStart().getCharacter() <= params.getPosition().getCharacter() &&
                                params.getPosition().getCharacter() <= ds.getRange().getEnd().getCharacter()) {

                            FeatureModelTree fmt = tree;
                            if(ds.toString().contains("::")) {
                                String[] features = ds.getName().split("::");
                                FeatureModelTree feature = fmt.search(features[0]);
                                for (int i = 1; i < features.length; i++) {
                                    feature = feature.getChiled(features[i]);
                                }
                            }
                            else{
                                fmt.search(ds.getName());
                            }


                            locations.add(new Location(currentFeatureModel.toUri().toString(),new Range(new Position(fmt.getFeatureLine(),fmt.getFeatureStart()),new Position(fmt.getFeatureLine(), fmt.getFeatureEnd()))));

                        }
                    }
                }
                return Either.forLeft(locations);

        });
    }

    @Override
    public CompletableFuture<List<? extends Location>> references(ReferenceParams referenceParams) {
        try {
            if(referenceParams.getTextDocument().getUri().equals(currentFeatureModel.toUri().toString())) {
                currdoc = Path.of(new URI(referenceParams.getTextDocument().getUri()));
                parseTextdocument();
            }
            else{
                logger.info("current file is featuremodel");
                symboslofcurrentfile = featuremodelsymbols;
            }
        } catch (URISyntaxException e) {
            logger.info("URi problem");
        }
        logger.info("document symbosl: " + symboslofcurrentfile.toString());
        logger.info("refrence called with params: " + referenceParams.getTextDocument().getUri() +" at: " + referenceParams.getPosition().toString());
         return CompletableFuture.supplyAsync(()->{
                 ArrayList<Location> locations = new ArrayList<>();
                 for (DocumentSymbol ds : symboslofcurrentfile) {
                     logger.info("symbol: " + ds.getName() + " symbol line: " + ds.getRange().getStart().getLine() + " line of param: " + referenceParams.getPosition().getLine());
                     if (ds.getRange().getStart().getLine() == referenceParams.getPosition().getLine()) {
                         if (ds.getRange().getStart().getCharacter() <= referenceParams.getPosition().getCharacter() &&
                                 referenceParams.getPosition().getCharacter() <= ds.getRange().getEnd().getCharacter()) {

                             FeatureModelTree fmt = tree;
                             if(ds.toString().contains("::")) {
                                 String[] features = ds.getName().split("::");
                                 FeatureModelTree feature = fmt.search(features[0]);
                                 for (int i = 1; i < features.length; i++) {
                                     feature = feature.getChiled(features[i]);
                                 }
                                 fmt = feature;
                             }
                             else{
                                 fmt = fmt.search(ds.getName());
                                 //fmt = searchForTree(fmt, ds.getName());
                             }

                             if(fmt != null) {
                                 logger.info("searched for: " +ds.getName() + " returned: " + fmt.toString() + " featurename: " + fmt.getName());
                                 //logger.info("fmt: " + fmt.toString() );

                                 for (FeatureLocation fl : fmt.getLocation()) {
                                     logger.info("fl:" + fl.getLocation() + " line: " + fl.getLineBegin());
                                     switch (fl.getType()) {
                                         case File:
                                             locations.add(new Location(fl.getLocation(), new Range(new Position(fl.getLineBegin(), fl.getCharBegin()), new Position(fl.getLineEnd(), fl.getCharEnd()))));
                                             break;
                                         case Folder:
                                             locations.add(new Location(fl.getLocation(), new Range(new Position(0, 0), new Position(0, 0))));
                                             break;
                                         default:
                                             break;
                                     }
                                 }
                             }
                             else{
                                 logger.info("symbol not found in tree");
                             }
                         }
                     }
                 }
                 return locations;
       });
    }

    /*
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
    */


    private FeatureModelTree searchForTree(FeatureModelTree tree, String name) {

        if (tree.getName().equalsIgnoreCase(name)) {
            return tree;
        }
        if (tree.getSubfeatures().isEmpty()) {
            return null;
        } else {
            for (FeatureModelTree tree1 : tree.getSubfeatures()) {
                if(searchForTree(tree1, name)!= null){
                    return searchForTree(tree1, name);
                }

            }
        }
        return null;
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
        if(!files.contains(uri)){
            parseTextdocument();
            files.add(uri);
        }
        if(currentFeatureModel == null){
            parseFeaturetree();
        }
        else {
            if (currentFeatureModel != findNextFeatureModel()) {
                parseFeaturetree();
            }
            else{
                parseFeatureTreeAfterChange();
                for(String file : files){
                    parseTextdocument(file);
                }
            }
        }




    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {

        String uri = params.getTextDocument().getUri();
        List<TextDocumentContentChangeEvent> changes = params.getContentChanges();



        logger.info("File has been changed : "+ params.getTextDocument().getUri() ); //logging change range?
        featurenames.clear();
        if (tree != null || Path.of( uri) == currentFeatureModel){
            parseFeatureTreeAfterChange();
            for(String file : files){
                parseTextdocument(file);
            }
        }
        parseTextdocument();
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        logger.info("File has been closed : {}"+ params.getTextDocument().getUri() );
        //remove datei

    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        String uri = params.getTextDocument().getUri();
        logger.info("File has been saved : {}"+ params.getTextDocument().getUri() );
    }

    public CompletableFuture<List<Either<SymbolInformation, DocumentSymbol>>> documentSymbol(DocumentSymbolParams params){
        String uri = params.getTextDocument().getUri();
        parseTextdocument(uri);
        //logger.info("symbol information: " + symboslofcurrentfile);

        return CompletableFuture.supplyAsync(()->{
            ArrayList<Either<SymbolInformation, DocumentSymbol>> list = new ArrayList<>();
            for (DocumentSymbol ds : symboslofcurrentfile){
                list.add(Either.forRight(ds));
            }
            return list;
        });
    }

}
