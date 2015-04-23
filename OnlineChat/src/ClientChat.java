
public class ClientChat {
	
	private Client[] clients = new Client [7];
	
	public static void main (String[] args) {
		ClientChat display = new ClientChat();
	}
	
	public ClientChat () {
		ClientChatControl control = new ClientChatControl(this);
		clients[0] = new Client();
		control.addTab("New Tab", clients[0]);
		clients[1] = new Client();
		control.addTab("", clients[1]);
	}

}
