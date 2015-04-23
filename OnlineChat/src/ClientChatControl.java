import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;

public class ClientChatControl implements MouseListener {
	ClientChatView view;
	ClientChat clientChat;
	
	public ClientChatControl(ClientChat clientChat) {
		this.clientChat = clientChat;
		view = new ClientChatView();
	}
	
	public void addTab(String title, Client client) {
		view.tabbedPane.addTab(title, client.control.view);
		setUpListener();
	}
	
	private void setUpListener() {
		view.tabbedPane.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if ((view.tabbedPane.getSelectedIndex() == view.tabbedPane.getTabCount() - 1)
				&& (clientChat.returnNumberOfClients() != clientChat.returnClients().length)) {
			int index = view.tabbedPane.getSelectedIndex();
			view.tabbedPane.setTitleAt(index, "New Tab");
			clientChat.addClient();
			System.out.println(clientChat.returnNumberOfClients());
			addTab("", clientChat.returnLastClient());
		} else if (clientChat.returnNumberOfClients() == clientChat.returnClients().length) {
			int index = view.tabbedPane.getSelectedIndex();
			view.tabbedPane.setTitleAt(index, "New Tab");
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
