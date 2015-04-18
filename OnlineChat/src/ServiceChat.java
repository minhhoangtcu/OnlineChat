import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class ServiceChat implements Runnable {
	private Socket client;
	private Scanner in;
	private PrintWriter out;
	
	public ServiceChat(Socket client) {
		this.client = client;
	}

	public void run() {
		try {
			in  = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
