package Client.chat;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class ClientChatView extends JFrame {

	private JPanel contentPane;
	JTabbedPane tabbedPane;

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
		setTitle("Client Chat");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
}
