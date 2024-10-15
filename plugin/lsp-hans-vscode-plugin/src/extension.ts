import { promises } from "dns";
import path from "path";
import * as vscode from "vscode";
import{
    LanguageClientOptions,
    RevealOutputChannelOn
}from "vscode-languageclient";
import{
    LanguageClient,
    ServerOptions,
    State
} from "vscode-languageclient/node"

const outputChannel = vscode.window.createOutputChannel("LSP-HAnS");
const LS_Launcher_Main = "HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies";


export class HAnSLSPExtention {
    
    private LangugeClient?: LanguageClient;
    private context?: vscode.ExtensionContext;
    
    setContext(context: vscode.ExtensionContext){
        this.context = context;
    }

    async init():Promise<void>{
        try {
            
            let serverOptions: ServerOptions = getServerOptions();
      
            
            let clientId = "HAnS-LSP";
            let clientName = "HAnS-LSP LS Client";
            let clientOptions: LanguageClientOptions = {
              documentSelector: [{ scheme: "file" }],
              outputChannel: outputChannel,
              revealOutputChannelOn: RevealOutputChannelOn.Never,
            };
            this.LangugeClient = new LanguageClient(
              clientId,
              clientName,
              serverOptions,
              clientOptions
            );
      
            const disposeDidChange = this.LangugeClient.onDidChangeState(
              (stateChangeEvent) => {
                if (stateChangeEvent.newState === State.Stopped) {
                  vscode.window.showErrorMessage(
                    "Failed to initialize the extension"
                  );
                } else if (stateChangeEvent.newState === State.Running) {
                  vscode.window.showInformationMessage(
                    "Extension initialized successfully!"
                  );
                }
              }
            );
      
            let disposable = this.LangugeClient.start();
            
          } catch (exception) {
            return Promise.reject("Extension error!");
          }
              
        }
    }
    function getServerOptions() {
        const PROJECT_HOME = "";
        const LS_LIB = "./HAnS-LSP-1.0-SNAPSHOT-jar-with-dependencies";
        const LS_HOME = path.join(PROJECT_HOME, LS_LIB);
        const JAVA_HOME = process.env.JAVA_HOME;
      
        let executable: string = path.join(String(JAVA_HOME), "bin", "java");
        let args: string[] = ["-cp", LS_HOME];
      
        let serverOptions: ServerOptions = {
          command: executable,
          args: [...args, LS_Launcher_Main],
          options: {},
        };
        return serverOptions;
      }
      
      export const extensionInstance = new HAnSLSPExtention();