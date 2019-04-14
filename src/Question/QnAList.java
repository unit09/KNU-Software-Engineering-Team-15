package Question;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class QnAList
{
	private static ArrayList<Qna> QnaList = new ArrayList<>();
	private static ArrayList<Faq> FaqList = new ArrayList<>();
	
	public static void createQna(String username, String questionName, String question) throws IOException
	{
		Qna Q = new Qna(getQnaList().size() + 1, username, questionName, question);

		//Q.typeQuestion();
		
		getQnaList().add(Q);
		
		System.out.println("list 삽입 완료");
	}
	
	
	public static void createQna(String username) throws IOException
	{
		Qna Q = new Qna(getQnaList().size() + 1, username);

		Q.typeQuestion();
		
		getQnaList().add(Q);
		
		System.out.println("list 삽입 완료");
	}
	
	
	public static void createFaq(String questionName, String question, String answer) throws IOException
	{
		Faq F = new Faq(FaqList.size() + 1, questionName, question, answer);
		
		//F.typeFaq();
		
		FaqList.add(F);
		
		System.out.println("자주묻는 질문 등록!");	
	}

	
	public static void createFaq() throws IOException
	{
		Faq F = new Faq(FaqList.size() + 1);
		
		F.typeFaq();
		
		FaqList.add(F);
		
		System.out.println("자주묻는 질문 등록!");	
	}
	
	
	public static void readQnaList()
	{
		Qna temp;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try
		{
			fis = new FileInputStream("database/qna/qna.dat");
			ois = new ObjectInputStream(fis);
			
			while(fis.available() > 0)
			{
				temp = (Qna)ois.readObject();
				getQnaList().add(temp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
				try {fis.close();} catch(IOException e) {}
			
			if(ois != null)
				try {ois.close();} catch(IOException e) {}
		}
	}
	
	public static void saveQnaList()
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try
		{
			fos = new FileOutputStream("database/qna/qna.dat");
			oos = new ObjectOutputStream(fos);
			
			for(Qna list : QnAList.getQnaList())
			{
				oos.writeObject(list);
				System.out.println("ok");
			}
			
			System.out.println("save ok!!");
		} catch (IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			if(fos != null)
				try {fos.close();} catch(IOException e) {}
			
			if(oos != null)
				try {oos.close();} catch(IOException e) {}
		}
	}
	
	public static void readFaqList()
	{
		Faq temp;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try
		{
			fis = new FileInputStream("database/qna/faq.dat");
			ois = new ObjectInputStream(fis);
			
			while(fis.available() > 0)
			{
				temp = (Faq)ois.readObject();
				getFaqList().add(temp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
				try {fis.close();} catch(IOException e) {}
			
			if(ois != null)
				try {ois.close();} catch(IOException e) {}
		}
	}
	
	public static void saveFaqList()
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try
		{
			fos = new FileOutputStream("database/qna/faq.dat");
			oos = new ObjectOutputStream(fos);
			
			for(Faq list : QnAList.getFaqList())
			{
				oos.writeObject(list);
				System.out.println("ok");
			}
			
			System.out.println("save ok!!");
		} catch (IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			if(fos != null)
				try {fos.close();} catch(IOException e) {}
			
			if(oos != null)
				try {oos.close();} catch(IOException e) {}
		}
	}
	
	public static void printQnaList()
	{
		System.out.println("<Qna Num> (QnaName) (Questioner) (QnaState)");
		
		for(Qna list : getQnaList())
		{
			System.out.print("<" + list.getQnaNum() + "> " + list.getQuestionName() + " " + list.getQuestioner());
			
			if(list.getState() == 0)
				System.out.println(" 답변 진행중");
			else
				System.out.println(" 답변 완료");
		}
	}
	
	public static void printFaqList()
	{
		for(Faq list : getFaqList())
		{
			System.out.println(list.getFaqNum() + " " + list.getQuestionName());
			
			System.out.println(list.getQuestion());
			System.out.println(list.getAnswer());
		}
	}

	public static ArrayList<Qna> getQnaList()
	{
		return QnaList;
	}
	
	public static ArrayList<Faq> getFaqList()
	{
		return FaqList;
	}

	public static void setQnaList(ArrayList<Qna> qnaList)
	{
		QnaList = qnaList;
	}
	
	public static void setFaqList(ArrayList<Faq> faqList)
	{
		FaqList = faqList;
	}
}
