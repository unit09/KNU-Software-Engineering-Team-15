package client;

import org.json.JSONObject;

public class Client {
	
	JSONObject file;
	
	public Client() {
		file = new JSONObject();
	}
	
	public String getString(String key) {
		return file.getString(key);
	}
	
	public void setString(String key, String value) {
		file.put(key, value);
	}
	
	//key에 저장되어 있던 데이터 뒤에 새로운 value를 덧붙인다.
	//addEnter true이면 "기존 데이터" + "\n" + "새 데이터"
	//addEnter false이면 "기존 데이터" + "새 데이터"
	public void addString(String key, String value, boolean addEnter) {
		String temp = file.getString(key);
		file.put(key, temp + (addEnter ? "\n" : "") + value);
	}
	
}
