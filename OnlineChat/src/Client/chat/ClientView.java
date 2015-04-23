package Client.chat;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.*;
import javax.swing.border.LineBorder;

public class ClientView extends JPanel{
	JTextArea result;
	JTextField userInput;
	JTextField tfSever;
	JTextField tfID;
	JButton connectButton;
	JButton sendButton;
	JLabel errors;
	private JLabel lbSever;
	private JLabel lblID;
	private JScrollPane scroller;
	final Color PURPLE = new Color (122, 0, 163),
			LIGHTPURPLE = new Color (181, 113, 204);
	
	
	public static void main(String[] args) {
		new ClientView();
	}
	
	public ClientView() {
		buildView();
		setVisible(true);
	}
	
	public void buildView() {
		setBounds(100, 100, 490, 375);
		setUpContentPane();
		setUpResult();
		setUpUserInput();
		setUpSendButton();
		setUpConnectButton();
		setUpServerLabel();
		setUpServerTF();
		setUpIDLabel();
		setUpIDTF();
		setUpError();
	}
	
	private void setUpContentPane() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setOpaque(true);
		setBackground(new Color(250, 250, 250));
		setLayout(null);
		setVisible(true);
	}

	private void setUpResult() {
		result = new JTextArea();
		result.setWrapStyleWord(true);
		result.setLineWrap(true);
		result.setEditable(false);
		scroller = new JScrollPane();
		result.setBorder(new LineBorder(new Color(122, 0, 163), 1));
		scroller.setViewportView(result);
		scroller.setBounds(10, 11, 467, 258);
		add(scroller);
	}
		
	private void setUpUserInput() {
		userInput = new JTextField(20);
		userInput.setBounds(10, 280, 344, 24);
		userInput.setColumns(10);
		userInput.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		add(userInput);
	}
	
	private void setUpSendButton() {
		sendButton = new JButton("Send");
		sendButton.setBounds(364, 281, 113, 23);
		sendButton.setEnabled(false);
		sendButton.setContentAreaFilled(false);
		sendButton.setBackground(PURPLE);
		sendButton.setForeground(Color.WHITE);
		add(sendButton); 
	}
			
	private void setUpConnectButton() {
		connectButton = new JButton("Connect");
		connectButton.setBounds(364, 310, 113, 23);
		connectButton.setBackground(PURPLE);
		connectButton.setForeground(Color.WHITE);
		add(connectButton); 
	}

	private void setUpServerLabel() {
		lbSever = new JLabel("Sever");
		lbSever.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbSever.setBounds(10, 315, 50, 14);
		lbSever.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbSever.setForeground(PURPLE);
		add(lbSever);
	}
	
	private void setUpServerTF() {
		tfSever = new JTextField();
		tfSever.setBounds(55, 311, 137, 20);
		tfSever.setColumns(10);
		tfSever.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		tfSever.setText("localhost");
		add(tfSever);
	}
		
	private void setUpIDLabel() {
		lblID = new JLabel("ID");
		lblID.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblID.setBounds(202, 313, 46, 14);
		lblID.setForeground(PURPLE);
		add(lblID);
	}
	
	private void setUpIDTF() {
		tfID = new JTextField();
		tfID.setBounds(231, 311, 123, 20);
		tfID.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		tfID.setColumns(10);
		add(tfID);
	}
	
	private void setUpError() {
		errors = new JLabel();
		add(errors);
	}
}
