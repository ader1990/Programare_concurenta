package Client;

import java.util.UUID;
import java.util.Vector;

import Document.Document;
import Interfaces.Findable;

public class Client implements Findable, Runnable {
	private UUID id = UUID.randomUUID();
	private Vector<Document> documents = new Vector<Document>();

	public Client() {
		int docNr = 10;
		for (int i = 0; i < docNr; i++) {
			Document doc = new Document();
			this.documents.add(doc);
		}
	}

	public Client(Vector<Document> documents) {
		this.documents = documents;
	}

	public boolean decreaseDocuments() {
		if (this.documents.size() > 0) {
			this.documents.remove(0);
			return true;
		} else
			return false;
	}

	public String toString() {
		return "Client ---" + this.getId().toString().substring(0, 4)
				+ "--- with nr documents:" + this.documents.size();
	}

	@Override
	public void run() {
		System.out.println(this);
		while (this.documents.size() > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getNrDoc() {
		return this.documents.size();
	}

	public synchronized void terminate() {
		if (this.documents.size() == 0) {
			try {
				System.out.println("Client has finished running for documents");
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id.toString().substring(0, 4);
	}

}
