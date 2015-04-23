
public class ClientChat {
	
	
	private final int MAX = 7;
	private Client[] clients = new Client [MAX];
	private int numberOfClients = 0;
	
	public static void main (String[] args) {
		ClientChat display = new ClientChat();
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
	
	public void removeClient() {
		clients[numberOfClients] = null;
		numberOfClients--;
	}
	
	public int returnNumberOfClients () {
		return numberOfClients;
	}
}