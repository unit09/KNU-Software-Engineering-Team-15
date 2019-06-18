package creditManagement;

import client.Client;

public class CompletedCreditInstanceMaker {
//초기화 -> 학점인정 신청(선택적) -> 저장 순서
	private static CompletedCreditList CCList;
	private static String[] nation_list = {"미국", "영국", "중국", "일본"};
	private static String[][] univ_list = {{"하버드", "MIT"}, {"옥스포드"}, {"산둥"}, {"메이지", "교토", "와세다"}};
	private static String[] dept_list = {"컴퓨터", "전자"};
	private static String[][] course_list
	= {{"컴퓨터학개론", "컴퓨터윤리", "이산수학", "기초물리학실험", "기초공학설계", "기초프로그래밍", "공학수학", "자료구조", "자료구조응용", "자바프로그래밍", "오토마타", "논리회로"},
		{"기초전자수학", "기초전자물리학", "기초전자실험설계", "전자회로설계", "논리회로설계"}};
	private static int[] acc_credit_list = {2, 3, 4};
	private static double[] grade_list = {1.7, 2.0, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0, 4.3};
	
	public static void main(String[] args) throws ClassNotFoundException {
		Client client = new Client();
		CCList = CompletedCreditList.get_completed_credit_list(client);
		
		
		do_initial(client, 2015123456, 2018, 1, "미국", "하버드", "컴퓨터", "컴퓨터학개론", 3, 3.7, false);
		do_initial(client, 2015123456, 2018, 1, "미국", "하버드", "컴퓨터", "컴퓨터윤리", 2, 3.3, false);
		do_initial(client, 2015123456, 2018, 1, "미국", "하버드", "컴퓨터", "이산수학", 2, 4.0, false);
		do_initial(client, 2015123456, 2018, 1, "미국", "하버드", "컴퓨터", "기초물리학실험", 3, 3.3, false);
		do_initial(client, 2015123456, 2018, 1, "미국", "하버드", "컴퓨터", "기초공학설계", 3, 2.7, false);
		do_initial(client, 2015123456, 2018, 1, "미국", "하버드", "컴퓨터", "기초프로그래밍", 3, 3.0, false);

		do_initial(client, 2015123456, 2018, 2, "미국", "하버드", "컴퓨터", "공학수학", 2, 3.7, false);
		do_initial(client, 2015123456, 2018, 2, "미국", "하버드", "컴퓨터", "자료구조", 3, 3.7, false);
		do_initial(client, 2015123456, 2018, 2, "미국", "하버드", "컴퓨터", "자료구조응용", 3, 4.0, false);
		do_initial(client, 2015123456, 2018, 2, "미국", "하버드", "컴퓨터", "자바프로그래밍", 3, 3.3, false);
		do_initial(client, 2015123456, 2018, 2, "미국", "하버드", "컴퓨터", "오토마타", 2, 2.3, false);
		do_initial(client, 2015123456, 2018, 2, "미국", "하버드", "컴퓨터", "논리회로", 2, 3.0, false);

		do_initial(client, 2015123456, 2019, 1, "영국", "옥스포드", "전자", "기초전자수학", 2, 4.0, false);
		do_initial(client, 2015123456, 2019, 1, "영국", "옥스포드", "전자", "기초전자물리학", 2, 4.3, false);
		do_initial(client, 2015123456, 2019, 1, "영국", "옥스포드", "전자", "기초전자실험설계", 3, 4.0, false);
		do_initial(client, 2015123456, 2019, 1, "영국", "옥스포드", "전자", "전자회로설계", 3, 4.0, false);
		do_initial(client, 2015123456, 2019, 1, "영국", "옥스포드", "전자", "논리회로설계", 3, 3.7, false);
		
		random_initial(client, 2015111111, 2018, 1);
		random_initial(client, 2015111111, 2018, 2);
		random_initial(client, 2015111111, 2019, 1);
	}
	
	public static void list_add(Client client, CompletedCredit CC) {
		boolean ismod = false;
		int size = 0;
		if(CCList.get_instance_list() != null) {
			size = CCList.get_instance_list().size();
		}
		else {
			try {
				CCList.completed_credit_list_append(client, CC);
			} catch (ClassNotFoundException e) {}
		}
		for(int i = 0; i < size ; i++) {
			try {
				if(CCList.completed_credit_list_modify(client, CC)) {
					ismod = true;
					break;
				}
			} catch (ClassNotFoundException e) {}
		}
		if(!ismod) {
			try {
				CCList.completed_credit_list_append(client, CC);
			} catch (ClassNotFoundException e) {}
		}
		//command line에서 append된 것 확인 용도
		System.out.printf("%d|%d|%d|%s|%4s|%4s|%10s|%d|%.1f|%b|%b\n",CC.getSt_id(),CC.getYear(),CC.getSemester(),CC.getNation(),CC.getUniv(),CC.getDept(),CC.getCourse(),CC.getAccept_credit(),CC.getGrade(),CC.isApplication_state(),ismod);
	}
	
	public static void do_initial(Client client, int st_id, int year, int semester, String nation, String univ, String dept, String course, int acc_credit, double grade, boolean isapped) {
		CompletedCredit s1;
		
		s1 = new CompletedCredit();
		s1.setSt_id(st_id);
		s1.setYear(year);
		s1.setSemester(semester);
		s1.setNation(nation);
		s1.setUniv(univ);
		s1.setDept(dept);
		s1.setCourse(course);
		s1.setAccept_credit(acc_credit);
		s1.setGrade(grade);
		s1.setApplication_state(isapped);
		list_add(client, s1);
		
	}
	
	public static void random_initial(Client client, int id, int year, int semester) {
		int nation_index = (int) (Math.random() * nation_list.length);
		int univ_index = (int) (Math.random() * univ_list[nation_index].length);
		int dept_index = (int) (Math.random() * dept_list.length);
		for(int i = 0; i < 6; i++) {
			int course_index = (int) (Math.random() * course_list[dept_index].length);
			int acc_credit_index = (int) (Math.random() * acc_credit_list.length);
			int grade_index = (int) (Math.random() * grade_list.length);
			
			do_initial(client, id, year, semester, nation_list[nation_index], univ_list[nation_index][univ_index], dept_list[dept_index], course_list[dept_index][course_index], acc_credit_list[acc_credit_index], grade_list[grade_index], false);
		}
	}
}
