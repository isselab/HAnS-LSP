package com.hans.lsp;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.SymbolInformation;
import org.eclipse.lsp4j.WorkspaceSymbolParams;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HAnSWorkSpaceService implements WorkspaceService {

    private static final Logger logger = LoggerFactory.getLogger(HAnSLanguageServer.class);
    private HAnSLanguageServer langugageServer;

    public HAnSWorkSpaceService(HAnSLanguageServer x) {
        this.langugageServer = x;
    }

    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        logger.info("Configuration has been changed : {}", params);

    }

    public void didChangeWatched(DidChangeWatchedParams params) {

    }

    public void renameFiles(RenameFilesParams params) {

    }

    public CompletableFuture<List<? extends SymbolInformation>> symbol(WorkspaceSymbolParams workspaceSymbolParams) {
        return null;
    }
}

