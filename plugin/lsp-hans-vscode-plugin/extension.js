// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
const vscode = require('vscode');

import { extensionInstance } from './src/extension';

// This method is called when your extension is activated
// Your extension is activated the very first time the command is executed

/**
 * @param {vscode.ExtensionContext} context
 */
function activate(context) {
	console.log('Congratulations, your extension "lsp-hans-vscode-plugin" is now active!');

	const disposable = vscode.commands.registerCommand('lsp-hans-vscode-plugin.helloWorld', function () {
		// The code you place here will be executed every time your command is executed

		// Display a message box to the user
		vscode.window.showInformationMessage('Hello World from lsp_HAnS_VSCode_plugin!');
	});
	
	context.subscriptions.push(disposable);

	extensionInstance.setContext(context);
	

	extensionInstance.init().catch((error)=>{
		vscode.window.showInformationMessage('Failed to activate extension!');
	})
	 
	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	

	// The command has been defined in the package.json file
	// Now provide the implementation of the command with  registerCommand
	// The commandId parameter must match the command field in package.json
	
	
}

// This method is called when your extension is deactivated
function deactivate() {}

module.exports = {
	activate,
	deactivate
}
