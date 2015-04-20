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
	JTextField userInput = new JTextField(20);
	JTextField tfSever = new JTextField();
	JTextField tfPort = new JTextField();
	JButton connectButton = new JButton("Connect");
	JButton sendButton = new JButton("Send");
	JLabel errors = new JLabel();
	JLabel lbSever = new JLabel("Sever");
	JLabel lblPort = new JLabel("Port");
	JScrollPane scroller = new JScrollPane();
	Color purple = new Color (153, 0, 204);
	JPanel contentPane;
	Socket socket;
	Scanner in;
	PrintWriter out;
	Thread thread;
	
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
			        result.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
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
			        userInput.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
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
			        sendButton.setBackground(Color.BLACK);
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
			        connectButton.setBackground(Color.BLACK);
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
		        tfSever.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        tfSever.setBorder(BorderFactory.createLineBorder(purple,1));
		    }
		});
		contentPane.add(tfSever);
		
		lblPort.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPort.setBounds(230, 313, 46, 14);
		lblPort.setForeground(purple);
		contentPane.add(lblPort);
		
		tfPort.setBounds(268, 311, 86, 20);
		tfPort.setColumns(10);
		tfPort.setBorder(BorderFactory.createLineBorder(purple,1));
		tfPort.setText("6969");
		tfPort.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        tfPort.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        tfPort.setBorder(BorderFactory.createLineBorder(purple,1));
		    }
		});
		contentPane.add(tfPort);
		}
		contentPane.add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
		try {
			if (evt.getActionCommand().equals("Connect") || 
					connectButton.getText().equals("Connect") && evt.getSource() == userInput) {
				String host = tfSever.getText();
				if (host.equals("")) return;
				int port;
				try {
					port = Integer.parseInt(tfPort.getText());
				} catch (NumberFormatException e) {
					return;
				}
				socket = new Socket(host, port);
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), false);
				thread = new ReadThread(in, result);
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
				out.close();
				sendButton.setEnabled(false);
				sendButton.setContentAreaFilled(false);
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