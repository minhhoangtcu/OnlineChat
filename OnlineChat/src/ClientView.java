import java.awt.BorderLayout;
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
	JTextField tfPort;
	JButton connectButton;
	JButton sendButton;
	JLabel errors;
	private JLabel lbSever;
	private JLabel lblPort;
	private JScrollPane scroller;
	private JPanel contentPane;
	final Color PURPLE = new Color (153, 0, 204);
	
	
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
		setUpPortLabel();
		setUpPortTF();
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
		scroller.getViewport().add(result);
		scroller.setBounds(10, 11, 344, 258);
		contentPane.add(add(scroller));
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
		add(lbSever);
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
		
	private void setUpPortLabel() {
		lblPort = new JLabel("Port");
		lblPort.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPort.setBounds(230, 313, 46, 14);
		lblPort.setForeground(PURPLE);
		contentPane.add(lblPort);
	}
	
	private void setUpPortTF() {
		tfPort = new JTextField();
		tfPort.setBounds(223, 311, 131, 20);
		add(tfPort);
		tfPort.setBounds(268, 311, 86, 20);
		tfPort.setColumns(10);
		JLabel lblPort = new JLabel("ID");
		lblPort.setBounds(202, 315, 46, 14);
		lblPort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		add(lblPort);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT, JTabbedPane.WRAP_TAB_LAYOUT);
		tabbedPane.setBounds(10, 11, 416, 258);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("hiiiiiaifiaii", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		tfPort.setBorder(BorderFactory.createLineBorder(PURPLE,1));
		tfPort.setText("6969");
		contentPane.add(tfPort);
	}
	
	private void setUpError() {
		errors = new JLabel();
		contentPane.add(errors);
	}
}
