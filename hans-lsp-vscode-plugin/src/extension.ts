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
  
  // the server's executable path
  const workspaceFolderPath = workspace.workspaceFolders ? workspace.workspaceFolders[0].uri.fsPath : undefined;

  console.log("Workspace folder path being sent to server:", workspaceFolderPath);

  let serverOptions: ServerOptions = getServerOptions();

  let clientOptions: LanguageClientOptions = {
    //gets the server's executable path
    initializationOptions: {
      workspaceFolder: workspaceFolderPath,
    },
    documentSelector: [
      { scheme: "file", language: "plaintext" },
      { scheme: "file", language: "javascript" },
      { scheme: "file", language: "typescript" },
      { scheme: "file", language: "Featuremodel" },
      { scheme: "file", language: "java" },
    ],
    synchronize: {
      fileEvents: workspace.createFileSystemWatcher("**/.clientrc"),
    },
  };

  client = new LanguageClient(
    "HAnSLSPid",
    "HAnSLSP",
    serverOptions,
    clientOptions
  );

  console.log("Server options: ", serverOptions);
  console.log("client:", client);

  client.start();
}

export function deactivate(): Thenable<void> | undefined {
  if (!client) {
    return undefined;
  }
  return client.stop();
}

function getServerOptions() {
  const PROJECT_HOME = "C:\\Users\\Taymo\\Documents\\GitHub\\HAnS-LSP\\target";
  const LS_LIB = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies.jar";
  const LS_HOME = path.join(PROJECT_HOME, LS_LIB);

  const JAVA_HOME = process.env.JAVA_HOME;

  let executable: string = path.join(String(JAVA_HOME), "bin", "java");
  let args: string[] = ["-jar", LS_HOME];

  let serverOptions: ServerOptions = {
    command: executable,
    args: [...args, LS_Launcher_Main],
    options: {},
  };
  return serverOptions;
}
