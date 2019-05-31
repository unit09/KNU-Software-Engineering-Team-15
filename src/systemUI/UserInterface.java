package systemUI;

import exchange.*;
import user.*;
import client.Client;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class UserInterface extends Observable {
    private JTabbedPane Tab = new JTabbedPane(JTabbedPane.LEFT);
    
    private static ArrayList<DispatchRecord> records;

    private CreditUI CreditViewIsapped;
    
    Student user;
    Administer admin;
    Guest guest;
    int userType;
    int year, month, date;
    
    public UserInterface(Student userinfo, Client clientpass) {
        super("교환학생 지원 프로그램");

        //client = new Client();
        client = clientpass;
        
        if(userinfo.getStudentID() == -1){
            admin = new Administer(userinfo.getName(), userinfo.getYear());
            userType = 1;
        }
        else if(userinfo.getStudentID() == -2){
            guest = new Guest();
            userType = 2;
        }
        else{
            user = userinfo;
            userType = 0;
        }

        //DB를 불러오는 부분
        mainList = (RecruitmentList)client.getObject("RecruitmentList");
        records = (ArrayList<DispatchRecord>)client.getObject("DispatchRecord");
        
        if(mainList == null) {
        	mainList = new RecruitmentList();
        }
        if(records == null) {
        	records = new ArrayList<DispatchRecord>();
        }
        
        
        //오늘 날짜를 불러와서 모집공고들의 상태를 바꿔주는 부분
        Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);
        mainList.setRecruitState(year, month, date);
        
        setLayout(null);

        Tab.setBounds(10, 10, 650, 480);

        Tab.addTab("메인", new Initial());
        
        RecruitLookUI rlUI = new RecruitLookUI(userType, mainList, user);
        addObserver(rlUI);
        Tab.addTab("모집공고 조회", rlUI);
        
        if(userType == 0) {
        	StateLookUI slUI = new StateLookUI(mainList, user, records, client);
        	addObserver(slUI);
            Tab.addTab("진행상황 조회", slUI);
            
    		try {
				Tab.addTab("이수학점 관리", new CreditUI(user.getStudentID(), this, false));
	    		CreditViewIsapped = new CreditUI(user.getStudentID(), this, true);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    		
    		Tab.addTab("등록학점 조회", CreditViewIsapped);
        }
        
        Tab.addTab("파견실적 조회", new DispatchUI(records));
        
        try {
			Tab.addTab("QNA 게시판", new QnAUI(userinfo.getStudentID(), client));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
        
        if(userType == 1) {
            Tab.addTab("모집공고 작성", new RecruitCreateUI(mainList, admin));
            
            RecruitDeleteUI rdUI = new RecruitDeleteUI(mainList);
            addObserver(rdUI);
            Tab.addTab("모집공고 삭제", rdUI);
        }
        
        add(Tab);
    }

    class Initial extends JPanel{
		private JLabel ment;

        public Initial(){            
            setLayout(null);
            JLabel intro;
            if(userType == 0){
                intro = new JLabel("어서오세요. " + user.getName() + "님       학번 : " + user.getStudentID());
            }
            else if(userType == 1){
                intro = new JLabel("어서오세요. " + admin.getName() + "님");
            }
            else{
                intro = new JLabel("손님 계정으로 입장하셨군요! 어서오세요 낯선이여");
            }
            intro.setBounds(20,140, 400, 20);
            add(intro);

            JLabel dateSet = new JLabel("오늘 날짜 : " + year +"년 "+ month + "월 " + date + "일");
            dateSet.setBounds(20, 220, 400, 20);
            add(dateSet);
         
            ment = new JLabel("원하시는 기능을 좌측 메뉴에서 선택해주세요.");
            ment.setBounds(20, 180, 300, 20);
            add(ment);
        }
    }
    
    
    
	public CreditUI getCreditViewIsapped() {
		return CreditViewIsapped;
	}
}