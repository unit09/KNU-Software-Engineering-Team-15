package systemUI;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;
import exchange.*;

public class DispatchUI extends JPanel {
	private JComboBox nations;
	private JComboBox unis;
	private JComboBox majors;
	private JButton search;
	private JTable table;
	
	private ArrayList<String> nation = new ArrayList<String>();
	private ArrayList<String> university = new ArrayList<String>();
	private ArrayList<String> major = new ArrayList<String>();
	//JTable용으로 연도와 학기, 기간에 대한 list가 필요한가?
	
	public DispatchUI(ArrayList<DispatchRecord> records) {
		setLayout(new FlowLayout());
		setSize(500, 400);
		
		nation.add("국가 선택");
		university.add("대학 선택");
		major.add("전공 선택");
		
		for(int i = 0; i < records.size(); i++) {
			DispatchRecord record = records.get(i);
			if(!nation.contains(record.getNation())) {
				nation.add(record.getNation());
			}
			if(!university.contains(record.getUniversity())) {
				university.add(record.getUniversity());
			}
			if(!major.contains(record.getMajor())) {
				major.add(record.getMajor());
			}
		}
		
		nations = new JComboBox(nation.toArray());
		unis = new JComboBox(university.toArray());
		majors = new JComboBox(major.toArray());
		
		search = new JButton("검색");
		
		add(nations);
		add(unis);
		add(majors);
		
		add(search);
		
		
	}
}
