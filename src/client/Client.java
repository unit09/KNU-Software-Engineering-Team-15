package client;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Client {
	
	JSONObject file;
	static String fileName = "data.txt";
	
	public Client() {
		file = new JSONObject();
	}
	
	//key 저장된 내용을 읽어온다. 저장된 게 없으면 null을 반환한다.
	public String getString(String key) {
		return this.read(key);
	}
	
	//key에 저장된 값이 기존에 있었건 없었건간에 덮어 씌운다
	public void setString(String key, String value) {
		try {
			write(key, value);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	//key에 저장되어 있던 데이터 뒤에 새로운 value를 덧붙인다.
	//addEnter true이면 "기존 데이터" + "\n" + "새 데이터"
	//addEnter false이면 "기존 데이터" + "새 데이터"
	//기존에 저장된 key가 없으면 그냥 setString과 동일하다
	public void addString(String key, String value, boolean addEnter) {
		String temp = this.getString(key);
		
		try {
			write(key, (temp == null ? "" : temp) + (temp != null && addEnter ? "\n" : "") + value);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	private void write(String key, String value) throws IOException {
		String saved = this.read();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		String message = null;
		try {
			jsonObject = (JSONObject) parser.parse(saved);
		} catch (ParseException e) {
			//파싱할 게 없는 경우(파일이 비어있거나 공백이거나 JSON 형식이 아닌 이상한 문자가 적혀 있으면 catch로 온다.)
			jsonObject = new JSONObject();
		}
		jsonObject.put(key, value);
		message = jsonObject.toString();
		
		write(message);
	}
	
	private void write(String value) throws IOException {
		if(value == null) {
			System.out.print("[경고] write(value)에서 value가 null입니다.\n");
		}
		
		BufferedOutputStream buffer = null;
		try {
			OutputStream outputStream = new FileOutputStream(fileName);
			buffer = new BufferedOutputStream(outputStream);
			byte by[] = value.getBytes();
			buffer.write(by);
		} catch(Exception e) {
			e.getStackTrace();
		} finally {
			buffer.close();
		}
	}
	
	private String read(String key) {
		String saved = read();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)parser.parse(saved);
		} catch (ParseException e) {
			e.getStackTrace();
		}
		
		Object value = (Object)jsonObject.get(key);
		
		return value == null ? null : jsonObject.get(key).toString();
	}
	
	private String read() {
		byte buffer[] = null;
		try {
			InputStream inputStream = new FileInputStream(fileName);
			buffer = new byte[inputStream.available()];
			while(inputStream.read(buffer) != -1 && !(new String(buffer)).equals("")) {}
			
			inputStream.close();
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return (buffer == null) ? "" : new String(buffer);
	}
	
}
