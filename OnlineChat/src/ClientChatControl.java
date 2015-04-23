
public class ClientChatControl {
	ClientChatView view;
	ClientChat clientChat;
	
	public ClientChatControl(ClientChat clientChat) {
		this.clientChat = clientChat;
		view = new ClientChatView();
	}
	
	public void addTab(String title, Client client) {
		view.getJTabbedPane().addTab(title, client.control.view);
	}
	
}
