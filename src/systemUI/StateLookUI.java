package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import client.Client;
import exchange.*;
import user.Student;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class StateLookUI extends JFrame implements Observer {
    private JButton del;
    private JButton sel;
    private JList list;
    private Student man;
    
    private static final String CANCEL = "응시 취소";
    private static final String FONT1 = "맑은 고딕";

    public StateLookUI(RecruitmentList mainList, Student user, ArrayList<DispatchRecord> records, Client client){
    	man = user;
        
        getContentPane().setLayout(null);
        setSize(800, 620);
        
        JLabel title = new JLabel("\uC9C4\uD589 \uC0C1\uD669 \uC870\uD68C");
        Color temp = new Color(240, 240, 240, 80);
        title.setOpaque(true);
        title.setBackground(temp);
        title.setFont(new Font(FONT1, Font.BOLD, 22));
        title.setBounds(317, 22, 148, 39);
        getContentPane().add(title);
        
        list = new JList(mainList.printState(user.getStudentID()));
        list.setFont(new Font(FONT1, Font.PLAIN, 18));
        list.setBounds(85, 125, 612, 377);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().add(list);
        
        JLabel label = new JLabel("\uC9C0\uC6D0\uD55C \uBAA8\uC9D1 \uACF5\uACE0 \uBAA9\uB85D");
        label.setOpaque(true);
        label.setFont(new Font(FONT1, Font.BOLD, 20));
        label.setBackground(new Color(240, 240, 240, 80));
        label.setBounds(98, 85, 207, 34);
        getContentPane().add(label);
        
        JButton select = new JButton("\uCD5C\uC885 \uB4F1\uB85D");
        select.setBounds(267, 514, 105, 27);
        getContentPane().add(select);
        
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!list.isSelectionEmpty()) {
            		String[] buttons = {"최종 등록", "취소"};
    				int result = 0;
    				result = JOptionPane.showOptionDialog(null, "최종 등록을 하시겠습니까?", "합격 원서 최종 등록", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
    				if(result == 0) {
	            		String buf = (String)list.getSelectedValue();
	            		int in = buf.indexOf(".");
	            		buf = buf.substring(0, in);
	            		int index = mainList.finalChoice(Integer.parseInt(buf), man.getStudentID());
	            		Observable.uploadData();
	            		Observable.notifyObservers();
	            		list.setListData(mainList.printState(man.getStudentID()));
	            		
	            		DispatchRecord newone = new DispatchRecord(mainList.getNation(index), mainList.getUniv(index), mainList.getYear(index), mainList.getSemester(index), mainList.getPeriod(index), mainList.getMajor(index));
	            		records.add(newone); 
	            		client.setObject("DispatchRecord", records);
    				}
            	}
            }
        });
        
        JButton cancel = new JButton(CANCEL);
        cancel.setBounds(399, 514, 105, 27);
        getContentPane().add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!list.isSelectionEmpty()) {
            		String[] buttons = {CANCEL, "취소"};
    				int result = 0;
    				result = JOptionPane.showOptionDialog(null, "응시된 원서를 취소하시겠습니까?", CANCEL, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
    				if(result == 0) {
	            		String buf = (String)list.getSelectedValue();
	            		int in = buf.indexOf(".");
	            		buf = buf.substring(0, in);
	            		mainList.deleteAplication(Integer.parseInt(buf), user.getStudentID());
	            		Observable.notifyObservers();
	            		list.setListData(mainList.printState(man.getStudentID()));
	            		JOptionPane.showMessageDialog(null, "취소되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
    				}
            	}
            }
        });
        
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon(StateLookUI.class.getResource("/systemUI/image/time.jpg")));
        background.setHorizontalAlignment(SwingConstants.CENTER);
        background.setBounds(0, 0, 800, 620);
        getContentPane().add(background);
        
    }
    
    public void update(RecruitmentList mainList) {
    	list.setListData(mainList.printState(man.getStudentID()));
    }
}
