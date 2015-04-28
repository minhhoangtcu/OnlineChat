package Server.commands;
import data.SpecialCommands;
import Server.ServerChat;
import Server.chat.ServerControl;
import Server.chat.ServiceChat;

public class KickCommand {

	// TODO fix this command. This does not work well. Cannot get the name of the client 
	public void executeCommand(ServerControl control, ServerChat server, int clientID) {
		ServiceChat service = server.getServiceWithThisID(clientID);
		//server.printAll(clientName + "GOT KICKED OUT");
		service.print(SpecialCommands.KEYWORD + SpecialCommands.kick);
		server.removeFromServices(service);
	}
}
