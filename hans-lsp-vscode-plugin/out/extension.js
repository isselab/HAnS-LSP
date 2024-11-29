"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.activate = activate;
exports.deactivate = deactivate;
const path = __importStar(require("path"));
const vscode_1 = require("vscode");
const vscode = __importStar(require("vscode"));
const vscode_2 = require("vscode");
const node_1 = require("vscode-languageclient/node");
let client;
const LS_Launcher_Main = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies";
const outputChannel = vscode.window.createOutputChannel("LSP-HAnS");
var LSPPath;
function activate(context) {
    // The server is implemented in node
    let serverModule = context.asAbsolutePath(path.join("server", "out", "server.js"));
    // The debug options for the server
    // --inspect=6009: runs the server in Node's Inspector mode so VS Code can attach to the server for debugging
    let debugOptions = { execArgv: ["--nolazy", "--inspect=6009"] };
    LSPPath = context.asAbsolutePath("./HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies.jar");
    const workspaceFolderPath = vscode_1.workspace.workspaceFolders ? vscode_1.workspace.workspaceFolders[0].uri.fsPath : undefined;
    //const workspaceFolderPath = workspace.workspaceFolders ? workspace.workspaceFolders[0].uri.fsPath :URI};
    console.log("Workspace folder path being sent to server:", workspaceFolderPath);
    // If the extension is launched in debug mode then the debug server options are used
    // Otherwise the run options are used
    let serverOptions = getServerOptions();
    // Options to control the language client
    //const workspaceFolder = workspace.workspaceFolders ? workspace.workspaceFolders[0].uri.fsPath : undefined;
    // Options to control the language client
    var clientOptions;
    let _sortedWorkspaceFolders;
    function sortedWorkspaceFolders() {
        if (_sortedWorkspaceFolders === void 0) {
            _sortedWorkspaceFolders = vscode_1.workspace.workspaceFolders ? vscode_1.workspace.workspaceFolders.map(folder => {
                let result = folder.uri.toString();
                if (result.charAt(result.length - 1) !== '/') {
                    result = result + '/';
                }
                return result;
            }).sort((a, b) => {
                return a.length - b.length;
            }) : [];
        }
        return _sortedWorkspaceFolders;
    }
    function getOuterMostWorkspaceFolder(folder) {
        const sorted = sortedWorkspaceFolders();
        for (const element of sorted) {
            let uri = folder.uri.toString();
            if (uri.charAt(uri.length - 1) !== '/') {
                uri = uri + '/';
            }
            if (uri.startsWith(element)) {
                return vscode_1.workspace.getWorkspaceFolder(vscode_2.Uri.parse(element));
            }
        }
        return folder;
    }
    let workspaceFolder = undefined;
    if (vscode_1.workspace.workspaceFolders != undefined) {
        const workspaceFolderUri = vscode_1.workspace.workspaceFolders[0].uri;
        workspaceFolder = vscode_1.workspace.getWorkspaceFolder(workspaceFolderUri);
        if (!workspaceFolder) {
            return;
        }
        workspaceFolder = getOuterMostWorkspaceFolder(workspaceFolder);
    }
    /*
    initializationOptions: {
          workspaceFolder: workspaceFolderPath,
        },
    */
    if (workspaceFolder != undefined) {
        let clientOptions = {
            // Register the server for plain text documents,
            workspaceFolder: workspaceFolder,
            documentSelector: [{ scheme: "file", language: "plaintext" }, { scheme: "file", language: "javascript" }, { scheme: "file", language: "typescript" }, { scheme: "file", language: "Featuremodel" }, { scheme: "file", language: "java" }, { scheme: "file", language: "FeatureToFile" }, { scheme: "file", language: "FeatureToFolder" }],
            synchronize: {
                // Notify the server about file changes to '.clientrc files contained in the workspace
                fileEvents: vscode_1.workspace.createFileSystemWatcher("**/.clientrc"),
            },
        };
        // Create the language client and start the client.
        client = new node_1.LanguageClient("HAnSLSPid", "HAnSLSP", serverOptions, clientOptions);
        console.log("Server options: ", serverOptions);
        console.log("client:", client);
        // Start the client. This will also launch the server
        client.start();
    }
    else {
        let clientOptions = {
            // Register the server for plain text documents,
            documentSelector: [{ scheme: "file", language: "plaintext" }, { scheme: "file", language: "javascript" }, { scheme: "file", language: "typescript" }, { scheme: "file", language: "Featuremodel" }, { scheme: "file", language: "java" }],
            synchronize: {
                // Notify the server about file changes to '.clientrc files contained in the workspace
                fileEvents: vscode_1.workspace.createFileSystemWatcher("**/.clientrc"),
            },
        };
        // Create the language client and start the client.
        client = new node_1.LanguageClient("HAnSLSPid", "HAnSLSP", serverOptions, clientOptions);
        console.log("Server options: ", serverOptions);
        console.log("client:", client);
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
function deactivate() {
    if (!client) {
        return undefined;
    }
    return client.stop();
}
function getServerOptions() {
    const PROJECT_HOME = "C:\\Users\\Tim\\Documents\\GitHub\\HAnS-LSP\\target";
    const LS_LIB = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies.jar"; // Added ".jar"
    const LS_HOME = path.join(PROJECT_HOME, LS_LIB);
    const JAVA_HOME = process.env.JAVA_HOME;
    //console.log("JAVA_HOME:", JAVA_HOME);
    //const JAVA_HOME = "C:\\Program Files\\Java\\jdk-23"; // Replace with the actual path to your Java JDK
    /* if (!JAVA_HOME) {
        vscode.window.showErrorMessage("JAVA_HOME environment variable is not set. Please set JAVA_HOME to the path of your JDK.");
        throw new Error("JAVA_HOME is not defined.");
    }*/
    let executable = path.join(String(JAVA_HOME), "bin", "java");
    //let args: string[] = ["-jar", LS_HOME];
    let args = ["-jar", LSPPath];
    let serverOptions = {
        command: executable,
        args: [...args, LS_Launcher_Main],
        options: {},
    };
    //TODO:
    return serverOptions;
}
//# sourceMappingURL=extension.js.map