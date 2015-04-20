import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.*;

public class ClientView extends JPanel implements ActionListener {

	JPanel contentPane;
	JTextArea result;
	JTextField userInput;
	Client client;
	JButton sendButton;
	JButton connectButton;
	JScrollPane scroller = new JScrollPane();
	JLabel errors = new JLabel();

	/**
	 * Create the frame.
	 */
	public ClientView(Client client) {
		this.client = client;
		contentPane = new JPanel();
		add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		result = new JTextArea(100,200);
		result.setBounds(10, 11, 414, 221);
		result.setColumns(10);
		scroller.getViewport().add(result);
		contentPane.add(scroller);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		sendButton.setEnabled(false);
		sendButton.setBounds(10, 238, 89, 23);
		contentPane.add(sendButton);
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(this);
		connectButton.setBounds(104, 238, 89, 23);
		contentPane.add(connectButton);
		
		userInput = new JTextField();
		userInput.addActionListener(this);
		userInput.setBounds(203, 239, 221, 22);
		userInput.setColumns(10);
		contentPane.add(userInput);
		
		contentPane.add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
//		if (evt.getActionCommand().equals("Connect") || 
//				connectButton.getText().equals("Connect") && evt.getSource() == userInput) {
//			client.connect();
//		} else if (evt.getActionCommand().equals("Disconnect")){
//			client.disconnect();
//		} else if (evt.getActionCommand().equals("Send") || 
//						sendButton.isEnabled() && evt.getSource() == userInput) {
//			client.send();
//		}
	}
}
