package question;
import java.io.IOException;
import java.util.*;

import client.Client;

public class QnAList
{
	private static ArrayList<Qna> QnaList = new ArrayList<>();
	private static ArrayList<Faq> FaqList = new ArrayList<>();
	
	public static void createQna(String username, String questionName, String question) throws IOException
	{
		Qna Q = new Qna(getQnaList().size() + 1, username, questionName, question);

		getQnaList().add(Q);
		
		//list 삽입 완료
	}
	
	
	public static void createQna(String username) throws IOException
	{
		Qna Q = new Qna(getQnaList().size() + 1, username);

		Q.typeQuestion();
		
		getQnaList().add(Q);
		
		//list 삽입 완료
	}
	
	public static void deleteQna(Qna one)
	{
		getQnaList().remove(one);
	}
	
	
	public static void createFaq(String questionName, String question, String answer)
	{
		Faq F = new Faq(FaqList.size() + 1, questionName, question, answer);
		
		FaqList.add(F);
		
		// 자주묻는 질문 등록	
	}

	
	public static void createFaq() throws IOException
	{
		Faq F = new Faq(FaqList.size() + 1);
		
		F.typeFaq();
		
		FaqList.add(F);
		
		// 자주묻는 질문 등록	
	}
	
	
	public static void readQnaList(Client client)
	{
		QnaList = (ArrayList<Qna>)client.getObject("QnAList");
	}
	
	public static void saveQnaList(Client client)
	{
		client.setObject("QnAList", QnaList);
	}
	
	public static void readFaqList(Client client)
	{
		FaqList = (ArrayList<Faq>)client.getObject("FAQList");
	}
	
	public static void saveFaqList(Client client)
	{
		client.setObject("FAQList", FaqList);
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
