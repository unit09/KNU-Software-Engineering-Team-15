package systemUI;

import exchange.*;
import user.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class UserInterface extends Observable {
    private JList list;
    private JList list2;
    private JList list3;
    private JTabbedPane Tab = new JTabbedPane(JTabbedPane.LEFT);

	private CreditUI CreditViewIsapped;
	
    private static ArrayList<DispatchRecord> record;
    private static File fp = new File("database/rerucitment/Rerucitment DB.txt");
    private static File fp2 = new File("database/dispatch_record/Dispatch Record.txt");

    Student user;
    Administer admin;
    Guest guest;
    int userType;
    int year, month, date;
    
    public UserInterface(Student userinfo) {
        super("교환학생 지원 프로그램");

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

        //DB를 불러오는 부분 - 나중에 사라질 예정
        if(fp.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(fp);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    mainList = (RecruitmentList) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("파일입출력 에러");
                System.exit(1);
            }
        }
        else{
            mainList = new RecruitmentList();
        }
        
        if(fp2.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(fp2);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    record = (ArrayList<DispatchRecord>) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("fp2 로드 에러");
                System.exit(1);
            }
        }
        else{
        	record = new ArrayList<DispatchRecord>();
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
            Tab.addTab("진행상황 조회", new StateLookUI(mainList, user, record));
    		try {
				Tab.addTab("이수학점 관리", new CreditUI(user.getStudentID(), this, false));
	    		CreditViewIsapped = new CreditUI(user.getStudentID(), this, true);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    		Tab.addTab("등록학점 조회", CreditViewIsapped);
        }
        
        Tab.addTab("파견실적 조회", new DispatchUI(record));
        
        try {
			Tab.addTab("QNA 게시판", new QnAUI(userinfo.getStudentID()));
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

        //종료될때 데이터베이스로 업로드하는 부분 - 나중에 사라질 예정
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream fos = new FileOutputStream(fp);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    try{
                        oos.writeObject(mainList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }catch (Exception ex){
                    System.out.println("파일입출력 에러");
                    System.exit(1);
                }
                
                if(!record.isEmpty()) {
                	try {
                		FileOutputStream fos = new FileOutputStream(fp2);
                		ObjectOutputStream oos = new ObjectOutputStream(fos);
                		try{
                			oos.writeObject(record);
                		}catch (Exception ex){
                			ex.printStackTrace();
                		}
                	}catch (Exception ex){
                			System.out.println(ex);
                			System.exit(1);
                	}
                }
            }
        });
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

            JLabel dateYo = new JLabel("오늘 날짜 : " + year +"년 "+ month + "월 " + date + "일");
            dateYo.setBounds(20, 220, 400, 20);
            add(dateYo);
         
            ment = new JLabel("원하시는 기능을 좌측 메뉴에서 선택해주세요.");
            ment.setBounds(20, 180, 300, 20);
            add(ment);
        }
    }
    
	public CreditUI getCreditViewIsapped() {
		return CreditViewIsapped;
	}
}