package Server.commands;
import Server.chat.ServerControl;
import data.SpecialCommands;

public class HelpCommand {
	
	public void executeCommand(ServerControl control) {
		String text = "\n"
			+ SpecialCommands.kick.toUpperCase() + "\t kick a client out of the system. \n"
			+ SpecialCommands.log.toUpperCase() + "\t show the chat log from the begining of the server \n"
			+ SpecialCommands.users.toUpperCase() + "\t show all clients connected to this server";
		control.appendText(text);
	}
}
