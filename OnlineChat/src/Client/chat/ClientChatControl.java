package Client.chat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Client.ClientChat;

public class ClientChatControl implements MouseListener, ActionListener {
	ClientChatView view;
	ClientChat clientChat;
	
	public ClientChatControl(ClientChat clientChat) {
		this.clientChat = clientChat;
		view = new ClientChatView();
		setUpListener();
	}
	
	public void addTab(String title, Client client) {
		view.tabbedPane.addTab(title, client.control.view);
		setUpListenerInsideTabbedPane(client);
	}
	
	private void setUpListener() {
		view.tabbedPane.addMouseListener(this);
	}
	
	private void setUpListenerInsideTabbedPane(Client client) {
		client.control.view.connectButton.addActionListener(this);
		client.control.view.tfID.addActionListener(this);
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
			// set title for the new tab
			int index = view.tabbedPane.getSelectedIndex();	
			setTitleForTab(index, "New Tab");
			createReadyTab();
		} else if (clientChat.returnNumberOfClients() == clientChat.returnClients().length) {
			//set title for the last tab
			int index = view.tabbedPane.getSelectedIndex();
			setTitleForTab(index, "New Tab");
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		int index = view.tabbedPane.getSelectedIndex();
		Client client = clientChat.returnClient(index);
		if (event.equals(client.control.view.connectButton) && !client.control.view.sendButton.isEnabled() || event == client.control.view.tfID) {
			setTitleForTab(index, client.control.view.tfID.getText());
		} else if (event.equals(client.control.view.connectButton) && client.control.view.sendButton.isEnabled()) {
			removeTab(client, index);
		}
	}
	
	private void setTitleForTab(int index, String s) {
		view.tabbedPane.setTitleAt(index, s);
	}
	
	private void createReadyTab () {
		clientChat.addClient();
		addTab("", clientChat.returnClient(clientChat.returnNumberOfClients() - 1));
	}
	
	private void removeTab(Client client, int index) {
		view.tabbedPane.remove(client.control.view);
		clientChat.removeClient(index);
		if (view.tabbedPane.getTabCount() == 1) {
			createReadyTab();
			setTitleForTab(0, "New Tab");
		}
	}
}