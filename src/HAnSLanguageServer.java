package com.hans.lsp;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;


public class HAnSLanguageServer implements LanguageServer, LanguageClientAware {

    private HAnSTextDocumentService tdservice;
    private HAnSWorkSpaceService wsservice;
    private LanguageClient client;
    private static final Logger logger = LoggerFactory.getLogger(HAnSLanguageServer.class);

    public HAnSLanguageServer() {
        this.tdservice = new HAnSTextDocumentService;
        this.wsservice = new HAnSWorkSpaceService;
    }

    public void initialized(initializedParams params) {
        logger.info("language server with params has been initialized: {}", params);

    }


    public void exit() {
        System.exit(0);
    }

    /*public NotebookDocumentService getNotebookDocumentService() {

    }*/


    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {

        logger.info("Initializing language server with params: {}", params);

        final InitializeResult result = new InitializeResult(new ServerCapabilities());
//        ServerCapabilities capabilities = new ServerCapabilities();

        // Set capabilities here, for example, text document synchronization
        result.getCapabilities().setTextDocumentSync(TextDocumentSyncKind.Full);

        CompletionOptions completionOptions = new CompletionOptions();
        result.getCapabilities().setCompletionProvider(completionOptions);
//        result.setCapabilities(capabilities);
        return CompletableFuture.supplyAsync(() -> result);

    }

    @Override
    public CompletableFuture<Object> shutdown() {
        logger.info("shutting down language server");
//        return CompletableFuture.completedFuture(null);
        return null;
    }

    //@Override
    //public NotebookDocumentService getNotebookDocumentService() {
//        return this;
//}

    @Override
    public TextDocumentService getTextDocumentService() {
        return this.tdservice;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        return this.wsservice;
    }

    public LanguageClient getClient() {
        return this.client;
    }

    @Override
    /*public void cancelProgress(WorkDoneProgressCancelParams params) {
    }*/

    public void connect(LanguageClient client) {
        logger.info("connecting language server with client: {}", client);
        this.client = client;
        client
    }

//    public boolean isDynamicCompletionRegistration() {
//        return false;
//    }
}