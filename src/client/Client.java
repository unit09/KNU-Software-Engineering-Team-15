package client;

import org.json.JSONObject;

public class Client {
	
	JSONObject file = new JSONObject();
	
	public Client() {
	}
	
	public String getString(String key) {
		return file.get(key).toString();
	}
	
	public void setString(String key, String value) {
		file.put(key, value);
	}
	
}
