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

public class StateLookUI extends JFrame implements Observer {
    private JButton del;
    private JButton sel;
    private JList list;
    private Student man;

    public StateLookUI(RecruitmentList mainList, Student user, ArrayList<DispatchRecord> records, Client client){
    	man = user;
        
        getContentPane().setLayout(null);
        setSize(800, 620);
        
        JLabel title = new JLabel("\uC9C4\uD589 \uC0C1\uD669 \uC870\uD68C");
        Color temp = new Color(240, 240, 240, 80);
        title.setOpaque(true);
        title.setBackground(temp);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        title.setBounds(317, 22, 148, 39);
        getContentPane().add(title);
        
        JList list_1 = new JList();
        list_1.setBounds(85, 118, 612, 132);
        getContentPane().add(list_1);
        
        JLabel label = new JLabel("\uBAA8\uC9D1\uC911");
        label.setOpaque(true);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label.setBackground(new Color(240, 240, 240, 80));
        label.setBounds(98, 78, 60, 34);
        getContentPane().add(label);
        
        JList list_2 = new JList();
        list_2.setBounds(85, 315, 612, 132);
        getContentPane().add(list_2);
        
        JLabel label_1 = new JLabel("\uC2EC\uC0AC\uC911 / \uC2EC\uC0AC\uC644\uB8CC");
        label_1.setOpaque(true);
        label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label_1.setBackground(new Color(240, 240, 240, 80));
        label_1.setBounds(98, 276, 169, 34);
        getContentPane().add(label_1);
        
        JButton btnNewButton = new JButton("\uCD5C\uC885 \uB4F1\uB85D");
        btnNewButton.setBounds(267, 490, 105, 27);
        getContentPane().add(btnNewButton);
        
        JButton button = new JButton("\uC751\uC2DC \uCDE8\uC18C");
        button.setBounds(399, 490, 105, 27);
        getContentPane().add(button);
        
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon(StateLookUI.class.getResource("/systemUI/image/time.jpg")));
        background.setHorizontalAlignment(SwingConstants.CENTER);
        background.setBounds(0, 0, 800, 620);
        getContentPane().add(background);
        
        
        
        
        /*list = new JList(mainList.printState(user.getStudentID()));  //진행상황에 필요한 리스트
        list.setBounds(0, 285, 335, -285);
        add(list);
        list.setVisibleRowCount(19);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(19);
        list.setFixedCellWidth(500);*/

        /*sel = new JButton("최종 등록");
        sel.addActionListener(new ActionListener() {
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
        add(sel);

        del = new JButton("응시 취소");
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!list.isSelectionEmpty()) {
            		String[] buttons = {"응시 취소", "취소"};
    				int result = 0;
    				result = JOptionPane.showOptionDialog(null, "응시된 원서를 취소하시겠습니까?", "응시 취소", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "취소");
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
        add(del);*/
    }
    
    public void update(RecruitmentList mainList) {
    	list.setListData(mainList.printState(man.getStudentID()));
    }
}
