package se.isselab.HAnS.codeAnnotation;

import org.eclipse.lsp4j.services.*;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import java.io.*;

public class HAnSLspLauncher {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        OutputStream out = System.out;
        HAnSLanguageServer server = new HAnSLanguageServer();
        Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(server, in, out);
        LanguageClient client = launcher.getRemoteProxy();
        server.connect(client);
        launcher.startListening();
    }
}
