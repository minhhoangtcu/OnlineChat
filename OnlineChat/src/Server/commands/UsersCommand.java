package Server.commands;

import Server.ServerChat;
import Server.chat.ServerControl;
import Server.chat.ServiceChat;

public class UsersCommand {
	
	//display all the connected clients
	public void executeCommand(ServerControl control, ServerChat server) {
		for (ServiceChat service: server.services) {
			control.appendText(service.getName() + " (" + service.getID() + ")");
		}
	}
}
