package client;

public class ClientExample {

	public static void main(String[] args) {
		Client client = new Client();
		client.setString("test", "this is a test");
		client.setString("test2", "this is a test2");
		client.addString("test2", "is overrided?", true);
		System.out.println(client.getString("test"));
		System.out.println(client.getString("test2"));
	}

}