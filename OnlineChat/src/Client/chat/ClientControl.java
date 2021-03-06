package Client.chat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

public class ClientControl implements MouseListener{
	
	ClientView view;
	Client client;
	private boolean isConnect;
	
	/*/
	 *Handle action listener, mouse listener of client class.
	 */
	public ClientControl(Client client) {
		isConnect = false;
		this.client = client;
		view = new ClientView();
		setUpListeners();
	}
	
	private void setUpListeners() {
		view.connectButton.addMouseListener(this);
		view.sendButton.addMouseListener(this);
		view.result.addMouseListener(this);
		view.userInput.addMouseListener(this);
		view.tfSever.addMouseListener(this);
		view.tfID.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		Object event = e.getSource();
		if (event.equals(view.connectButton)) changeConnectButtonWhenEntered();
		else if (event.equals(view.sendButton)) changeSendButtonWhenEntered();
		else if (event.equals(view.result)) changeResultBorderWhenEntered();
		else if (event.equals(view.userInput)) changeUserInputWhenEntered();
		else if (event.equals(view.tfSever)) changeServerTFWhenEntered();
		else if (event.equals(view.tfID)) changeIDTFWhenEntered();
	}

	public void mouseExited(MouseEvent e) {
		Object event = e.getSource();
		if (event.equals(view.connectButton)) changeConnectButtonWhenExited();
		else if (event.equals(view.sendButton)) changeSendButtonWhenExited();
		else if (event.equals(view.result)) changeResultBorderWhenExited();
		else if (event.equals(view.userInput)) changeUserInputWhenExited();
		else if (event.equals(view.tfSever)) changeServerTFWhenExited();
		else if (event.equals(view.tfID)) changeIDTFWhenExited();
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void setConnected(boolean state) {
		isConnect = state;
	}
	
	public boolean isConnect() {
		return isConnect;
	}
	
	private void changeConnectButtonWhenEntered() {
		view.connectButton.setBackground(view.LIGHTPURPLE);
	}
	
	private void changeConnectButtonWhenExited() {
		view.connectButton.setBackground(view.PURPLE);
	}
	
	private void changeSendButtonWhenEntered() {
		view.sendButton.setBackground(view.LIGHTPURPLE);
	}
	
	private void changeSendButtonWhenExited() {
		view.sendButton.setBackground(view.PURPLE);
	}
	
	private void changeResultBorderWhenEntered() {
		view.result.setBorder(BorderFactory.createLineBorder(view.LIGHTPURPLE,1));
	}
	
	private void changeResultBorderWhenExited() {
		view.result.setBorder(BorderFactory.createLineBorder(view.PURPLE, 1));
	}
	
	private void changeUserInputWhenEntered() {
		view.userInput.setBorder(BorderFactory.createLineBorder(view.LIGHTPURPLE,1));
	}
	
	private void changeUserInputWhenExited() {
		view.userInput.setBorder(BorderFactory.createLineBorder(view.PURPLE, 1));
	}
	
	private void changeServerTFWhenEntered() {
		view.tfSever.setBorder(BorderFactory.createLineBorder(view.LIGHTPURPLE,1));
	}
	
	private void changeServerTFWhenExited() {
		view.tfSever.setBorder(BorderFactory.createLineBorder(view.PURPLE, 1));
	}
	
	private void changeIDTFWhenEntered() {
		view.tfID.setBorder(BorderFactory.createLineBorder(view.LIGHTPURPLE,1));
	}
	
	private void changeIDTFWhenExited() {
		view.tfID.setBorder(BorderFactory.createLineBorder(view.PURPLE, 1));
	}

}

