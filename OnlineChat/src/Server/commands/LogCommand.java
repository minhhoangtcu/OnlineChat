package Server.commands;
import data.SpecialCommands;
import Server.chat.ServerControl;

public class LogCommand {
	
	public void executeCommand(ServerControl control) {
		control.appendText(SpecialCommands.getLog());
	}

}
