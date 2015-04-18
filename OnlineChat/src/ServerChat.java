import java.io.IOException;
import java.net.*;

public class ServerChat {
	ServerSocket socket;
	final int PORT = 6969;
	
	public static void main(String[] args) {
		ServerChat server = new ServerChat();
		server.startClients();
	}
	
	public ServerChat() {
		try {
			socket = new ServerSocket(PORT);
			System.out.print("Server initiated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startClients() {
		whi
	}
}
