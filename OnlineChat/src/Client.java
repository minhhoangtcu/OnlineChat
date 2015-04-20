import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {
	JTextArea result = new JTextArea();
	JTextField userInput = new JTextField(20);
	JButton connectButton = new JButton("Connect");
	JButton sendButton = new JButton("Send");
	JLabel errors = new JLabel();
	JScrollPane scroller = new JScrollPane();
	JPanel contentPane;
	Socket socket;
	Scanner in;
	PrintWriter out;
	Thread thread;
	
	public Client() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scroller.getViewport().add(result);
		scroller.setBounds(10, 11, 414, 221);
		contentPane.add(add(scroller));
		{
			contentPane.add(userInput); 
			userInput.setBounds(203, 239, 221, 22);
			userInput.setText("localhost 6969");
			userInput.setColumns(10);
			userInput.addActionListener(this);
		}
		{
			contentPane.add(sendButton); 
			sendButton.setBounds(10, 238, 89, 23);
			sendButton.addActionListener(this); 
			sendButton.setEnabled(false);
			sendButton.setContentAreaFilled(false);
			sendButton.setBackground(new Color (153, 0, 204));
			sendButton.setForeground(Color.WHITE);
			sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			        sendButton.setBackground(Color.BLACK);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			        sendButton.setBackground(new Color (153, 0, 204));
			    }
			});
		}
		{
			contentPane.add(connectButton); 
			connectButton.setBounds(104, 238, 89, 23);
			connectButton.addActionListener(this);
			connectButton.setBackground(new Color (153, 0, 204));
			connectButton.setForeground(Color.WHITE);
			connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			        connectButton.setBackground(Color.BLACK);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			        connectButton.setBackground(new Color (153, 0, 204));
			    }
			});
		}
		contentPane.add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
		try {
			if (evt.getActionCommand().equals("Connect") || 
					connectButton.getText().equals("Connect") && evt.getSource() == userInput) {
				Scanner scanner = new Scanner(userInput.getText());
				if (!scanner.hasNext()) return;
				String host = scanner.next();
				if (!scanner.hasNextInt()) return;
				int port = scanner.nextInt();
				socket = new Socket(host, port);
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), false);
				thread = new ReadThread(in, result);
				thread.start();
				sendButton.setEnabled(true);
				connectButton.setText("Disconnect");
				userInput.setText("");
			}
			else if (evt.getActionCommand().equals("Disconnect")) {
				thread.interrupt();
				socket.close();
				in.close();
				out.close();
				sendButton.setEnabled(false);
				connectButton.setText("Connect");
				result.setText("");
			}
			else if (evt.getActionCommand().equals("Send") || 
						sendButton.isEnabled() && evt.getSource() == userInput) {
				out.print(userInput.getText() + "\n");
				out.flush();
				userInput.setText("");
			}
		} catch(UnknownHostException uhe) {
			errors.setText(uhe.getMessage());
		} catch(IOException ioe) {
			errors.setText(ioe.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Client display = new Client();
		display.setVisible(true);
	}
}

class ReadThread extends Thread {
	Scanner in;
	JTextArea display;
	public ReadThread(Scanner br, JTextArea jta) {
		in = br;
		display = jta;
	}
	public void run() {
		String s;
		while ((s = in.nextLine()) != null) {
			display.append(s + '\n');
		}
	}
}