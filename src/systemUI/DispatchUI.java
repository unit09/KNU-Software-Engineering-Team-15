package systemUI;

import java.awt.FlowLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import client.Client;
import exchange.*;

public class DispatchUI extends JPanel {
	private JComboBox nations;
	private JComboBox unis;
	private JComboBox majors;
	private JButton search;
	private DefaultTableModel model;
	
	private ArrayList<String> nation = new ArrayList<String>();
	private ArrayList<String> university = new ArrayList<String>();
	private ArrayList<String> major = new ArrayList<String>();
	private ArrayList<Integer> year = new ArrayList<Integer>();
	
	//JTable용으로 연도와 학기, 기간에 대한 list가 필요한가?
	//여기가 아니라 밑에서 1회용으로 선언해야될지도
	
	public DispatchUI(ArrayList<DispatchRecord> records) {
		setLayout(null);
		setSize(700, 540);
		
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
		nations.setBounds(379, 28, 117, 36);
		add(nations);
		
		unis = new JComboBox(university.toArray());
		unis.setBounds(117, 28, 117, 36);
		add(unis);
		
		majors = new JComboBox(major.toArray());
		majors.setBounds(248, 28, 117, 36);
		add(majors);
		
		search = new JButton("검색");
		search.setBounds(510, 28, 61, 36);
		add(search);
		
		String[][] test = { {"test", "", ""}, {"test2", "", ""} };
		model = new DefaultTableModel(test, major.toArray());
		
		//지원 현황과 선발 현황으로 나눠서 표시?
		
		//검색 버튼을 누르면 파견실적을 읽으면서 검색 조건에 해당하는 파견실적을 읽고 그에 맞는 JTable을 그린다
		//그려놓은 표는 새로 검색을 누를때까지 남아있고 새로 검색을 누르면 기존의 표를 삭제하고 다시 그린다
		//Collections.sort(year);
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(DispatchUI.class.getResource("/systemUI/image/graph.jpg")));
		background.setBounds(0, 0, 700, 540);
		add(background);
		
		
	}
}
