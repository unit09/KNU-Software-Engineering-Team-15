package systemUI;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;
import exchange.*;

public class DispatchUI extends JPanel {
	JComboBox test;
	ArrayList<String> nation = new ArrayList<String>();
	ArrayList<String> university = new ArrayList<String>();;
	ArrayList<String> major = new ArrayList<String>();;
	
	public DispatchUI(ArrayList<DispatchRecord> record) {
		setLayout(new FlowLayout());
		setSize(500, 400);
		
		nation.add("선택하지 않음");
		nation.add("천조국");
		test = new JComboBox(nation.toArray());
		
		add(test);
	}
}
