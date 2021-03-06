package systemUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.Random;
import exchange.*;
import systemUI.Observable;

public class DispatchUI extends JFrame {
	private JComboBox nations;
	private JComboBox unis;
	private JComboBox majors;
	private JComboBox periods;
	private JButton search;
	
	private ArrayList<String> nation;
	private ArrayList<String> university;
	private ArrayList<String> major;
	private ArrayList<String> period;
	
	private int[] numbers;
	private DrawingPanel graph;
	
	private static final String TOOLTIP = "선택하지 않을 경우 전체 범위로 검색합니다.";
	private static final String DURINGPERIODS = "학기 동안";

	public DispatchUI(ArrayList<DispatchRecord> records) {
		super("파견실적 조회");
		setLayout(null);
		setSize(800, 620);
		
		nation = new ArrayList<String>();
		university = new ArrayList<String>();
		major = new ArrayList<String>();
		period = new ArrayList<String>();
		
		numbers = new int[6];
		
		nation.add("국가 선택");
		university.add("대학 선택");
		major.add("전공 선택");
		period.add("기간 선택");	
		
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
			if(!period.contains(Integer.toString(record.getPeriod()) + DURINGPERIODS)) {
				period.add(Integer.toString(record.getPeriod()) + DURINGPERIODS);
			}
		}
		
		nations = new JComboBox(nation.toArray());
		nations.setToolTipText(TOOLTIP);
		nations.setBounds(93, 25, 117, 36);
		add(nations);
		
		unis = new JComboBox(university.toArray());
		unis.setToolTipText(TOOLTIP);
		unis.setBounds(224, 25, 117, 36);
		add(unis);
		
		majors = new JComboBox(major.toArray());
		majors.setToolTipText(TOOLTIP);
		majors.setBounds(355, 25, 117, 36);
		add(majors);
		
		periods = new JComboBox(period.toArray());
		periods.setToolTipText(TOOLTIP);
		periods.setBounds(486, 25, 117, 36);
		add(periods);
		
		graph = new DrawingPanel();
		graph.setBounds(50, 111, 700, 453);
		add(graph);
		
		search = new JButton("검색");
		search.setBounds(617, 25, 92, 36);
		search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		for(int i = 0; i < 6; i++) {
        			numbers[i] = 0;
        		}        		
        		for(int i = 0; i < records.size(); i++) {
        			DispatchRecord record = records.get(i);
        			if(nations.getSelectedItem().equals(record.getNation()) || nations.getSelectedIndex() == 0) {
        				if(unis.getSelectedItem().equals(record.getUniversity()) || unis.getSelectedIndex() == 0) {
        					if(majors.getSelectedItem().equals(record.getMajor()) || majors.getSelectedIndex() == 0) {
        						if(periods.getSelectedItem().equals(Integer.toString(record.getPeriod()) + DURINGPERIODS) || periods.getSelectedIndex() == 0) {
        							numbers[record.getStartYear() - 2014] += 1;
        						}
        					}
        				}
        			}
        		}
        		graph.setList(numbers);
        		graph.repaint();
        	}
        });
		add(search);
				
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(DispatchUI.class.getResource("/systemUI/image/chart.jpg")));
		background.setBounds(0, 0, 800, 620);
		add(background);
		
	}
}
