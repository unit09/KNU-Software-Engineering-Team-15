package systemUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import exchange.*;
import user.*;

public class RecruitContent extends JFrame {
	
	public RecruitContent(int userType, Recruitment recruitment, Student user) {
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setSize(400, 700);
		
		JLabel title = new JLabel(recruitment.getTitle());
		title.setForeground(Color.WHITE);
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		title.setBounds(14, 36, 372, 44);
		getContentPane().add(title);
		
		JTextArea textArea = new JTextArea(recruitment.getContents());
		textArea.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(null);
		Color temp = new Color(255, 255, 255, 0);
		textArea.setBackground(temp);
		textArea.setEditable(false);
		textArea.setBounds(103, 109, 265, 156);
		getContentPane().add(textArea);
		
		JLabel nation = new JLabel("\uBAA8\uC9D1\uAD6D\uAC00");
		nation.setForeground(Color.WHITE);
		nation.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		nation.setBounds(103, 295, 71, 35);
		getContentPane().add(nation);
		
		JLabel label1 = new JLabel(recruitment.getNation());
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label1.setBounds(204, 295, 182, 35);
		getContentPane().add(label1);
		
		JLabel univ = new JLabel("\uBAA8\uC9D1\uB300\uD559");
		univ.setForeground(Color.WHITE);
		univ.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		univ.setBounds(103, 348, 71, 35);
		getContentPane().add(univ);
		
		JLabel label2 = new JLabel(recruitment.getUniversity());
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label2.setBounds(204, 348, 182, 35);
		getContentPane().add(label2);
		
		JLabel major = new JLabel("\uC804\uACF5");
		major.setForeground(Color.WHITE);
		major.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		major.setBounds(103, 401, 71, 35);
		getContentPane().add(major);
		
		JLabel label3 = new JLabel(recruitment.getMajor());
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label3.setBounds(204, 401, 182, 35);
		getContentPane().add(label3);
		
		JLabel start = new JLabel("\uC2DC\uC791\uD559\uAE30");
		start.setForeground(Color.WHITE);
		start.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		start.setBounds(103, 453, 71, 35);
		getContentPane().add(start);
		
		JLabel label4 = new JLabel(recruitment.getStartYear() + "³â " + recruitment.getStartSemester() + "ÇÐ±âºÎÅÍ");
		label4.setForeground(Color.WHITE);
		label4.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 14));
		label4.setBounds(204, 453, 182, 35);
		getContentPane().add(label4);
		
		JLabel period = new JLabel("\uD30C\uACAC\uAE30\uAC04");
		period.setForeground(Color.WHITE);
		period.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		period.setBounds(103, 503, 71, 35);
		getContentPane().add(period);
		
		JLabel label5 = new JLabel(recruitment.getPeriod() + "ÇÐ±â µ¿¾È");
		label5.setForeground(Color.WHITE);
		label5.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		label5.setBounds(204, 503, 182, 35);
		getContentPane().add(label5);
		
		JLabel deadline = new JLabel("\uBAA8\uC9D1\uAE30\uAC04");
		deadline.setForeground(Color.WHITE);
		deadline.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		deadline.setBounds(103, 550, 71, 35);
		getContentPane().add(deadline);
		
		JLabel label6 = new JLabel(recruitment.getDeadlineYear() + "³â " + recruitment.getDeadlineMonth() + "¿ù " + recruitment.getDeadlineDay() + "ÀÏ ±îÁö");
		label6.setForeground(Color.WHITE);
		label6.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 14));
		label6.setBounds(204, 550, 182, 35);
		getContentPane().add(label6);
		
		JLabel select = new JLabel("\uB4F1\uB85D\uB9C8\uAC10");
		select.setForeground(Color.WHITE);
		select.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 16));
		select.setBounds(103, 597, 71, 35);
		getContentPane().add(select);
		
		JLabel label7 = new JLabel(recruitment.getSelectDeadlineYear() + "³â " + recruitment.getSelectDeadlineMonth() + "¿ù " + recruitment.getSelectDeadlineDay() + "ÀÏ ±îÁö");
		label7.setForeground(Color.WHITE);
		label7.setFont(new Font("¸¼Àº °íµñ Semilight", Font.BOLD, 14));
		label7.setBounds(204, 597, 182, 35);
		getContentPane().add(label7);
		
		if(userType == 0) {
			JButton apply = new JButton("\uC9C0\uC6D0\uD558\uAE30");
			apply.setBounds(281, 642, 105, 27);
			
			apply.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(recruitment.getProgress() == 0) {
                		if (recruitment.checkUser(user.getStudentID()) == -1) {
                			String[] buttons = {"½ÅÃ»", "Ãë¼Ò"};
            				int result = 0;
            				result = JOptionPane.showOptionDialog(null, "½ÅÃ»ÇÏ½Ã°Ú½À´Ï±î?", "ÀÀ½Ã¿ø¼­ ÀÛ¼º", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "Ãë¼Ò");
            				if(result == 0) {
                                Application newone = user.ApplicationCreate(recruitment.getRecruitNum());
                                recruitment.addList(newone);
                                Observable.uploadData();
                                Observable.notifyObservers();
                                
                                JOptionPane.showMessageDialog(null, "ÀÀ½Ã¿ø¼­ ÀÛ¼ºÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.", "¾Ë¸²", JOptionPane.PLAIN_MESSAGE);
            				}
                        } else
                            JOptionPane.showMessageDialog(null, "ÀÌ¹Ì ÀÀ½ÃÇÑ ¸ðÁý°ø°íÀÔ´Ï´Ù.", "¾Ë¸²", JOptionPane.PLAIN_MESSAGE);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "½ÅÃ»ÇÒ ¼ö ¾ø´Â »óÅÂÀÔ´Ï´Ù.", "¾Ë¸²", JOptionPane.PLAIN_MESSAGE);
                	}
                }
            });
			getContentPane().add(apply);
		}
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(RecruitContent.class.getResource("/systemUI/image/\uBAA8\uC9D1\uBC30\uACBD.png")));
		background.setBounds(0, 0, 400, 700);
		getContentPane().add(background);
		
		
	}
}
