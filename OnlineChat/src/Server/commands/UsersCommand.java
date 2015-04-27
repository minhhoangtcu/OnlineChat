package Server.commands;

import Server.ServerChat;
import Server.chat.ServerControl;
import Server.chat.ServiceChat;

public class UsersCommand {
	
	// TODO fix this also. Cannot get name from service
	public void executeCommand(ServerControl control, ServerChat server) {
		for (ServiceChat service: server.services) {
			control.appendText(service.getName() + " (" + service.getID() + ")");
		}
	}
}
