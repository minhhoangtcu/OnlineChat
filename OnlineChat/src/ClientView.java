import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ClientView extends JFrame{
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
	
	public static void main() {
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
		setContentPane(contentPane);
		contentPane.setOpaque(true);
		contentPane.setBackground(new Color(250, 250, 250));
		contentPane.setLayout(null);
	}

	private void setUpResult() {
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
		
	private void setUpUserInput() {
		contentPane.add(userInput); 
		userInput.setBounds(10, 280, 344, 24);
		userInput.setColumns(10);
		userInput.setBorder(BorderFactory.createLineBorder(purple,1));
	}
	
	private void setUpSendButton() {
		contentPane.add(sendButton); 
		sendButton.setBounds(364, 281, 113, 23);
		sendButton.setEnabled(false);
		sendButton.setContentAreaFilled(false);
		sendButton.setBackground(purple);
		sendButton.setForeground(Color.WHITE);
	}
			
	private void setUpConnectButton() {
		contentPane.add(connectButton); 
		connectButton.setBounds(364, 310, 113, 23);
		connectButton.setBackground(purple);
		connectButton.setForeground(Color.WHITE);
	}

	private void setUpServerLabel() {
		lbSever.setFont(new Font("SansSerif", Font.BOLD, 12));
		lbSever.setBounds(10, 315, 50, 14);
		lbSever.setForeground(purple);
		contentPane.add(lbSever);
	}
	
	private void setUpServerTF() {
		tfSever.setBounds(55, 311, 137, 20);
		tfSever.setColumns(10);
		tfSever.setBorder(BorderFactory.createLineBorder(purple,1));
		tfSever.setText("localhost");
		contentPane.add(tfSever);
	}
		
	private void setUpPortLabel() {
		lblPort.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPort.setBounds(230, 313, 46, 14);
		lblPort.setForeground(purple);
		contentPane.add(lblPort);
	}
	
	private void setUpPortTF() {
		tfPort.setBounds(268, 311, 86, 20);
		tfPort.setColumns(10);
		tfPort.setBorder(BorderFactory.createLineBorder(purple,1));
		tfPort.setText("6969");
		contentPane.add(tfPort);
	}
	
	private void setUpError() {
		contentPane.add(errors);
	}
}