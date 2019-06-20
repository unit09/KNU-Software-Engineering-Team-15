package systemUI;

import client.Client;
import user.*;

public class Test {
	
	public static void main(String[] args) {
		Client client = new Client();
		
		//Student temp = new Student(2015123456, "김길동", "컴퓨터학부", 2, 2.14, "test1@naver.com", "010-1264-1231");
        Student temp2 = new Student(-1, "관리자", "관리자용학생객체", 777, 0, "year가 관리자번호", " ");
        //Student temp3 = new Student(2015123456, "홍길동", "컴퓨터학부", 2, 4.12, "test@naver.com", "010-1234-5678");
        
        //client.setObject("s1123", "1123", temp);
       //client.setObject("s1132", "1132", temp3);
        client.setObject("a1124", "1124", temp2);
	}
}
