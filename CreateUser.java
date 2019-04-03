package User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateUser {

    public static void main(String[] args){
        File test= new File("User DB.txt");
        ArrayList<Student> userset= new ArrayList<>();
        Student temp = new Student(2015123456, "김길동", "컴퓨터학부", 2, 2.14, "test1@naver.com", "010-1264-1231");
        Student temp2 = new Student(-1, "관리자", "관리자용학생객체", 777, 0, "year가 관리자번호", "우끼끼");
        Student temp3 = new Student(2015111111, "홍길동", "컴퓨터학부", 2, 4.12, "test@naver.com", "010-1234-5678");

        userset.add(temp);
        userset.add(temp2);
        userset.add(temp3);

        try {
            FileOutputStream fos = new FileOutputStream(test);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try{
                oos.writeObject(userset);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }catch (Exception ex){
            System.out.println("파일입출력 에러");
            System.exit(1);
        }
    }
}
