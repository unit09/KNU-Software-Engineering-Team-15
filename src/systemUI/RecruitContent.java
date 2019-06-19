package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import exchange.*;
import user.*;

public class RecruitContent extends JPanel {
	
	public RecruitContent(int userType, int index, Recruitment recruitment) {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(614, 547);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(262, 517, 62, 18);
		add(lblNewLabel);
		
		
	}
}
