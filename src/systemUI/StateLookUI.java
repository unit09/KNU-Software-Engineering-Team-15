package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import client.Client;
import exchange.*;
import user.Student;

public class StateLookUI extends JPanel {
    private JButton del;
    private JButton sel;
    private JList list;
    private Student man;

    public StateLookUI(RecruitmentList mainList, Student user, ArrayList<DispatchRecord> records, Client client){
    	list = new JList(mainList.printState(user.getStudentID()));  //진행상황에 필요한 리스트
        list.setVisibleRowCount(19);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(19);
        list.setFixedCellWidth(500);
    	
        man = user;
        
        setLayout(new FlowLayout());
        setSize(500, 400);
        
        add(new JScrollPane(list));

        sel = new JButton("최종 등록");
        sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!list.isSelectionEmpty()) {
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
        });
        add(sel);

        del = new JButton("응시 취소");
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!list.isSelectionEmpty()) {
            		String buf = (String)list.getSelectedValue();
            		int in = buf.indexOf(".");
            		buf = buf.substring(0, in);
            		mainList.deleteAplication(Integer.parseInt(buf), user.getStudentID());
            		Observable.notifyObservers();
            		list.setListData(mainList.printState(man.getStudentID()));
            		JOptionPane.showMessageDialog(null, "취소되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
            	}
            }
        });
        add(del);
    }
}
