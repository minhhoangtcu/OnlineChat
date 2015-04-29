/*
 * Name: Client
 * Authors: Minh Hoang, Kiet Nguyen, Le Bui, Quang Nguyen
 * 
 * Program Overview:
 * 		This is the client with a fixed port number to connect to one and only one designed server.
 * 		When getting in the client, you will need to provide a ID (a name for your client).
 * 		
 */

package Client;
import Client.chat.Client;
import Client.chat.ClientChatControl;

public class ClientChat {
	
	private final int MAX = 6;
	private Client[] clients = new Client [MAX];
	private int numberOfClients = 0;
	
	public static void main (String[] args) {
		new ClientChat();
	}
	
	public ClientChat () {
		ClientChatControl control = new ClientChatControl(this);
		addClient();
		control.addTab("New Tab", clients[0]);
		addClient();
		control.addTab("", clients[1]);
	}
	
	public Client[] returnClients() {
		return clients;
	}
	
	public Client returnClient (int index) {
		return clients[index];
	}
	
	public void addClient() {
		Client client = new Client();
		clients[numberOfClients] = client;
		numberOfClients++;
	}
	
	public void removeClient(int index) {
		for (int i = index; i < numberOfClients - 1; i++) {
			clients[i] = clients[i + 1];
		}
		numberOfClients--;
	}
	
	public int returnNumberOfClients () {
		return numberOfClients;
	}
}
