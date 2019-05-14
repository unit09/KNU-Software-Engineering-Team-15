package client;

public class ClientExample {

	public static void main(String[] args) {
		Client client = new Client();
		client.setString("key1", "this is a test");
		client.setString("key2", "apple");
		client.addString("key2", "banana", true);
		client.addString("key2", " is yelllow", false);
		System.out.println(client.getString("key1"));
		System.out.println(client.getString("key2"));
	}

}