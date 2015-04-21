import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {
	JTextArea result = new JTextArea();
	JTextField userInput = new JTextField(20),
			tfSever = new JTextField(),
			tfID = new JTextField();
	JButton connectButton = new JButton("Connect"),
			sendButton = new JButton("Send");
	JLabel errors = new JLabel(),
			lbSever = new JLabel("Sever"),
			lbID = new JLabel("ID");
	JScrollPane scroller = new JScrollPane();
	Color purple = new Color (124, 0, 166),
			lightpurple = new Color (158, 90, 181);
	final static int PORT = 6969;
	JPanel contentPane;
	Socket socket;
	Scanner in;
	PrintWriter out;
	Thread thread;
	ClientControl control;
	
	public Client() {
		buildView();
	}
	
	private void buildView() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 375);
		setResizable(false);
		{
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setOpaque(true);
			contentPane.setBackground(new Color(250, 250, 250));
			contentPane.setLayout(null);
		}
		{
			result.setBorder(BorderFactory.createLineBorder(purple,1));
			result.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			        result.setBorder(BorderFactory.createLineBorder(lightpurple,1));
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			        result.setBorder(BorderFactory.createLineBorder(purple,1));
			    }
			});
			scroller.getViewport().add(result);
			scroller.setBounds(10, 11, 344, 258);
			contentPane.add(add(scroller));
		}
		{
			contentPane.add(userInput); 
			userInput.setBounds(10, 280, 344, 24);
			userInput.setColumns(10);
			userInput.setBorder(BorderFactory.createLineBorder(purple,1));
			userInput.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			        userInput.setBorder(BorderFactory.createLineBorder(lightpurple,1));
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			        userInput.setBorder(BorderFactory.createLineBorder(purple,1));
			    }
			});
			userInput.addActionListener(this);
		}
		{
			contentPane.add(sendButton);
			sendButton.setBounds(364, 281, 113, 23);
			sendButton.addActionListener(this); 
			sendButton.setEnabled(false);
			sendButton.setContentAreaFilled(false);
			sendButton.setBackground(purple);
			sendButton.setForeground(Color.WHITE);
			sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			        sendButton.setBackground(lightpurple);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			        sendButton.setBackground(purple);
			    }
			});
		}
		{
			contentPane.add(connectButton); 
			connectButton.setBounds(364, 310, 113, 23);
			connectButton.addActionListener(this);
			connectButton.setBackground(purple);
			connectButton.setForeground(Color.WHITE);
			connectButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			        connectButton.setBackground(lightpurple);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			        connectButton.setBackground(purple);
			    }
			});
		}
		{
		lbSever.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbSever.setBounds(10, 315, 50, 14);
		lbSever.setForeground(purple);
		contentPane.add(lbSever);
		
		tfSever.setBounds(55, 311, 137, 20);
		tfSever.setColumns(10);
		tfSever.setBorder(BorderFactory.createLineBorder(purple,1));
		tfSever.setText("localhost");
		tfSever.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        tfSever.setBorder(BorderFactory.createLineBorder(lightpurple,1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        tfSever.setBorder(BorderFactory.createLineBorder(purple,1));
		    }
		});
		contentPane.add(tfSever);
		
		lbID.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbID.setBounds(202, 315, 46, 14);
		lbID.setForeground(purple);
		contentPane.add(lbID);
		
		tfID.setBounds(223, 311, 131, 20);
		tfID.setColumns(10);
		tfID.setBorder(BorderFactory.createLineBorder(purple,1));
		tfID.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        tfID.setBorder(BorderFactory.createLineBorder(lightpurple,1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        tfID.setBorder(BorderFactory.createLineBorder(purple,1));
		    }
		});
		contentPane.add(tfID);
		}
		contentPane.add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
		try {
			if (evt.getActionCommand().equals("Connect") || 
					connectButton.getText().equals("Connect") && evt.getSource() == userInput) {
				String host = tfSever.getText();
				String name = tfID.getText();
				if (host.equals(null) || name.equals(null)) {
					System.out.println("Should have stop");
					return;
				}
				socket = new Socket(host, PORT);
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				thread = new ReadThread(in, result);
				thread.setName(name);
				thread.start();
				sendButton.setEnabled(true);
				sendButton.setContentAreaFilled(true);
				connectButton.setText("Disconnect");
				userInput.setText("");
			}
			else if (evt.getActionCommand().equals("Disconnect")) {
				thread.interrupt();
				socket.close();
				in.close();
				out.print(false);
				out.close();
				sendButton.setEnabled(false);
				sendButton.setContentAreaFilled(false);
				connectButton.setText("Connect");
				result.setText("");
			}
			else if (evt.getActionCommand().equals("Send") || 
						sendButton.isEnabled() && evt.getSource() == userInput) {
				out.println(userInput.getText());
				out.println(thread.getName());
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
		try {
			while ((s = in.nextLine()) != null) {
				display.append(s + '\n');
			}
		} catch (NoSuchElementException e) {}
	}
}
