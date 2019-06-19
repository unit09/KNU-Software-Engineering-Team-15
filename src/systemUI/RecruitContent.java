package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import exchange.*;
import user.*;

public class RecruitContent extends JPanel {
	
	public RecruitContent(int userType, int index, Recruitment recruitment) {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setSize(400, 700);
		
		JLabel title = new JLabel(recruitment.getTitle());
		title.setForeground(Color.WHITE);
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		title.setBounds(14, 36, 372, 44);
		add(title);
		
		JLabel contents = new JLabel(recruitment.getContents());
		contents.setForeground(Color.WHITE);
		contents.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		contents.setBounds(103, 109, 283, 156);
		add(contents);
		
		JLabel nation = new JLabel("\uBAA8\uC9D1\uAD6D\uAC00");
		nation.setForeground(Color.WHITE);
		nation.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		nation.setBounds(103, 295, 71, 35);
		add(nation);
		
		JLabel label1 = new JLabel(recruitment.getNation());
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label1.setBounds(204, 295, 182, 35);
		add(label1);
		
		JLabel univ = new JLabel("\uBAA8\uC9D1\uB300\uD559");
		univ.setForeground(Color.WHITE);
		univ.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		univ.setBounds(103, 348, 71, 35);
		add(univ);
		
		JLabel label2 = new JLabel(" ");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label2.setBounds(204, 348, 182, 35);
		add(label2);
		
		JLabel major = new JLabel("\uC804\uACF5");
		major.setForeground(Color.WHITE);
		major.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		major.setBounds(103, 401, 71, 35);
		add(major);
		
		JLabel label3 = new JLabel(" ");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label3.setBounds(204, 401, 182, 35);
		add(label3);
		
		JLabel start = new JLabel("\uC2DC\uC791\uD559\uAE30");
		start.setForeground(Color.WHITE);
		start.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		start.setBounds(103, 453, 71, 35);
		add(start);
		
		JLabel label4 = new JLabel(" ");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label4.setBounds(204, 453, 182, 35);
		add(label4);
		
		JLabel period = new JLabel("\uD30C\uACAC\uAE30\uAC04");
		period.setForeground(Color.WHITE);
		period.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		period.setBounds(103, 503, 71, 35);
		add(period);
		
		JLabel label5 = new JLabel(" ");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label5.setBounds(204, 503, 182, 35);
		add(label5);
		
		JLabel deadline = new JLabel("\uBAA8\uC9D1\uAE30\uAC04");
		deadline.setForeground(Color.WHITE);
		deadline.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		deadline.setBounds(103, 550, 71, 35);
		add(deadline);
		
		JLabel label6 = new JLabel(" ");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label6.setBounds(204, 550, 182, 35);
		add(label6);
		
		JLabel select = new JLabel("\uB4F1\uB85D\uB9C8\uAC10");
		select.setForeground(Color.WHITE);
		select.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		select.setBounds(103, 597, 71, 35);
		add(select);
		
		JLabel label7 = new JLabel(" ");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label7.setBounds(204, 597, 182, 35);
		add(label7);
		
		JLabel caution = new JLabel("\uBC1C\uD45C \uD6C4 \uBC18\uB4DC\uC2DC \uC9C4\uD589\uC0C1\uD669\uC744 \uD655\uC778\uD558\uC5EC \uCD5C\uC885\uB4F1\uB85D\uD574\uC8FC\uC2DC\uAE30 \uBC14\uB78D\uB2C8\uB2E4.");
		caution.setForeground(Color.WHITE);
		caution.setFont(new Font("¸¼Àº °íµñ Semilight", Font.PLAIN, 12));
		caution.setBounds(41, 644, 345, 35);
		add(caution);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(RecruitContent.class.getResource("/systemUI/image/\uBAA8\uC9D1\uBC30\uACBD.png")));
		background.setBounds(0, 0, 400, 700);
		add(background);
		
		
	}
}
