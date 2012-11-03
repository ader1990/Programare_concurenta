package Manager;

import java.util.Vector;

import Bureau.Bureau;
import Client.Client;
import Document.Document;

public class ManagerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Vector<Client> clients = new Vector<Client>();
		Vector<Bureau> bureaus = new Vector<Bureau>();

		int nrClients = 10;
		Client client;
		for (int j = 0; j < nrClients; j++) {
			client = new Client();
			clients.add(client);
			new Thread(client).start();
		}

		int nrBureaus = 10;
		Bureau bureau;
		for (int j = 0; j < nrBureaus; j++) {
			bureau = new Bureau();
			bureaus.add(bureau);
			new Thread(bureau).start();
		}

		for (int c = 0; c < clients.size(); c++) {
			int bureauNr = ((int) (Math.random() * 1000)) % bureaus.size();
			bureaus.get(bureauNr).addClient(clients.get(c));
		}

	}

}
