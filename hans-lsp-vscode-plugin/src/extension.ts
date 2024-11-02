import * as path from "path";
import { workspace, ExtensionContext } from "vscode";
import * as vscode from "vscode";
import { Uri } from "vscode";

import {
  LanguageClient,
  LanguageClientOptions,
  ServerOptions,
  TransportKind,
} from "vscode-languageclient/node";

import { Disposable, DocumentSelector, RevealOutputChannelOn } from "vscode-languageclient";
import { Server } from "http";

let client: LanguageClient;
const LS_Launcher_Main = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies";
const outputChannel = vscode.window.createOutputChannel("LSP-HAnS");

export function activate(context: ExtensionContext) {
  // The server is implemented in node
  let serverModule = context.asAbsolutePath(
    path.join("server", "out", "server.js")
  );
  // The debug options for the server
  // --inspect=6009: runs the server in Node's Inspector mode so VS Code can attach to the server for debugging
  let debugOptions = { execArgv: ["--nolazy", "--inspect=6009"] };

  // If the extension is launched in debug mode then the debug server options are used
  // Otherwise the run options are used
  let serverOptions: ServerOptions = getServerOptions();
  // Options to control the language client
  const workspaceFolder = workspace.workspaceFolders ? workspace.workspaceFolders[0].uri.fsPath : undefined;
  // Options to control the language client
  var clientOptions;
  if(workspaceFolder != undefined){
   let clientOptions: LanguageClientOptions = {
    // Register the server for plain text documents,
      workspaceFolder: {uri: Uri.file(workspaceFolder), name:"" ,index:0 },
     documentSelector: [{ scheme: "file", language: "plaintext" },{scheme: "file", language: "javascript"},{scheme: "file", language: "typescript"},{scheme: "file", language: "Featuremodel"},{scheme: "file", language: "java"}],
      synchronize: {
       // Notify the server about file changes to '.clientrc files contained in the workspace
       fileEvents: workspace.createFileSystemWatcher("**/.clientrc"), 
      
     },
    };
      // Create the language client and start the client.
    client = new LanguageClient(
    "HAnSLSPid",
    "HAnSLSP",
    serverOptions,
    clientOptions
  
    );
    console.log("Server options: ", serverOptions);
    console.log("client:",client);


    // Start the client. This will also launch the server
    client.start();
  }

  
  else{
    let clientOptions : LanguageClientOptions = {
      // Register the server for plain text documents,
      documentSelector: [{ scheme: "file", language: "plaintext" },{scheme: "file", language: "javascript"},{scheme: "file", language: "typescript"},{scheme: "file", language: "Featuremodel"},{scheme: "file", language: "java"}],
      synchronize: {
        // Notify the server about file changes to '.clientrc files contained in the workspace
        fileEvents: workspace.createFileSystemWatcher("**/.clientrc"), 
        
      },
    };

    // Create the language client and start the client.
    client = new LanguageClient(
      "HAnSLSPid",
     "HAnSLSP",
     serverOptions,
      clientOptions
  
    );
    console.log("Server options: ", serverOptions);
    console.log("client:",client);

    // Start the client. This will also launch the server
    client.start();
  }

  
  
  /*
  let sel: DocumentSelector = [{ scheme: "file", language: "plaintext" },{scheme: "file", language: "javascript"},{scheme: "file", language: "typescript"},{scheme: "file", language: "Featuremodel"},{scheme: "file", language: "java"}]
  let disposable = vscode.languages.registerHoverProvider(sel,{
    provideHover(document,position,token){
      return{
        contents: [position.line.toString(), position.character.toString()]
      };
    }
   });
  context.subscriptions.push(disposable);
  */

  
}

export function deactivate(): Thenable<void> | undefined {
  if (!client) {
    return undefined;
  }
  return client.stop();
}
function getServerOptions() {
  const PROJECT_HOME =    "C:\\Users\\Tim\\Documents\\GitHub\\HAnS-LSP\\target";
  const LS_LIB = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies.jar"; // Added ".jar"
  const LS_HOME = path.join(PROJECT_HOME, LS_LIB);

  const JAVA_HOME = process.env.JAVA_HOME;
  //console.log("JAVA_HOME:", JAVA_HOME);
  //const JAVA_HOME = "C:\\Program Files\\Java\\jdk-23"; // Replace with the actual path to your Java JDK

  /* if (!JAVA_HOME) {
	  vscode.window.showErrorMessage("JAVA_HOME environment variable is not set. Please set JAVA_HOME to the path of your JDK.");
	  throw new Error("JAVA_HOME is not defined.");
  }*/

  let executable: string = path.join(String(JAVA_HOME), "bin", "java");
  let args: string[] = ["-jar", LS_HOME];

  let serverOptions: ServerOptions = {
    command: executable,
    args: [...args, LS_Launcher_Main],
    options: {
       
    },
  };
  //TODO:
  return serverOptions;
}
