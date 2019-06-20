package creditManagement;

import java.io.Serializable;

import client.Client;

public class CompletedCredit implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int StudentID = 0;
	private int year = 0;
	private int semester = 0;
	private String nation = null;
	private String univ = null;
	private String dept = null;
	private String course = null;
	private int AcceptCredit = 0;
	private double grade = 0.0;
	private boolean ApplicationState = false;
	
	public void credit_application(Client client) throws ClassNotFoundException { // 학점 인정 신청
		CompletedCreditList list = CompletedCreditList.get_completed_credit_list(client);
		ApplicationState = true;
		
		list.completed_credit_list_modify(client, this);
		
		// 신청완료 안내창
	}
	
	public void register_credit(Client client) throws ClassNotFoundException { // 파견 이후 새로 생성되는 이수학점 목록들
		CompletedCreditList list = CompletedCreditList.get_completed_credit_list(client);
		
		list.completed_credit_list_append(client, this);
	}
	
	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int StudentID) {
		this.StudentID = StudentID;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getUniv() {
		return univ;
	}

	public void setUniv(String univ) {
		this.univ = univ;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getAcceptCredit() {
		return AcceptCredit;
	}

	public void setAcceptCredit(int AcceptCredit) {
		this.AcceptCredit = AcceptCredit;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public boolean isApplication_state() {
		return ApplicationState;
	}

	public void setApplicationState(boolean ApplicationState) {
		this.ApplicationState = ApplicationState;
	}

}
