package se.isselab.HAnS.codeAnnotation;


import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HAnSWorkSpaceService implements WorkspaceService {

    private static final Logger logger = LoggerFactory.getLogger(HAnSLanguageServer.class);
    private HAnSLanguageServer langugageServer;
    private FeatureModelTree tree;

    public HAnSWorkSpaceService(HAnSLanguageServer x, FeatureModelTree y) {
        this.langugageServer = x;
        this.tree = y;
    }

    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        logger.info("Configuration has been changed : {}", params);

    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {

    }


    public void renameFiles(RenameFilesParams params) {

    }

//    public CompletableFuture<List<? extends SymbolInformation>> symbol(WorkspaceSymbolParams workspaceSymbolParams) {
//        return null;
//    }
}

