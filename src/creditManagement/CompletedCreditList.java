package creditManagement;

import java.io.*;

import java.util.*;

import client.Client;
import question.Qna;

 
public class CompletedCreditList {
	private static CompletedCreditList ListInstance = null;
	private static ArrayList<CompletedCredit> CreditList;
	
	public final static String NO_LIST = "내역이 없습니다";
	
	private CompletedCreditList() {} // singleton으로 생성하기 위해 생성자를 private로
	
	public static CompletedCreditList get_completed_credit_list(Client client) throws ClassNotFoundException {
		if(ListInstance == null) {
			ListInstance = new CompletedCreditList();
			readCreditList(client);
		}
		return ListInstance;
	}
	public ArrayList<CompletedCredit> get_instance_list(){
		return CreditList;
	}
	
	public static void readCreditList(Client client)
	{
		CreditList = (ArrayList<CompletedCredit>)client.getObject("CreditList");
		if(CreditList == null) {
			CreditList = new ArrayList<>();
		}
	}
	
	public static void saveCreditList(Client client)
	{
		client.setObject("CreditList", CreditList);
	}
		
	//instance initial용도
	public boolean completed_credit_list_append(Client client, CompletedCredit input) throws ClassNotFoundException {
		
		//기존에 저장된 리스트 불러옴
		readCreditList(client);
		try{
			// 중복검사 - 한 학생이 수강한 것에서는 학번, 학년도, 학기, 과목은 중복이 될 수 없기에 이것으로 중복검사 (object끼리 비교가 안먹혀서 ㅠㅠ)

			int st_id = input.getStudentID();
			int year = input.getYear();
			int semester = input.getSemester();
			String univ = input.getUniv();
			String course = input.getCourse();
			boolean overlap = false;
			for(CompletedCredit one : CreditList) {
				if(one.getStudentID() == st_id && one.getUniv().equals(univ) && one.getYear() == year && one.getSemester() == semester && one.getCourse().equals(course)) {
					overlap = true;
				}
			}
			// 중복 아닐 경우에만 add
			if(!overlap) {
				CreditList.add(CreditList.size(), input);
				saveCreditList(client);
			}
			
			return true;
		}
		catch(Exception e) {
			return false; // fail
		}
		
	}
	
	public boolean completed_credit_list_modify(Client client, CompletedCredit input) throws ClassNotFoundException {
		boolean ischanged = false;
		try{
			// 수정된 사항을 저장하기 위해 리스트의 인스턴스 내에 바뀌지 않을 데이터로 기존 값 찾음. 인덱스 쓰기에는 더 복잡해짐.
			int st_id = input.getStudentID();
			int year = input.getYear();
			int semester = input.getSemester();
			String course = input.getCourse();
			String univ = input.getUniv();
			int i = 0;
			for(CompletedCredit one : CreditList) {
				if(one.getStudentID() == st_id && one.getUniv().equals(univ) && one.getYear() == year && one.getSemester() == semester && one.getCourse().equals(course)) {
					one = input;
					ischanged = true;
					// 바꾸고 업데이트
					saveCreditList(client);
				}
			}
			
		}
		catch(Exception e) {}
		return ischanged;
	}
	
	
	public int count_std_term_credit(int st_id, int year, int semester) {
		int count = 0;
		for(CompletedCredit one : CreditList) {
			if(one.getStudentID() == st_id && one.getYear() == year && one.getSemester() == semester) {
				count ++;
			}
		}
		return count;
	}
	public int count_std_term_credit_isapped(int st_id, int year, int semester) {
		int count = 0;
		for(CompletedCredit one : CreditList) {
			if(one.getStudentID() == st_id && one.getYear() == year && one.getSemester() == semester && one.isApplication_state()) {
				count ++;
			}
		}
		return count;
	}
	
	public String[] semester_list(int st_id) {
		int count = 0;
		String[] SemeList = {NO_LIST};
		if(CreditList == null) {
			return null;
		}
		else {
			int[][] temp = new int[CreditList.size()][2];
			
			for(int i = 0; i < CreditList.size(); i++) {
				if(CreditList.get(i).getStudentID() == st_id) {
					boolean judge = true;
					for(int j = 0; j < CreditList.size(); j++) {
						if(temp[j][0] == CreditList.get(i).getYear() && temp[j][1] == CreditList.get(i).getSemester()) {
							judge = false;
						}
					}
					if(judge) {
						temp[count][0] = CreditList.get(i).getYear();
						temp[count][1] = CreditList.get(i).getSemester();
						count++;
					}
				}
			}
			
			if(count > 0) {
				SemeList = new String[count];
				for(int i = 0; i < count; i++) {
					SemeList[i] = String.format("%4s년%2s학기", temp[i][0], temp[i][1]);
				}
			}
			return SemeList;
		}
	}
	
	public String[] semester_list_isapped(int st_id) {
		int count = 0;
		String[] SemeList_isapped = {NO_LIST};
		if(CreditList == null) {
			return null;
		}
		int[][] temp = new int[CreditList.size()][2];
		
		for(int i = 0; i < CreditList.size(); i++) {
			if(CreditList.get(i).getStudentID() == st_id && CreditList.get(i).isApplication_state()) {
				boolean judge = true;
				for(int j = 0; j < CreditList.size(); j++) {
					if(temp[j][0] == CreditList.get(i).getYear() && temp[j][1] == CreditList.get(i).getSemester()) {
						judge = false;
					}
				}
				if(judge) {
					temp[count][0] = CreditList.get(i).getYear();
					temp[count][1] = CreditList.get(i).getSemester();
					count++;
				}
			}
		}
		
		if(count > 0) {
			SemeList_isapped = new String[count];
			for(int i = 0; i < count; i++) {
				SemeList_isapped[i] = String.format("%4s년%2s학기", temp[i][0], temp[i][1]);
			}
		}
		return SemeList_isapped;
	}
	
	public ArrayList<CompletedCredit> completed_credit_list_print(int st_id, int year, int semester) { // 학기별 이수한 학점 목록 출력
		int count = count_std_term_credit(st_id, year, semester);
		ArrayList<CompletedCredit> CCLP = new ArrayList<>();
		
		if(count > 0) {
			for(CompletedCredit one : CreditList) {
				if(one.getStudentID() == st_id && one.getYear() == year && one.getSemester() == semester) {
					CCLP.add(one);
				}
			}
		}
		return CCLP;
	}
	
	public ArrayList<CompletedCredit> applicated_credit_list_print(int st_id, int year, int semester) { // 이수한 학점 목록 중 신청한 학점 목록 출력
		int count = count_std_term_credit_isapped(st_id, year, semester);
		ArrayList<CompletedCredit> ACLP = new ArrayList<>();
		
		if(count > 0) {
			for(CompletedCredit one : CreditList) {
				if(one.getStudentID() == st_id && one.getYear() == year && one.getSemester() == semester && one.isApplication_state()) {
					ACLP.add(one);
				}
			}
		}
		return ACLP;
	}
	
}
