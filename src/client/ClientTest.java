package client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {
	Client one = new Client();
	String TestKey = "TestKey";
	String test = "Test";
	@Test
	void testGetString() {
		one.setString(TestKey, test);
		assertEquals(test, one.getString(TestKey));
	}
}
