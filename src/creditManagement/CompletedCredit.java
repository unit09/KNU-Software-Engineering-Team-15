package creditManagement;

import java.io.Serializable;

import client.Client;

public class CompletedCredit implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int studentID = 0;
	private int year = 0;
	private int semester = 0;
	private String nation = null;
	private String univ = null;
	private String dept = null;
	private String course = null;
	private int acceptCredit = 0;
	private double grade = 0.0;
	private boolean applicationState = false;
	
	public void creditApplication(Client client) throws ClassNotFoundException { // 학점 인정 신청
		CompletedCreditList list = CompletedCreditList.getCompletedCreditList(client);
		applicationState = true;
		
		list.completedCreditListModify(client, this);
		
		// 신청완료 안내창
	}
	
	public void registerCredit(Client client) throws ClassNotFoundException { // 파견 이후 새로 생성되는 이수학점 목록들
		CompletedCreditList list = CompletedCreditList.getCompletedCreditList(client);
		
		list.completedCreditListAppend(client, this);
	}
	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
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
		return acceptCredit;
	}

	public void setAcceptCredit(int acceptCredit) {
		this.acceptCredit = acceptCredit;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public boolean isApplicationState() {
		return applicationState;
	}

	public void setApplicationState(boolean applicationState) {
		this.applicationState = applicationState;
	}

}
