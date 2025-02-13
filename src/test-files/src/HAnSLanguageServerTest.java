

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.TextDocumentService;
import se.isselab.HAnS.codeAnnotation.HAnSTextDocumentService;

public class HAnSLanguageServerTest {
  private static final Logger log = LogManager.getLogger(HAnSLanguageServerTest.class);

  public static void main(String[] args) {
    testHover();
    testCompletion();
    testDefinition();
    testReference();

    System.out.println("all tests are correct");
  }


  public static void testHover() {

    TextDocumentService textDocumentService = new HAnSTextDocumentService();

    URI documentUri = Path.of("C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\src\\test-files\\testproject\\.feature-model").toUri();
    DidOpenTextDocumentParams params = new DidOpenTextDocumentParams();

    TextDocumentIdentifier textDocumentIdentifier = new TextDocumentIdentifier(documentUri.toString());

    TextDocumentItem tDocumentItem = null;
    try {
      System.out.println(documentUri.toString());
      File test = new File(documentUri);
      BufferedReader text = new BufferedReader(new FileReader(new File(documentUri)));

      String workline = "";
      String result = "";
      int line = 0;

      workline = text.readLine();
      while (workline != null) {

        result += workline + "\n";
        System.out.println(workline);
        workline = text.readLine();


      }
      tDocumentItem = new TextDocumentItem(documentUri.toString(), "feature-model", 1, result);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");

    } catch (IOException e1) {
      System.out.println("IO Exception");
    }

    params.setTextDocument(tDocumentItem);
    textDocumentService.didOpen(params);

    Position position = new Position(0, 1);
    HoverParams hoverParams = new HoverParams();
    hoverParams.setPosition(position);
    hoverParams.setTextDocument(textDocumentIdentifier);
    Hover hover = null;
    CompletableFuture<Hover> hoverFuture = textDocumentService.hover(hoverParams);
    if (hoverFuture != null) {
      try {
        hover = hoverFuture.get();
      } catch (InterruptedException | ExecutionException e) {

        System.out.println("completable future is nullll");

      }
    } else {
      System.out.println("CompletableFuture is null");
    }


    MarkupContent markupContent = new MarkupContent();
    markupContent.setKind(MarkupKind.PLAINTEXT); // MARKDOWN
    markupContent.setValue("Snake_Game" + " is a Feature defined in the feature-model"
            + System.lineSeparator() + "children: " + "Playing_Area, Snake, Controls, GameState, DataTypes"
            + System.lineSeparator() + "Defined  at: " + (Path.of(documentUri))
            + System.lineSeparator() + "in line: " + 0);

    Hover toCompare = new Hover(markupContent);

    assertNotNull(hover);
    if (hover != null) {
      assertNotNull(hover.getContents());
      assertEquals(hover, toCompare);
    }
  }

  public static void testCompletion() {

    TextDocumentService textDocumentService = new HAnSTextDocumentService();

    URI documentUri = Path.of("C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\src\\test-files\\testproject\\.feature-model").toUri();
    DidOpenTextDocumentParams params = new DidOpenTextDocumentParams();

    TextDocumentIdentifier textDocumentIdentifier = new TextDocumentIdentifier(documentUri.toString());

    TextDocumentItem tDocumentItem = null;

    try {
      System.out.println(documentUri.toString());
      File test = new File(documentUri);
      BufferedReader text = new BufferedReader(new FileReader(new File(documentUri)));

      String workline = "";
      String result = "";
      int line = 0;

      workline = text.readLine();
      while (workline != null) {

        result += workline + "\n";
        System.out.println(workline);
        workline = text.readLine();


      }
      tDocumentItem = new TextDocumentItem(documentUri.toString(), "feature-model", 1, result);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");

    } catch (IOException e1) {
      System.out.println("IO Exception");
    }
    params.setTextDocument(tDocumentItem);
    textDocumentService.didOpen(params);

    Position position = new Position(0, 1);

    CompletionParams completionParam = new CompletionParams();
    completionParam.setPosition(position);
    completionParam.setTextDocument(textDocumentIdentifier);
    Either<List<CompletionItem>, CompletionList> completionItems;
    completionItems = null;

    CompletableFuture<Either<List<CompletionItem>, CompletionList>> completionFuture = textDocumentService.completion(completionParam);
    if (completionFuture != null) {
      try {
        completionItems = completionFuture.get();
      } catch (InterruptedException | ExecutionException e) {

        System.out.println("completable future is nullll");

      }
    } else {

      System.out.println("CompletableFuture is null");
    }


    Either<List<CompletionItem>, CompletionList> completionItemsToCompare;


    assertNotNull(completionItems);
    if (completionItems != null) {
      assertEquals(completionItems, buildCompletionItem());


    }

  }

  public static Either<List<CompletionItem>, CompletionList> buildCompletionItem() {
    List<CompletionItem> completionItems = new ArrayList<>();
    CompletionItem completionItem = new CompletionItem();
    completionItem.setInsertText("//&Begin[]\n\n//&End[]\n");
    completionItem.setLabel("//&Begin[] ... //&End[]");
    completionItem.setKind(CompletionItemKind.Snippet);
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

    CompletionItem completionItem7 = new CompletionItem();
    completionItem7.setInsertText("Begin[]\n");
    completionItem7.setLabel("Begin[]");
    completionItem7.setKind(CompletionItemKind.Snippet);
    completionItem7.setDetail("Creating a new Begin Annotation");
    completionItems.add(completionItem7);

    CompletionItem completionItem8 = new CompletionItem();
    completionItem8.setInsertText("End[]");
    completionItem8.setLabel("End[]");
    completionItem8.setKind(CompletionItemKind.Snippet);
    completionItem8.setDetail("Creating a new End Annotation");
    completionItems.add(completionItem8);

    CompletionItem completionItem9 = new CompletionItem();
    completionItem9.setInsertText("Line[]");
    completionItem9.setLabel("Line[]");
    completionItem9.setKind(CompletionItemKind.Snippet);
    completionItem9.setDetail("Creating a new Line Annotation");
    completionItems.add(completionItem9);

    CompletionItem completionItemi = new CompletionItem();
    completionItemi.setInsertText("Snake_Game");
    completionItemi.setLabel("Snake_Game");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Playing_Area");
    completionItemi.setLabel("Playing_Area");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Tile");
    completionItemi.setLabel("Tile");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Food");
    completionItemi.setLabel("Food");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Spawn");
    completionItemi.setLabel("Spawn");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Blank");
    completionItemi.setLabel("Blank");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Update");
    completionItemi.setLabel("Update");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Move");
    completionItemi.setLabel("Move");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Collision");
    completionItemi.setLabel("Collision");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Position");
    completionItemi.setLabel("Position");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Tail");
    completionItemi.setLabel("Tail");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Controls");
    completionItemi.setLabel("Controls");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("GameState");
    completionItemi.setLabel("GameState");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("DataTypes");
    completionItemi.setLabel("DataTypes");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);


    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Tile::Snake");
    completionItemi.setLabel("Tile::Snake");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);

    completionItemi = new CompletionItem();
    completionItemi.setInsertText("Snake_Game::Snake");
    completionItemi.setLabel("Snake_Game::Snake");
    completionItemi.setKind(CompletionItemKind.Snippet);
    completionItemi.setDetail("a feature defined in the model");
    completionItems.add(completionItemi);


    return Either.forLeft(completionItems);
  }

  public static void testDefinition() {
    TextDocumentService textDocumentService = new HAnSTextDocumentService();

    URI documentUri = Path.of("C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\src\\test-files\\testproject\\.feature-model").toUri();
    DidOpenTextDocumentParams params = new DidOpenTextDocumentParams();

    TextDocumentIdentifier textDocumentIdentifier = new TextDocumentIdentifier(documentUri.toString());

    TextDocumentItem tDocumentItem = null;

    try {
      System.out.println(documentUri.toString());
      File test = new File(documentUri);
      BufferedReader text = new BufferedReader(new FileReader(new File(documentUri)));

      String workline = "";
      String result = "";
      int line = 0;

      workline = text.readLine();
      while (workline != null) {

        result += workline + "\n";
        System.out.println(workline);
        workline = text.readLine();


      }
      tDocumentItem = new TextDocumentItem(documentUri.toString(), "feature-model", 1, result);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");

    } catch (IOException e1) {
      System.out.println("IO Exception");
    }
    params.setTextDocument(tDocumentItem);
    textDocumentService.didOpen(params);
    Position position = new Position(0, 1);
    DefinitionParams definitionParam = new DefinitionParams();
    definitionParam.setPosition(position);
    definitionParam.setTextDocument(textDocumentIdentifier);
    Either<List<? extends Location>, List<? extends LocationLink>> completionItems = null;

    CompletableFuture<Either<List<? extends Location>, List<? extends LocationLink>>> completionFuture = textDocumentService.definition(definitionParam);
    if (completionFuture != null) {
      try {
        completionItems = completionFuture.get();
      } catch (InterruptedException | ExecutionException e) {

        System.out.println("completable future is nullll");

      }
    } else {

      System.out.println("CompletableFuture is null");
    }
    ArrayList<Location> locations = new ArrayList<>();

    int rangeStart1 = 0;
    int rangeEnd1 = 10;

    Range range1 = new Range(new Position(0, rangeStart1), new Position(0, rangeEnd1));

    Location snakeGameLoc = new Location(documentUri.toString(), range1);

    CompletionParams completionParam = new CompletionParams();
    completionParam.setPosition(position);
    completionParam.setTextDocument(textDocumentIdentifier);

    locations.add(snakeGameLoc);
    assertNotNull(completionItems);
    if (completionItems != null) {
      assertEquals(completionItems, Either.forLeft(locations));

    }
  }

  public static void testReference() {
    TextDocumentService textDocumentService = new HAnSTextDocumentService();

    URI documentUri = Path.of("C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\src\\test-files\\testproject\\.feature-model").toUri();
    URI referencedDocumentUri = Path.of("C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\src\\test-files\\testproject\\referenceTest.java").toUri();
    URI referencedDocumentUri1 = Path.of("C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\src\\test-files\\testproject\\referenceTest1.java").toUri();
    DidOpenTextDocumentParams params = new DidOpenTextDocumentParams();

    TextDocumentIdentifier textDocumentIdentifier = new TextDocumentIdentifier(documentUri.toString());
    TextDocumentIdentifier textreferencedDocumentIdentifier = new TextDocumentIdentifier(referencedDocumentUri.toString());
    TextDocumentIdentifier textreferencedDocumentIdentifier1 = new TextDocumentIdentifier(referencedDocumentUri1.toString());

    TextDocumentItem tDocumentItem = null;

    try {
      System.out.println(documentUri.toString());
      BufferedReader text = new BufferedReader(new FileReader(new File(documentUri)));

      String workline = "";
      String result = "";

      workline = text.readLine();
      while (workline != null) {

        result += workline + "\n";
        System.out.println(workline);
        workline = text.readLine();


      }

      tDocumentItem = new TextDocumentItem(documentUri.toString(), "feature-model", 1, result);

    } catch (FileNotFoundException e) {
      System.out.println("File not found");

    } catch (IOException e1) {
      System.out.println("IO Exception");
    }
    params.setTextDocument(tDocumentItem);
    textDocumentService.didOpen(params);
    Position position = new Position(0, 1);

    ReferenceParams referenceParams = new ReferenceParams();
    referenceParams.setPosition(position);
    referenceParams.setTextDocument(textDocumentIdentifier);
    List<? extends Location> completionItems = null;

    CompletableFuture <List<? extends Location>> completionFuture = textDocumentService.references(referenceParams);
    if (completionFuture != null) {
      try {
        completionItems = completionFuture.get();
      } catch (InterruptedException | ExecutionException e) {

        System.out.println("completable future is nullll");

      }
    } else {

      System.out.println("CompletableFuture is null");
    }
    ArrayList<Location> locations = new ArrayList<>();

    int TestRangeStart = 13;
    int TestRangeEnd = 21;

    int Test1RangeStart = 13;
    int Test1RangeEnd = 21;


    Range range1 = new Range(new Position(2, TestRangeStart), new Position(6, TestRangeEnd));
    Range range2 = new Range(new Position(2, Test1RangeStart), new Position(6, Test1RangeEnd));



    Location snakeGameRef1Loc = new Location(referencedDocumentUri.toString(), range1);
    Location snakeGameRef12Loc = new Location(referencedDocumentUri1.toString(), range2);



    CompletionParams completionParam = new CompletionParams();
    completionParam.setPosition(position);
    completionParam.setTextDocument(textDocumentIdentifier);

    locations.add(snakeGameRef1Loc);
    locations.add(snakeGameRef12Loc);

    assertNotNull(completionItems);
    if (completionItems != null) {
      assertEquals(completionItems, locations);

    }
  }
}


