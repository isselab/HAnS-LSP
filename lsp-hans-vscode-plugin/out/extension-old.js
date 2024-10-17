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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.extensionInstance = exports.HAnSLSPExtention = void 0;
const path_1 = __importDefault(require("path"));
const vscode = __importStar(require("vscode"));
const vscode_languageclient_1 = require("vscode-languageclient");
const node_1 = require("vscode-languageclient/node");
const outputChannel = vscode.window.createOutputChannel("LSP-HAnS");
const LS_Launcher_Main = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies";
class HAnSLSPExtention {
    LangugeClient;
    context;
    setContext(context) {
        this.context = context;
    }
    async init() {
        try {
            let serverOptions = getServerOptions();
            let clientId = "HAnS-LSP";
            let clientName = "HAnS-LSP LS Client";
            let clientOptions = {
                documentSelector: [{ scheme: "file" }],
                outputChannel: outputChannel,
                revealOutputChannelOn: vscode_languageclient_1.RevealOutputChannelOn.Never,
            };
            this.LangugeClient = new node_1.LanguageClient(clientId, clientName, serverOptions, clientOptions);
            const disposeDidChange = this.LangugeClient.onDidChangeState((stateChangeEvent) => {
                if (stateChangeEvent.newState === node_1.State.Stopped) {
                    vscode.window.showErrorMessage("Failed to initialize the extension");
                }
                else if (stateChangeEvent.newState === node_1.State.Running) {
                    vscode.window.showInformationMessage("Extension initialized successfully!");
                }
            });
            let disposable = this.LangugeClient.start();
        }
        catch (exception) {
            return Promise.reject("Extension error!");
        }
    }
}
exports.HAnSLSPExtention = HAnSLSPExtention;
function getServerOptions() {
    const PROJECT_HOME = "C:\Users\Taymo\Documents\GitHub\HAnS-LSP\lsp-hans-vscode-plugin";
    const LS_LIB = "src\HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies";
    const LS_HOME = path_1.default.join(PROJECT_HOME, LS_LIB);
    const JAVA_HOME = process.env.JAVA_HOME;
    let executable = path_1.default.join(String(JAVA_HOME), "bin", "java");
    let args = ["-cp", LS_HOME];
    let serverOptions = {
        command: executable,
        args: [...args, LS_Launcher_Main],
        options: {},
    };
    return serverOptions;
}
exports.extensionInstance = new HAnSLSPExtention();
//# sourceMappingURL=extension-old.js.map