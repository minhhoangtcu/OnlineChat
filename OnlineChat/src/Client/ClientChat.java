/**************************************************************************/
/*  Program Name:    Lab# 4                                               */
/*                                                                        */
/*  Student Name:    Quang Nguyen,Minh Hoang, Kiet Nguyen, Le Bui         */
/*  Semester:        Spring,  2015                                        */
/*  Class & Section: CoSc 20203                                           */
/*  Instructor:      Dr. Rinewalt Dick                                    */
/*  Due Date:        April 29, 2015                                    */
/*                                                                        */
/*  Program Overview:                                                     */
/*      This applet use socket and multi-threading to create a chat client,*/ 
/* connect it to the server to process chat content between many clients. Many
/* tabs can be opened to represent new clients within the same window at once */
/*                                                                        */
/*                                                                        */
/*  Program Limitations:                                                  */
/*      (1) No private chat function available yet.                       */
/*      (2) Difficult to read chat from many users at the same time       */
/*                                                                        */
/*  Programme Strength:                                                   */
/* 		(1) Multiple tabs for multiple client which are easy to switch between*/
/* different chat client                                                  */
/*                                                                        */
/*  Significant Program Variables:                                        */
/*     socket, thread, instances of clients.                              */ 

/**************************************************************************/

package Client;
import Client.chat.Client;
import Client.chat.ClientChatControl;

public class ClientChat {
	
	private final int MAX = 6;
	private Client[] clients = new Client [MAX];
	private int numberOfClients = 0;
	
	/*
	 * Create an instance of the class ClientChat to start program
	 */
	public static void main (String[] args) {
		new ClientChat();
	}
	
	/*
	 * Create a Control instance which handles all the listeners inside Control
	 */
	public ClientChat () {
		ClientChatControl control = new ClientChatControl(this);
		addClient();
		control.addTab("New Tab", clients[0]);
		addClient();
		control.addTab("", clients[1]);
	}
	
	/*
	 * Return client
	 */
	public Client[] returnClients() {
		return clients;
	}
	
	public Client returnClient (int index) {
		return clients[index];
	}
	
	/*
	 * add client into CLient arraylist 
	 */
	public void addClient() {
		Client client = new Client();
		clients[numberOfClients] = client;
		numberOfClients++;
	}
	/*
	 * remove a client after disconnet
	 */
	public void removeClient(int index) {
		for (int i = index; i < numberOfClients - 1; i++) {
			clients[i] = clients[i + 1];
		}
		numberOfClients--;
	}
	
	//return the number of clients connected
	public int returnNumberOfClients () {
		return numberOfClients;
	}
}
