package Client.chat;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.*;

import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class ClientView extends JPanel{
	JTextArea result;
	JTextField userInput;
	JTextField tfSever;
	JTextField tfID;
	JTextField tfConnectTo;
	JButton connectButton;
	JButton sendButton;
	JButton privateButton;
	JLabel errors;
	private JLabel lbSever;
	private JLabel lblID;
	private JLabel connectTo;
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
		setUpConnectToLabel();
		setUpConnectToTF();
		setUPPrivateButton();
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
		DefaultCaret caret = (DefaultCaret)result.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
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
	
	private void setUpConnectToLabel() {
		connectTo = new JLabel("Connect To");
		connectTo.setForeground(new Color(122, 0, 163));
		connectTo.setFont(new Font("SansSerif", Font.BOLD, 12));
		connectTo.setBounds(10, 347, 78, 14);
		add(connectTo);
	}
	
	private void setUpConnectToTF() {
		tfConnectTo = new JTextField();
		tfConnectTo.setToolTipText("type an online client");
		tfConnectTo.setColumns(10);
		tfConnectTo.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		tfConnectTo.setBounds(98, 344, 256, 20);
		add(tfConnectTo);
	}
	
	private void setUPPrivateButton() {
		privateButton = new JButton("Private");
		privateButton.setForeground(Color.WHITE);
		privateButton.setBackground(new Color(122, 0, 163));
		privateButton.setBounds(364, 341, 113, 23);
		add(privateButton);
	}
}
