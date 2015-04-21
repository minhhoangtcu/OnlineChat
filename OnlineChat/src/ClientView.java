import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class ClientView extends JFrame{
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
	private JPanel contentPane;
	final Color PURPLE = new Color (153, 0, 204),
			LIGHTPURPLE = new Color (158, 90, 181);
	
	
	public static void main(String[] args) {
		ClientView view = new ClientView();
		view.setVisible(true);
	}
	
	public ClientView() {
		buildView();
	}
	
	private void buildView() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 375);
		setResizable(false);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(true);
		contentPane.setBackground(new Color(250, 250, 250));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}

	private void setUpResult() {
		result = new JTextArea();
		scroller = new JScrollPane();
		result.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		scroller.setViewportView(result);
		scroller.setBounds(10, 11, 344, 258);
		contentPane.add(getContentPane().add(scroller));
	}
		
	private void setUpUserInput() {
		userInput = new JTextField(20);
		userInput.setBounds(10, 280, 344, 24);
		userInput.setColumns(10);
		userInput.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		contentPane.add(userInput);
	}
	
	private void setUpSendButton() {
		sendButton = new JButton("Send");
		sendButton.setBounds(364, 281, 113, 23);
		sendButton.setEnabled(false);
		sendButton.setContentAreaFilled(false);
		sendButton.setBackground(PURPLE);
		sendButton.setForeground(Color.WHITE);
		contentPane.add(sendButton); 
	}
			
	private void setUpConnectButton() {
		connectButton = new JButton("Connect");
		connectButton.setBounds(364, 310, 113, 23);
		connectButton.setBackground(PURPLE);
		connectButton.setForeground(Color.WHITE);
		contentPane.add(connectButton); 
	}

	private void setUpServerLabel() {
		lbSever = new JLabel("Sever");
		lbSever.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbSever.setBounds(10, 315, 50, 14);
		lbSever.setFont(new Font("SansSerif", Font.PLAIN, 12));
		getContentPane().add(lbSever);
		lbSever.setForeground(PURPLE);
		contentPane.add(lbSever);
	}
	
	private void setUpServerTF() {
		tfSever = new JTextField();
		tfSever.setBounds(55, 311, 137, 20);
		tfSever.setColumns(10);
		tfSever.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		tfSever.setText("localhost");
		contentPane.add(tfSever);
	}
		
	private void setUpIDLabel() {
		lblID = new JLabel("ID");
		lblID.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblID.setBounds(202, 313, 46, 14);
		lblID.setForeground(PURPLE);
		contentPane.add(lblID);
	}
	
	private void setUpIDTF() {
		tfID = new JTextField();
		tfID.setBounds(231, 311, 123, 20);
		tfID.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		getContentPane().add(tfID);
		tfID.setColumns(10);
		contentPane.add(tfID);
		
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.WRAP_TAB_LAYOUT);
//		tabbedPane.setBounds(10, 11, 416, 258);
//		getContentPane().add(tabbedPane);
//		
//		JPanel panel = new JPanel();
//		tabbedPane.addTab("hiiiiiaifiaii", null, panel, null);
//		
//		JPanel panel_1 = new JPanel();
//		tabbedPane.addTab("New tab", null, panel_1, null);
//		tfID.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		
	}
	
	private void setUpError() {
		errors = new JLabel();
		contentPane.add(errors);
	}
}
