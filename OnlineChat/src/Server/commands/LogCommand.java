package Server.commands;
import data.SpecialCommands;
import Server.chat.ServerControl;

public class LogCommand {
	
	//display the chat history of all clients
	public void executeCommand(ServerControl control) {
		control.appendText(SpecialCommands.getLog());
	}

}
