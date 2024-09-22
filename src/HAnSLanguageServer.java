package se.isselab.HAnS.codeAnnotation;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.eclipse.lsp4j.services.LanguageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.isselab.HAnS.codeAnnotation.HAnSWorkSpaceService;
import se.isselab.HAnS.codeAnnotation.FeatureModelTree;

import java.util.concurrent.CompletableFuture;


public class HAnSLanguageServer implements LanguageServer, LanguageClientAware {

    private HAnSTextDocumentService tdservice;
    private HAnSWorkSpaceService wsservice;
    private LanguageClient client;
    private static final Logger logger = LoggerFactory.getLogger(HAnSLanguageServer.class);
    private FeatureModelTree tree;

    public HAnSLanguageServer() {
        this.tree = new FeatureModelTree();
        this.tdservice = new HAnSTextDocumentService(this, tree);
        this.wsservice = new HAnSWorkSpaceService(this, tree);
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
        return CompletableFuture.completedFuture(null);
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
    }

//    public boolean isDynamicCompletionRegistration() {
//        return false;
//    }
}