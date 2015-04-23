package Server.chat;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerView extends JFrame {
	JTextField input;
	JTextArea output;
	
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.BLACK);
	}
	
	public void setUpTextArea() {
		output = new JTextArea();
		output.setBounds(10, 11, 574, 513);
		output.setBackground(Color.DARK_GRAY);
		output.setForeground(Color.WHITE);
		getContentPane().add(output, BorderLayout.CENTER);
	}

	public void setUpInput() {
		input = new JTextField();
		input.setBackground(Color.DARK_GRAY);
		input.setForeground(Color.WHITE);
		input.setBounds(10, 535, 574, 25);
		input.setColumns(10);
		getContentPane().add(input);
	}
}
