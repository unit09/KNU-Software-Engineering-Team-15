package creditManagement;

import java.util.*;

import client.Client;

public class CompletedCreditList {
	private static CompletedCreditList listInstance = null;
	private static ArrayList<CompletedCredit> creditList;
	
	public final static String noList = "내역이 없습니다";
	
	private CompletedCreditList() {} // singleton으로 생성하기 위해 생성자를 private로
	
	public static CompletedCreditList getCompletedCreditList(Client client) throws ClassNotFoundException {
		if(listInstance == null) {
			listInstance = new CompletedCreditList();
			readCreditList(client);
		}
		return listInstance;
	}
	public ArrayList<CompletedCredit> get_instance_list(){
		return creditList;
	}
	
	public static void readCreditList(Client client)
	{
		creditList = (ArrayList<CompletedCredit>)client.getObject("CreditList");
		if(creditList == null) {
			creditList = new ArrayList<>();
		}
	}
	
	public static void saveCreditList(Client client)
	{
		client.setObject("CreditList", creditList);
	}
		
	//instance initial용도
	public boolean completedCreditListAppend(Client client, CompletedCredit input) throws ClassNotFoundException {
		
		//기존에 저장된 리스트 불러옴
		readCreditList(client);
		try{
			// 중복검사 - 한 학생이 수강한 것에서는 학번, 학년도, 학기, 과목은 중복이 될 수 없기에 이것으로 중복검사 (object끼리 비교가 안먹혀서 ㅠㅠ)

			int studentID = input.getStudentID();
			int year = input.getYear();
			int semester = input.getSemester();
			String univ = input.getUniv();
			String course = input.getCourse();
			boolean overlap = false;
			for(CompletedCredit one : creditList) {
				if(one.getStudentID() == studentID && one.getUniv().equals(univ) && one.getYear() == year && one.getSemester() == semester && one.getCourse().equals(course)) {
					overlap = true;
				}
			}
			// 중복 아닐 경우에만 add
			if(!overlap) {
				creditList.add(creditList.size(), input);
				saveCreditList(client);
			}
			
			return true;
		}
		catch(Exception e) {
			return false; // fail
		}
		
	}
	
	public boolean completedCreditListModify(Client client, CompletedCredit input) throws ClassNotFoundException {
		boolean ischanged = false;
		try{
			// 수정된 사항을 저장하기 위해 리스트의 인스턴스 내에 바뀌지 않을 데이터로 기존 값 찾음. 인덱스 쓰기에는 더 복잡해짐.
			int studentID = input.getStudentID();
			int year = input.getYear();
			int semester = input.getSemester();
			String course = input.getCourse();
			String univ = input.getUniv();
			int i = 0;
			for(CompletedCredit one : creditList) {
				if(one.getStudentID() == studentID && one.getUniv().equals(univ) && one.getYear() == year && one.getSemester() == semester && one.getCourse().equals(course)) {
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
	
	
	public int countStudnetTermCredit(int studentID, int year, int semester) {
		int count = 0;
		for(CompletedCredit one : creditList) {
			if(one.getStudentID() == studentID && one.getYear() == year && one.getSemester() == semester) {
				count ++;
			}
		}
		return count;
	}
	public int countStudentTermCreditIsapped(int studentID, int year, int semester) {
		int count = 0;
		for(CompletedCredit one : creditList) {
			if(one.getStudentID() == studentID && one.getYear() == year && one.getSemester() == semester && one.isApplicationState()) {
				count ++;
			}
		}
		return count;
	}
	
	public String[] semesterList(int studentID) {
		int count = 0;
		String[] SemeList = {noList};
		if(creditList == null) {
			return null;
		}
		else {
			int[][] temp = new int[creditList.size()][2];
			
			for(int i = 0; i < creditList.size(); i++) {
				if(creditList.get(i).getStudentID() == studentID) {
					boolean judge = true;
					for(int j = 0; j < creditList.size(); j++) {
						if(temp[j][0] == creditList.get(i).getYear() && temp[j][1] == creditList.get(i).getSemester()) {
							judge = false;
						}
					}
					if(judge) {
						temp[count][0] = creditList.get(i).getYear();
						temp[count][1] = creditList.get(i).getSemester();
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
	
	public String[] semesterListIsapped(int studentID) {
		int count = 0;
		String[] semeListiIsapped = {noList};
		if(creditList == null) {
			return null;
		}
		int[][] temp = new int[creditList.size()][2];
		
		for(int i = 0; i < creditList.size(); i++) {
			if(creditList.get(i).getStudentID() == studentID && creditList.get(i).isApplicationState()) {
				boolean judge = true;
				for(int j = 0; j < creditList.size(); j++) {
					if(temp[j][0] == creditList.get(i).getYear() && temp[j][1] == creditList.get(i).getSemester()) {
						judge = false;
					}
				}
				if(judge) {
					temp[count][0] = creditList.get(i).getYear();
					temp[count][1] = creditList.get(i).getSemester();
					count++;
				}
			}
		}
		
		if(count > 0) {
			semeListiIsapped = new String[count];
			for(int i = 0; i < count; i++) {
				semeListiIsapped[i] = String.format("%4s년%2s학기", temp[i][0], temp[i][1]);
			}
		}
		return semeListiIsapped;
	}
	
	public ArrayList<CompletedCredit> completedCreditListPrint(int studentID, int year, int semester) { // 학기별 이수한 학점 목록 출력
		int count = countStudnetTermCredit(studentID, year, semester);
		ArrayList<CompletedCredit> CCLP = new ArrayList<>();
		
		if(count > 0) {
			for(CompletedCredit one : creditList) {
				if(one.getStudentID() == studentID && one.getYear() == year && one.getSemester() == semester) {
					CCLP.add(one);
				}
			}
		}
		return CCLP;
	}
	
	public ArrayList<CompletedCredit> applicatedCreditListPrint(int studentID, int year, int semester) { // 이수한 학점 목록 중 신청한 학점 목록 출력
		int count = countStudentTermCreditIsapped(studentID, year, semester);
		ArrayList<CompletedCredit> ACLP = new ArrayList<>();
		
		if(count > 0) {
			for(CompletedCredit one : creditList) {
				if(one.getStudentID() == studentID && one.getYear() == year && one.getSemester() == semester && one.isApplicationState()) {
					ACLP.add(one);
				}
			}
		}
		return ACLP;
	}
	
	public static String getNoList() {
		return noList;
	}
}
