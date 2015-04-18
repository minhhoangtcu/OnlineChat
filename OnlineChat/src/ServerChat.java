import java.io.IOException;
import java.net.*;

public class ServerChat {
	ServiceChat[] services;
	ServerSocket server;
	final int PORT = 6969;
	final int MAX_NUMBER_OF_CLIENTS = 10;
	int currentNumberOfClients;
	
	public static void main(String[] args) {
		ServerChat server = new ServerChat();
		server.initClients();
	}
	
	public ServerChat() {
		services = new ServiceChat[MAX_NUMBER_OF_CLIENTS];
		currentNumberOfClients = 0;
		try {
			server = new ServerSocket(PORT);
			System.out.print("Server initiated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method handle error for the method startClients
	 */
	public void initClients() {
		try {
			startClients();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This method wait and run thread of each client
	 */
	public void startClients() throws IOException{
		 while (true) {
			 Socket client = server.accept();
			 System.out.print("Client connected");
			 ServiceChat service = new ServiceChat(client);
			 Thread t = new Thread(service);
			 t.start();
		 }
	}
}
