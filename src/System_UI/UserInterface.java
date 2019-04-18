package System_UI;

import User.*;
import Exchange.*;
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

	private credit_UI CreditViewIsapped;
	
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
        Tab.addTab("모집공고 조회", new RecruitLook());
        if(userType == 0) {
            Tab.addTab("진행상황 조회", new StateLook());
    		try {
				Tab.addTab("이수학점 관리", new credit_UI(user.getStudentID(), this, false));
	    		CreditViewIsapped = new credit_UI(user.getStudentID(), this, true);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    		Tab.addTab("등록학점 조회", CreditViewIsapped);
        }
        Tab.addTab("파견실적 조회", new JPanel());    //미구현
        try {
			Tab.addTab("QNA 게시판", new QnA_UI(userinfo.getStudentID()));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
        if(userType == 1) {
            Tab.addTab("모집공고 작성", new RecruitCreate());
            Tab.addTab("모집공고 삭제", new RecruitDelete());
        }

        Tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                list.clearSelection();
                if(userType == 0)
                    list3.clearSelection();
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
    class RecruitLook extends JPanel{
        private JButton select;
        private JPanel con;

        public RecruitLook(){
            setLayout(new FlowLayout());
            setSize(500, 400);

            add(new JScrollPane(list));

            con = new JPanel();
            con.setPreferredSize(new Dimension(380,450));
            con.setBackground(Color.WHITE);
            add(con);

            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int index = list.getSelectedIndex();
                    con.removeAll();
                    if(index >= 0) {
                        con.add(sampleList.printContents(index));
                        select = new JButton("응시원서 작성");

                        select.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            	if(sampleList.getRecruitState(index) == 0) {
                            		if (sampleList.checkUser(index, user.getStudentID()) == false) {
                                        Application newone = user.ApplicationCreate(sampleList.getRecruitNum(index));
                                        sampleList.apply(index, newone);
                                        list3.setListData(sampleList.printState(user.getStudentID()));
                                        JOptionPane.showMessageDialog(null, "응시원서 작성이 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                                    } else
                                        JOptionPane.showMessageDialog(null, "이미 응시한 모집공고입니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                            	}
                            	else {
                            		JOptionPane.showMessageDialog(null, "신청할 수 없는 상태입니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                            	}
                            }
                        });
                        if(userType == 0)
                            con.add(select);
                    }
                    con.revalidate();
                    con.repaint();
                }
            });
        }
    }
    class StateLook extends JPanel{
        private JButton del;
        private JButton sel;

        public StateLook(){
            setLayout(new FlowLayout());
            setSize(500, 400);

            add(new JScrollPane(list3));

            sel = new JButton("최종 등록");
            sel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String test = (String)list3.getSelectedValue();
                    int in = test.indexOf(".");
                    test = test.substring(0, in);
                    sampleList.choiceYo(Integer.parseInt(test), user.getStudentID());
                    list3.setListData(sampleList.printState(user.getStudentID()));
                }
            });
            add(sel);

            del = new JButton("응시 취소");
            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String test = (String)list3.getSelectedValue();
                    int in = test.indexOf(".");
                    test = test.substring(0, in);
                    sampleList.deleteAplication(Integer.parseInt(test), user.getStudentID());
                    list3.setListData(sampleList.printState(user.getStudentID()));
                    JOptionPane.showMessageDialog(null, "취소되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                }
            });
            add(del);
        }
    }
    class RecruitCreate extends JPanel {
        private JTextField[] format = new JTextField[10];
        private JTextArea contents = new JTextArea();
        private JButton confirm;

        public RecruitCreate() {
            setLayout(new FlowLayout());
            setSize(500, 400);

            format[0] = new JTextField("Number of Recruitment (ex. 1)", 44);
            format[1] = new JTextField("Title", 44);
            format[2] = new JTextField("Nation", 44);
            format[3] = new JTextField("University", 44);
            format[4] = new JTextField("Major", 44);
            format[5] = new JTextField("Start Year", 44);
            format[6] = new JTextField("Start Semester", 44);
            format[7] = new JTextField("Period", 44);
            format[8] = new JTextField("Deadline (ex. 20180908)", 44);
            format[9] = new JTextField("Resisteration Deadline (ex. 20181010)", 44);

            contents = new JTextArea("Contents");
            contents.setColumns(44);
            contents.setRows(8);

            confirm = new JButton("작성완료");
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sampleList.checkList(Integer.parseInt(format[0].getText())) == false) {
                        Recruitment newone = admin.createRecruitment(Integer.parseInt(format[0].getText()), format[1].getText(), contents.getText(), Integer.parseInt(format[8].getText()),
                                Integer.parseInt(format[9].getText()), Integer.parseInt(format[5].getText()), Integer.parseInt(format[6].getText()), Integer.parseInt(format[7].getText()),
                                format[2].getText(), format[3].getText(), format[4].getText());
                        sampleList.addList(newone);
                        list.setListData(sampleList.printList());
                        list2.setListData(sampleList.printList2());
                        JOptionPane.showMessageDialog(null, "모집공고 작성이 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "같은 번호의 모집공고가 이미 존재합니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                }
            });

            for (int i = 0; i < 10; i++) {
                add(format[i]);
            }

            add(contents);
            add(confirm);
        }
    }
    class RecruitDelete extends JPanel {
        private JButton delet;

        public RecruitDelete() {
            setLayout(new FlowLayout());
            setSize(500, 400);

            add(new JScrollPane(list2));

            delet = new JButton("삭제");
            delet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = list2.getSelectedIndex();
                    if (index == -1)
                        JOptionPane.showMessageDialog(null, "삭제할 모집공고를 선택하세요.", "알림", JOptionPane.PLAIN_MESSAGE);
                    else{
                        sampleList.deleteList(index);
                        list.setListData(sampleList.printList());
                        list2.setListData(sampleList.printList2());
                        JOptionPane.showMessageDialog(null, "모집공고가 삭제되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });

            add(delet);
        }
    }
	public credit_UI getCreditViewIsapped() {
		return CreditViewIsapped;
	}
}