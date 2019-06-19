package question;
import java.io.*;

public class Qna implements Serializable
{
	private static final long serialVersionUID = 2L;
	private int qnaNum;
	private String questionName;
	private String questioner;
	private String question;
	private String answer;
	private int state; //question = 0 answer = 1
	
	transient BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public Qna(int qnaNum, String questioner, String questionName, String question)
	{
		this.qnaNum = qnaNum;
		this.questioner = questioner;
		this.question = question;
		this.questionName = questionName;
		this.answer = null;
		this.state = 0;
	}
	
	
	public Qna(int qnaNum, String questioner)
	{
		this.questioner = questioner;
		this.qnaNum = qnaNum;
		this.answer = null;
		this.state = 0;
	}
	
	
	public void typeQuestion() throws IOException
	{
		String line = null;
		
		// 질문 제목 입력
		
		this.questionName = br.readLine();
		
		// 질문 내용 입력(end입력)
		
		this.question = br.readLine();
		
		while( (line = br.readLine()) != null)
		{
			if(line.equals("end"))
				break;
			
			this.question = this.question + "\n" + line;
		}
	}
	
	public void getAnswer(String answer) //관리자 확인 필요
	{
		this.answer = answer;
		this.state = 1;
	}
	
	public int getQnaNum() {
		return qnaNum;
	}

	public void setQnaNum(int qnaNum) {
		this.qnaNum = qnaNum;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestioner() {
		return questioner;
	}

	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return question;
	}
}
