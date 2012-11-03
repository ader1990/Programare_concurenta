package Bureau;

import java.util.UUID;
import java.util.Vector;
import Client.Client;
import Interfaces.Findable;

public class Bureau implements Findable, Runnable {

	private UUID id = UUID.randomUUID();
	private int officeNumber = 0;
	private Vector<Client> clients = new Vector<Client>();

	public Bureau() {
		this.officeNumber = 5;
	}

	public synchronized int getOffices() {
		return this.officeNumber;
	}

	public synchronized void decreaseOfficeNumber() {
		this.officeNumber--;
	}

	public synchronized void increaseOfficeNumber() {
		this.officeNumber++;
	}

	public synchronized void resetOffices() {
		this.officeNumber = ((int) (Math.random() * 1000)) % 10;
	}

	public void serveClient(Client c) {
		this.decreaseOfficeNumber();
		try {
			c.notify();
			Thread.sleep(1000);
			System.out.println("Bureau " + this.getId() + " Serving client "
					+ c.getId() + " with nrdoc" + c.getNrDoc());
			this.increaseOfficeNumber();
			c.decreaseDocuments();
			if (this.clients.size() > 1)
				this.clients.get(this.clients.indexOf(c) + 1).notify();
			if (c.getNrDoc() == 0)
				this.clients.remove(this.clients.indexOf(c));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void addClient(Client c) {
		this.clients.add(c);
		try {
			c.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println(this);
		new Thread(new OfficeNumberSetter(this)).start();
		new Thread(new ClientServer(this)).start();
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class OfficeNumberSetter implements Runnable {
		private Bureau bureau;

		public OfficeNumberSetter(Bureau bureau) {
			this.bureau = bureau;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					Thread.sleep(1000);
					this.bureau.resetOffices();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	private class ClientServer implements Runnable {
		private Bureau bureau;

		public ClientServer(Bureau bureau) {
			this.bureau = bureau;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {

				for (int i = 0; i < this.bureau.clients.size(); i++) {
					this.bureau.serveClient(this.bureau.clients.get(i));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public String toString() {
		return "Bureau ---" + this.getId().toString().substring(0, 4)
				+ "--- with nr clients:" + this.clients.size();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id.toString().substring(0, 4);
	}

}
