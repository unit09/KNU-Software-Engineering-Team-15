package systemUI;

import java.awt.FlowLayout;
import java.util.*;
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
	private ArrayList<Integer> year = new ArrayList<Integer>(); 
	//JTable용으로 연도와 학기, 기간에 대한 list가 필요한가?
	//여기가 아니라 밑에서 1회용으로 선언해야될지도
	
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
		//검색 버튼을 누르면 파견실적을 읽으면서 검색 조건에 해당하는 파견실적을 읽고 그에 맞는 JTable을 그린다
		//그려놓은 표는 새로 검색을 누를때까지 남아있고 새로 검색을 누르면 기존의 표를 삭제하고 다시 그린다
		//Collections.sort(year);
		
		add(nations);
		add(unis);
		add(majors);
		
		add(search);
		
		
	}
}
