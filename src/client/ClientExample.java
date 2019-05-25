package client;

import java.io.Serializable;

import org.json.simple.JSONObject;

public class ClientExample {

	public static void main(String[] args) {
//		Client client = new Client();
//		client.setString("key1", "this is a test");
//		client.setString("key2", "apple");
//		client.addString("key2", "banana", true);
//		client.addString("key2", " is yellow", false);
//		System.out.println(client.getString("key1"));
//		System.out.println(client.getString("key2"));
//		
//		client.remove("key2");
//		System.out.println("key2 값을 지웠습니다.\n" + client.getString("key2"));
		
		Client client = new Client();
		A a = new A();
		a.x = 11;
		
		A a2 = new A();
		a2.x = 22;
		
		client.setObject("A", a);
		client.setObject("A2", a2);
		
		client.setString("str", "hello world!!");
		client.addString("str", " hihihihihihihi", false);
		
		A aa = (A)client.getObject("A");
		A aaa = (A)client.getObject("A2");
		
		System.out.println(aa.x);
		System.out.println(aaa.x);
		
		System.out.println(client.getString("str"));
		client.remove("str");
		System.out.println(client.getString("str"));
		
		JSONObject o = new JSONObject();
		o.put("id", "2015117202");
		o.put("A", a);
		
		client.setObject("student1", o);
		JSONObject o2 = (JSONObject)client.getObject("student1");
		System.out.println(o2.get("id"));
		System.out.println(((A)o2.get("A")).x);
	}

}

class A implements Serializable {
	int x;
}