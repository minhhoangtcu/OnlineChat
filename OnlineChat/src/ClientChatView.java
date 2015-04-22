import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Canvas;


public class ClientChatView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClientChatView display = new ClientChatView();
		display.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ClientChatView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		Client client = new Client();
		tabbedPane.addTab("New tab", null, client.control.view, null);
		}
}
