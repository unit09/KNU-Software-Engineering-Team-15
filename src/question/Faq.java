package question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Faq implements Serializable
{
	private static final long serialVersionUID = 2L;
	private int faqNum;
	private String questionName;
	private String question;
	private String answer;
	
	transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Faq(int faqNum, String questionName, String question, String answer)
	{
		this.faqNum = faqNum;
		this.questionName = questionName;
		this.question = question;
		this.answer = answer;
	}
	
	
	public Faq(int faqNum)
	{
		this.faqNum = faqNum;
	}
	
	
	public void typeFaq() throws IOException
	{		
		String line = null;
		
		System.out.println("관리자 전용 FAQ 등록");		
		
		System.out.println("질문 제목 입력 : ");
		
		this.questionName = br.readLine();
		
		System.out.println("질문 내용 입력 : ");
		
		this.question = br.readLine();
		
		while( (line = br.readLine()) != null)
		{
			if(line.equals("end"))
				break;
			
			this.question = this.question + "\n" + line;
		}
		
		System.out.println("질문 답변 입력 : ");
		
		this.answer = br.readLine();
		
		while( (line = br.readLine()) != null)
		{
			if(line.equals("end"))
				break;
			
			this.answer = this.answer + "\n" + line;
		}
	}
	
	public void printFaq()
	{
		System.out.println(this.question);
		System.out.println(this.answer);
	}
	
	public int getFaqNum() {
		return faqNum;
	}

	public void setFaqNum(int faqNum) {
		this.faqNum = faqNum;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
