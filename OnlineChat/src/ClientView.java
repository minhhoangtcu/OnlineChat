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

public class ClientView extends JPanel {
	private JTextField tfSever;
	private JTextField tfPort;
	public ClientView() {
		setLayout(null);
		
		JTextArea result = new JTextArea();
		result.setForeground(Color.BLACK);
		result.setBounds(10, 11, 344, 258);
		add(result);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 280, 344, 24);
		add(textArea);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(364, 281, 113, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(364, 310, 113, 23);
		add(btnNewButton_1);
		
		JLabel lbSever = new JLabel("Sever");
		lbSever.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lbSever.setBounds(10, 315, 50, 14);
		add(lbSever);
		
		tfSever = new JTextField();
		tfSever.setBounds(55, 311, 137, 20);
		add(tfSever);
		tfSever.setColumns(10);
		
		tfPort = new JTextField();
		tfPort.setBounds(268, 311, 86, 20);
		add(tfPort);
		tfPort.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPort.setBounds(230, 313, 46, 14);
		add(lblPort);
	}
}
