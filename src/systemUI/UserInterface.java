package systemUI;

import exchange.*;
import user.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.Calendar;

public class UserInterface extends JFrame {
    private JList list;
    private JList list2;
    private JList list3;
    private JTabbedPane Tab = new JTabbedPane(JTabbedPane.LEFT);

	private CreditUI CreditViewIsapped;
	
    private static RecruitmentList sampleList;
    private static File fp = new File("database/rerucitment/Rerucitment DB.txt");

    Student user;
    Administer admin;
    Guest guest;
    int userType;
    int year, month, date;
    
    
    public UserInterface(Student userinfo) {
        super("교환학생 프로그램");

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
        if(fp.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(fp);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    sampleList = (RecruitmentList) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("파일입출력 에러");
                System.exit(1);
            }
        }
        else{
            sampleList = new RecruitmentList();
        }
        
        //오늘 날짜를 불러와서 모집공고들의 상태를 바꿔주는 부분
        Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);
        
        sampleList.setRecruitState(year, month, date);
        

        setLayout(null);

        Tab.setBounds(10, 10, 650, 480);

        Tab.addTab("메인", new Initial());
        Tab.addTab("모집공고 조회", new RecruitLook(userType, sampleList, user, list));
        if(userType == 0) {
            Tab.addTab("진행상황 조회", new StateLook(sampleList, user, list3));
    		try {
				Tab.addTab("이수학점 관리", new CreditUI(user.getStudentID(), this, false));
	    		CreditViewIsapped = new CreditUI(user.getStudentID(), this, true);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    		Tab.addTab("등록학점 조회", CreditViewIsapped);
        }
        Tab.addTab("파견실적 조회", new JPanel());    //미구현
        try {
			Tab.addTab("QNA 게시판", new QnAUI(userinfo.getStudentID()));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
        if(userType == 1) {
            Tab.addTab("모집공고 작성", new RecruitCreate(sampleList, admin, list2));
            Tab.addTab("모집공고 삭제", new RecruitDelete(sampleList, list2));
        }

        Tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	list.clearSelection();
            	list.setListData(sampleList.printList());
                if(userType == 0) {
                    list3.clearSelection();
                    list3.setListData(sampleList.printState(user.getStudentID()));
                }
            }
        });

        add(Tab);

        //종료될때 데이터베이스로 업로드하는 부분
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream fos = new FileOutputStream(fp);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    try{
                        oos.writeObject(sampleList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }catch (Exception ex){
                    System.out.println("파일입출력 에러");
                    System.exit(1);
                }
            }
        });
    }

    class Initial extends JPanel{
		private JLabel ment;

        public Initial(){
        	list = new JList(sampleList.printList());   //조회에 필요한 리스트
            list.setVisibleRowCount(20);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setFixedCellHeight(20);
            list.setFixedCellWidth(120);
            
            list2 = new JList(sampleList.printList2());  //삭제에 필요한 리스트
            list2.setVisibleRowCount(20);
            list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list2.setFixedCellHeight(20);
            list2.setFixedCellWidth(300);

            if(userType == 0) {
                list3 = new JList(sampleList.printState(user.getStudentID()));  //진행상황에 필요한 리스트
                list3.setVisibleRowCount(19);
                list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list3.setFixedCellHeight(19);
                list3.setFixedCellWidth(500);
            }

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