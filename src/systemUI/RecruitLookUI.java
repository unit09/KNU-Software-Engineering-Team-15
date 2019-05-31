package systemUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exchange.*;
import user.*;

public class RecruitLookUI extends JPanel implements Observer {
    private JButton select;
    private JPanel con;
    private JList list;

    public RecruitLookUI(int userType, RecruitmentList mainList, Student user){
    	list = new JList(mainList.printList());   //조회에 필요한 리스트
        list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(20);
        list.setFixedCellWidth(120);
        
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
                    con.add(mainList.printContents(index));
                    select = new JButton("응시원서 작성");

                    select.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	if(mainList.getRecruitState(index) == 0) {
                        		if (mainList.checkUser(index, user.getStudentID()) == false) {
                        			String[] buttons = {"신청", "취소"};
                    				int result = 0;
                    				result = JOptionPane.showOptionDialog(null, "신청하시겠습니까?", "응시원서 작성", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
                    				if(result == 0) {
	                                    Application newone = user.ApplicationCreate(mainList.getRecruitNum(index));
	                                    mainList.apply(index, newone);
	                                    Observable.uploadData();
	                                    Observable.notifyObservers();
	                                    
	                                    JOptionPane.showMessageDialog(null, "응시원서 작성이 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
                    				}
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
    
    public void update(RecruitmentList mainList) {
    	list.setListData(mainList.printList());
    }
}
