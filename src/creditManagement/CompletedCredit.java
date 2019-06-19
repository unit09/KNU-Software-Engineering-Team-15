package creditManagement;

import java.io.Serializable;

import client.Client;

public class CompletedCredit implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int st_id = 0;
	private int year = 0;
	private int semester = 0;
	private String nation = null;
	private String univ = null;
	private String dept = null;
	private String course = null;
	private int accept_credit = 0;
	private double grade = 0.0;
	private boolean application_state = false;
	
	public void credit_application(Client client) throws ClassNotFoundException { // 학점 인정 신청
		CompletedCreditList list = CompletedCreditList.get_completed_credit_list(client);
		application_state = true;
		
		list.completed_credit_list_modify(client, this);
		
		// 신청완료 안내창
	}
	
	public void register_credit(Client client) throws ClassNotFoundException { // 파견 이후 새로 생성되는 이수학점 목록들
		CompletedCreditList list = CompletedCreditList.get_completed_credit_list(client);
		
		list.completed_credit_list_append(client, this);
	}
	
	public int getSt_id() {
		return st_id;
	}

	public void setSt_id(int st_id) {
		this.st_id = st_id;
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

	public int getAccept_credit() {
		return accept_credit;
	}

	public void setAccept_credit(int accept_credit) {
		this.accept_credit = accept_credit;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public boolean isApplication_state() {
		return application_state;
	}

	public void setApplication_state(boolean application_state) {
		this.application_state = application_state;
	}

}
