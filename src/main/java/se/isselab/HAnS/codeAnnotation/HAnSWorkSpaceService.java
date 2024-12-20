package se.isselab.HAnS.codeAnnotation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HAnSWorkSpaceService implements WorkspaceService {

    private static FileLogger logger;
    private HAnSLanguageServer langugageServer;
    private FeatureModelTree tree;
    private HAnSTextDocumentService textService;

    public HAnSWorkSpaceService(HAnSLanguageServer x, FeatureModelTree y) {
        //System.setProperty("log4j.configurationFile", "log4jconfig.xml");
        logger = new FileLogger(HAnSWorkSpaceService.class);
        this.langugageServer = x;
        this.tree = y;
        this.textService = (HAnSTextDocumentService) langugageServer.getTextDocumentService();
    }

    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        logger.info("Configuration has been changed :"+ params.toString() );

    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
        logger.info("files changed: " + params.getChanges().toString());
        //textService.setWorkspaceFolders()
    }
    public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params){
        logger.info("files opened: " + params.getEvent().getAdded());
        textService.setWorkspaceFolders(params.getEvent().getAdded());

    }


    public void renameFiles(RenameFilesParams params) {

    }

//    public CompletableFuture<List<? extends SymbolInformation>> symbol(WorkspaceSymbolParams workspaceSymbolParams) {
//        return null;
//    }
}

