package Client.chat;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class ClientChatView extends JFrame {

	private JPanel contentPane;
	JTabbedPane tabbedPane;
	final Color PURPLE = new Color (122, 0, 163),
			LIGHTPURPLE = new Color (181, 113, 204);

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
		setBounds(100, 100, 510, 415);
		setTitle("Client Chat");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(LIGHTPURPLE);
		setContentPane(contentPane);
		setColorTheme();
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(PURPLE);
		tabbedPane.setForeground(Color.WHITE);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
	
	private void setColorTheme() {
		UIManager.put("TabbedPane.selected", LIGHTPURPLE);
		UIManager.put("TabbedPane.borderHightlightColor", LIGHTPURPLE);
		UIManager.put("TabbedPane.contentAreaColor", LIGHTPURPLE);
		UIManager.put("TabbedPane.darkShadow", LIGHTPURPLE);
		UIManager.put("TabbedPane.focus", LIGHTPURPLE);
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
	}
}
