import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;


public class ServerView extends JFrame {

	private JTextArea textArea;
	private JButton send;
	private JTextField enterText;
	
	public ServerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		enterText = new JTextField();
		panel.add(enterText);
		enterText.setColumns(10);
		
		send = new JButton("Send");
		panel.add(send);
	}

}
