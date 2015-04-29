package Server.chat;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class ServerView extends JFrame {
	JTextField input;
	JTextArea output;
	JScrollPane scroller;
	
	public static void main(String[] args) {
		ServerView view = new ServerView();
		view.setVisible(true);
	}
	
	public ServerView() {
		setUpContentPane();
		setUpTextArea();
		setUpInput();
	}
	
	public void setUpContentPane() {
		setTitle("SERVER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
	}
	
	public void setUpTextArea() {
		scroller = new JScrollPane();
		output = new JTextArea();
		scroller.setViewportView(output);
		scroller.setBounds(10, 11, 574, 513);
		DefaultCaret caret = (DefaultCaret)output.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		output.setBackground(Color.DARK_GRAY);
		output.setForeground(Color.WHITE);
		add(scroller, BorderLayout.CENTER);
	}

	public void setUpInput() {
		input = new JTextField();
		input.setBackground(Color.DARK_GRAY);
		input.setForeground(Color.WHITE);
		input.setBounds(10, 535, 574, 25);
		input.setColumns(10);
		add(input);
	}
}
